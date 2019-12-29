package com.li.bbt.registry.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author itming
 */
//开启springSecurity认证
@EnableWebSecurity
public class BbtRegisterWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //以eureka开头的所有请求进行放行
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
}
