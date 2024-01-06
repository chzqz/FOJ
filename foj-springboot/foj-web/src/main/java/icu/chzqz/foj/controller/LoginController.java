package icu.chzqz.foj.controller;

import icu.chzqz.foj.dto.LoginDTO;
import icu.chzqz.foj.dto.RegisterDTO;
import icu.chzqz.foj.entity.exception.RequestFailException;
import icu.chzqz.foj.result.Result;
import icu.chzqz.foj.service.LoginService;
import icu.chzqz.foj.util.BaseContextUtil;
import icu.chzqz.foj.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result<LoginVO> login(@RequestBody LoginDTO loginDTO) throws RequestFailException, UnsupportedEncodingException, NoSuchAlgorithmException {
        log.info("用户登录: {}",loginDTO);
        LoginVO loginVO = loginService.login(loginDTO);
        return Result.success(loginVO);
    }

    @PostMapping("/logout")
    public Result logout(){
        log.info("用户退出登录: {}", BaseContextUtil.getBaseContext().get("id"));
        return Result.success();
    }

    @PostMapping("/regist")
    public Result register(@RequestBody RegisterDTO registerDTO) throws RequestFailException, UnsupportedEncodingException, NoSuchAlgorithmException {
        log.info("用户注册: {}",registerDTO);
        loginService.register(registerDTO);
        return Result.success();
    }
}
