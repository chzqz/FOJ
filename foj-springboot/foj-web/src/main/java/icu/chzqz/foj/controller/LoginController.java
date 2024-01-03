package icu.chzqz.foj.controller;

import icu.chzqz.foj.dto.LoginDTO;
import icu.chzqz.foj.exception.RequestFailException;
import icu.chzqz.foj.result.Result;
import icu.chzqz.foj.service.LoginService;
import icu.chzqz.foj.util.JWTUtil;
import icu.chzqz.foj.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

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


}
