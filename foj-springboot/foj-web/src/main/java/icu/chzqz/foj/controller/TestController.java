package icu.chzqz.foj.controller;


import icu.chzqz.foj.judgeServer.pojo.Cmd;
import icu.chzqz.foj.judgeServer.pojo.Collector;
import icu.chzqz.foj.judgeServer.pojo.File;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/test")
    public Cmd test(){
        return new Cmd();
    }
}
