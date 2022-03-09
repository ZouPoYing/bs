package com.graduation.bs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.graduation.bs.mapper")
public class BsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsApplication.class, args);
    }

}
