package icu.chzqz.foj.service;

import icu.chzqz.foj.dto.*;
import icu.chzqz.foj.entity.exception.RequestFailException;
import icu.chzqz.foj.result.PageResult;
import icu.chzqz.foj.vo.InformationVO;
import icu.chzqz.foj.vo.UserPageVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {
    /**
     * 根据id获取头像
     * @param id
     * @return
     */
    String getAvatar(Integer id);

    /**
     * 获取用户个人信息
     * @return
     */
    InformationVO information();

    /**
     * 修改个人用户信息
     * @param modifyUserInfoDTO
     */
    void modifyInformation(ModifyUserInfoDTO modifyUserInfoDTO) throws RequestFailException;

    /**
     * 上传头像
     * @param file
     */
    void uploadAvatar(MultipartFile file) throws IOException;

    /**
     * 修改密码
     * @param modifyPasswdDTO
     */
    void modifyPasswd(ModifyPasswdDTO modifyPasswdDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException, RequestFailException;

    /**
     * 列表查询
     * @param userPageDTO
     * @return
     */
    PageResult<UserPageVO> list(UserPageDTO userPageDTO);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 修改用户信息
     * @param modifyUserDTO
     */
    void modifyUser(ModifyUserDTO modifyUserDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
