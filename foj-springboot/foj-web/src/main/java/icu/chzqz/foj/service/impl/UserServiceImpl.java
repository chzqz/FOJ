package icu.chzqz.foj.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import icu.chzqz.foj.dto.*;
import icu.chzqz.foj.entity.User;
import icu.chzqz.foj.entity.exception.RequestFailException;
import icu.chzqz.foj.mapper.UserMapper;
import icu.chzqz.foj.properties.DefaultProperty;
import icu.chzqz.foj.properties.MessageProperty;
import icu.chzqz.foj.properties.ServerProperty;
import icu.chzqz.foj.result.PageResult;
import icu.chzqz.foj.service.UserService;
import icu.chzqz.foj.util.BaseContextUtil;
import icu.chzqz.foj.util.FormatUtil;
import icu.chzqz.foj.util.MD5Util;
import icu.chzqz.foj.vo.InformationVO;
import icu.chzqz.foj.vo.UserPageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    ServerProperty serverProperty;
    @Autowired
    MessageProperty messageProperty;
    @Autowired
    DefaultProperty defaultProperty;

    /**
     * 通过id获取用户头像
     * @param id 用户id
     * @return 头像路径
     */
    @Override
    public String getAvatar(Integer id) {
        return "http://"+serverProperty.getResourceServer()+"/src/avatar"+userMapper.selectAvatarById(id);
    }

    @Override
    public InformationVO information() {
        Integer id = (Integer) BaseContextUtil.getBaseContext().get("id");
        InformationVO informationVO = new InformationVO();
        User user = userMapper.selectById(id);
        BeanUtils.copyProperties(user,informationVO);
        if(user.getExperiment()!=0) informationVO.setPassRate(1.0*user.getAccepted()/user.getExperiment());
        else informationVO.setPassRate(0D);
        return informationVO;
    }

    @Override
    public void modifyInformation(ModifyUserInfoDTO modifyUserInfoDTO) throws RequestFailException {
        if(!FormatUtil.checkEmail(modifyUserInfoDTO.getEmail())){
            //邮箱不符合格式
            throw new RequestFailException(messageProperty.emailFormatError);
        }
        Integer id = (Integer) BaseContextUtil.getBaseContext().get("id");
        User user = userMapper.selectByName(modifyUserInfoDTO.getName());
        if(user!=null&&!user.getId().equals(id)){
            // 用户已存在
            throw new RequestFailException(messageProperty.usernameExists);
        }
        user = userMapper.selectByEmail(modifyUserInfoDTO.getEmail());
        if(user!=null&&!user.getId().equals(id)){
            // 邮箱已存在1
            throw new RequestFailException(messageProperty.emailExists);
        }

        user = new User();
        BeanUtils.copyProperties(modifyUserInfoDTO,user);

        user.setId((Integer) BaseContextUtil.getBaseContext().get("id"));
        userMapper.update(user);
    }

    @Override
    public void uploadAvatar(MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        Integer id = (Integer) BaseContextUtil.getBaseContext().get("id");
        String oldPath = userMapper.selectAvatarById(id);
        File oldFile = new File(oldPath);
        oldFile.delete();
        User user = new User();
        String path = defaultProperty.avatarPath + id+"_"+ UUID.randomUUID() + name.substring(name.lastIndexOf("."));
        user.setId(id);
        user.setPicture(path);
        userMapper.update(user);
        File dest = new File(path);
        file.transferTo(dest);
    }

    @Override
    public void modifyPasswd(ModifyPasswdDTO modifyPasswdDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException, RequestFailException {
        Integer id = (Integer) BaseContextUtil.getBaseContext().get("id");
        User user = userMapper.selectById(id);
        if(!user.getPassword().equals(MD5Util.getMD5ByString(modifyPasswdDTO.getOldPassword()))){
            //旧密码错误
            throw new RequestFailException(messageProperty.passwordError);
        }
        if(!FormatUtil.checkPasswd(modifyPasswdDTO.getNewPassword())){
            //密码不符合规范
            throw new RequestFailException(messageProperty.unqualifiedPassword);
        }
        //更新密码
        userMapper.updatePasswdById(id,MD5Util.getMD5ByString(modifyPasswdDTO.getNewPassword()));
    }

    @Override
    public PageResult<UserPageVO> list(UserPageDTO userPageDTO) {
        PageHelper.startPage(userPageDTO.getPage(),userPageDTO.getPageSize());
        Page<User> page = userMapper.list(userPageDTO);
        List<User> userList = page.getResult();
        List<UserPageVO> result = new ArrayList<>();
        for (User user : userList) {
            UserPageVO userPageVO = new UserPageVO(user.getId(),user.getName(),user.getEmail(),user.getAccepted(),user.getExperiment(),user.getCreateDate(),user.getLastLoginTime(),user.getAuthority());
            result.add(userPageVO);
        }
        return new PageResult<>(page.getTotal(),result);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.delete(id);
    }

    @Override
    public void modifyUser(ModifyUserDTO modifyUserDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = User.builder()
                .id(modifyUserDTO.getId())
                .name(modifyUserDTO.getName())
                .email(modifyUserDTO.getEmail())
                .password(MD5Util.getMD5ByString(modifyUserDTO.getPassword()))
                .authority(modifyUserDTO.getAuthority())
                .build();
        userMapper.update(user);
    }
}
