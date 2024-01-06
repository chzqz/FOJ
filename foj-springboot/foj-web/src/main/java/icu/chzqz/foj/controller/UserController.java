package icu.chzqz.foj.controller;

import icu.chzqz.foj.dto.JudgeDTO;
import icu.chzqz.foj.dto.ModifyPasswdDTO;
import icu.chzqz.foj.dto.ModifyUserInfoDTO;
import icu.chzqz.foj.entity.exception.RequestFailException;
import icu.chzqz.foj.result.Result;
import icu.chzqz.foj.service.UserService;
import icu.chzqz.foj.util.BaseContextUtil;
import icu.chzqz.foj.vo.InformationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController()
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;
    /**
     * 获取用户头像
     * @param id 用户id
     * @return
     */
    @GetMapping("/avatar/{id}")
    public Result<String> getAvatar(@PathVariable Integer id){
        log.info("获取头像: {}", id);
        String path = userService.getAvatar(id);
        return Result.success(path);
    }

    /**
     * 获取个人信息
     * @return
     */
    @GetMapping("/information")
    public Result<InformationVO> information(){
        log.info("获取用户个人信息: {}", BaseContextUtil.getBaseContext().get("id"));
        InformationVO informationVO = userService.information();
        return Result.success(informationVO);
    }

    /**
     * 修改个人信息
     * @param modifyUserInfoDTO
     * @return
     * @throws RequestFailException
     */
    @PutMapping("/information")
    public Result modifyInformation(@RequestBody ModifyUserInfoDTO modifyUserInfoDTO) throws RequestFailException {
        log.info("修改个人信息: {}",modifyUserInfoDTO);
        userService.modifyInformation(modifyUserInfoDTO);
        return Result.success();
    }

    /**
     * 头像上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/avatar")
    public Result uploadAvatar(@RequestBody MultipartFile file) throws IOException {
        log.info("头像上传");
        userService.uploadAvatar(file);
        return Result.success();
    }


    /**
     * 修改密码
     * @param modifyPasswdDTO
     * @return
     * @throws RequestFailException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @PutMapping("/password")
    public Result modifyPasswd(@RequestBody ModifyPasswdDTO modifyPasswdDTO) throws RequestFailException, UnsupportedEncodingException, NoSuchAlgorithmException {
        log.info("修改密码: {}",BaseContextUtil.getBaseContext().get("id"));
        userService.modifyPasswd(modifyPasswdDTO);
        return Result.success();
    }



}
