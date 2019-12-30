package com.li.bbt.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
//每一个都是Eureka的客户端
@EnableDiscoveryClient
//开启zuul网关的功能
@EnableZuulProxy
public class BbtGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BbtGatewayApplication.class, args);
    }

}
