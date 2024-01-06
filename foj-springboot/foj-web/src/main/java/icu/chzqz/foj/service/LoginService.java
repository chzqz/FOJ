package icu.chzqz.foj.service;

import icu.chzqz.foj.dto.LoginDTO;
import icu.chzqz.foj.dto.RegisterDTO;
import icu.chzqz.foj.entity.exception.RequestFailException;
import icu.chzqz.foj.vo.LoginVO;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface LoginService {

    /**
     * 用户登录
     * @param loginDTO 用户登录的数据传输对象
     * @return 用户登录视图对象
     */
    LoginVO login(LoginDTO loginDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException, RequestFailException;

    /**
     * 注册用户
     * @param registerDTO
     */
    void register(RegisterDTO registerDTO) throws RequestFailException, UnsupportedEncodingException, NoSuchAlgorithmException;
}
