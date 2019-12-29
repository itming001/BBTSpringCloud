package com.li.bbt.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//开启服务注册与发现功能
@EnableDiscoveryClient
public class BbtAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(BbtAuthApplication.class, args);
    }

}
