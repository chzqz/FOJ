package icu.chzqz.foj.controller;

import icu.chzqz.foj.dto.UserPageDTO;
import icu.chzqz.foj.result.Result;
import icu.chzqz.foj.service.UserService;
import icu.chzqz.foj.vo.UserPageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("/user/list")
    public Result<List<UserPageVO>> userList(UserPageDTO userPageDTO){
        log.info("用户列表: {}", userPageDTO);
        List<UserPageVO> list = userService.list(userPageDTO);
        return Result.success(list);
    }
}
