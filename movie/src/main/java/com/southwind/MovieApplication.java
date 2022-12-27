package com.southwind;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//扫描 repository
@MapperScan("com.southwind.repository")
public class MovieApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class,args);
    }
}