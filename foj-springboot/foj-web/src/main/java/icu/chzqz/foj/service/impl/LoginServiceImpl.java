package icu.chzqz.foj.service.impl;

import icu.chzqz.foj.dto.LoginDTO;
import icu.chzqz.foj.dto.RegisterDTO;
import icu.chzqz.foj.entity.User;
import icu.chzqz.foj.entity.exception.RequestFailException;
import icu.chzqz.foj.mapper.UserMapper;
import icu.chzqz.foj.properties.MessageProperty;
import icu.chzqz.foj.service.LoginService;
import icu.chzqz.foj.util.FormatUtil;
import icu.chzqz.foj.util.JWTUtil;
import icu.chzqz.foj.util.MD5Util;
import icu.chzqz.foj.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    MessageProperty messageProperty;

    /**
     * 用户登录
     * @param loginDTO 用户登录的数据传输对象
     * @return 用户登录的视图对象
     */
    @Override
    public LoginVO login(LoginDTO loginDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException, RequestFailException {
        String passwd = MD5Util.getMD5ByString(loginDTO.getPassword());
        User user = userMapper.selectByName(loginDTO.getUsername());
        if(user==null||!user.getPassword().equals(passwd)){
            throw new RequestFailException(messageProperty.loginFailed);
        }
        //更新最后登录时间
        User temp = User.builder().lastLoginTime(LocalDateTime.now()).id(user.getId()).build();
        userMapper.update(temp);

        //创建token
        Map claims = new HashMap();
        claims.put("name",user.getName());
        claims.put("id",user.getId());
        claims.put("authority",user.getAuthority());
        String token = JWTUtil.createToken(claims);
        return new LoginVO(token,user.getId(),user.getName(),user.getAuthority());
    }

    @Override
    public void register(RegisterDTO registerDTO) throws RequestFailException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if(!FormatUtil.checkEmail(registerDTO.getEmail())){
            //邮箱不符合格式
            throw new RequestFailException(messageProperty.emailFormatError);
        }
        if(!FormatUtil.checkPasswd(registerDTO.getPassword())){
            // 密码不符合规范
            throw new RequestFailException(messageProperty.unqualifiedPassword);
        }
        User user = userMapper.selectByName(registerDTO.getName());
        if(user!=null){
            // 用户已存在
            throw new RequestFailException(messageProperty.usernameExists);
        }
        user = userMapper.selectByEmail(registerDTO.getEmail());
        if(user!=null){
            // 邮箱已存在
            throw new RequestFailException(messageProperty.emailExists);
        }

        user = User.builder()
                .name(registerDTO.getName())
                .password(MD5Util.getMD5ByString(registerDTO.getPassword()))
                .email(registerDTO.getEmail())
                .createDate(LocalDateTime.now())
                .authority(0)
                .accepted(0)
                .experiment(0)
                .build();
        userMapper.insert(user);
    }
}
