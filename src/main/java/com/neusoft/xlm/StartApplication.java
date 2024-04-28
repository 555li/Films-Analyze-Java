package com.neusoft.xlm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.neusoft.xlm.dao")
public class StartApplication {

    public static void main(String[] args) {
//1
        SpringApplication.run(StartApplication.class, args);
    }

}
