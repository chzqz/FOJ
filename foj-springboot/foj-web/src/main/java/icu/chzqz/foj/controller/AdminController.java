package icu.chzqz.foj.controller;

import icu.chzqz.foj.dto.ModifyUserDTO;
import icu.chzqz.foj.dto.TagDTO;
import icu.chzqz.foj.dto.UserPageDTO;
import icu.chzqz.foj.result.PageResult;
import icu.chzqz.foj.result.Result;
import icu.chzqz.foj.service.UserService;
import icu.chzqz.foj.vo.UserPageVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("/user/list")
    public Result<PageResult<UserPageVO>> userList(UserPageDTO userPageDTO){
        log.info("用户列表: {}", userPageDTO);
        PageResult<UserPageVO> list = userService.list(userPageDTO);
        return Result.success(list);
    }


    @DeleteMapping("/admin/user")
    public Result deleteUser(@RequestBody Integer id){
        log.info("删除用户: {}", id);
        userService.deleteUser(id);
        return Result.success();
    }

    @PutMapping("/admin/user")
    public Result modifyUser(@RequestBody ModifyUserDTO modifyUserDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        log.info("修改用户信息: {}", modifyUserDTO);
        userService.modifyUser(modifyUserDTO);
        return Result.success();
    }


}
