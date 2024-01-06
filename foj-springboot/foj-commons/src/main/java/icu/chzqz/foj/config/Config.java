package icu.chzqz.foj.config;

import icu.chzqz.foj.util.CmdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }


}
