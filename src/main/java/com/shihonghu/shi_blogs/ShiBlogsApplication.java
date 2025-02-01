package com.shihonghu.shi_blogs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.shihonghu.shi_blogs.mapper")
public class ShiBlogsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiBlogsApplication.class, args);
    }

}
