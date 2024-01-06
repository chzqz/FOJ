package icu.chzqz.foj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class FojWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(FojWebApplication.class, args);
    }

}
