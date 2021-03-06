package com.li.bbt.auth.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author itming
 */
@RestController
public class SecurityController {
    @Resource
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("oauth/test")
    public String testOauth() {
        return "oauth";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @DeleteMapping("signout")
    public String signout(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");

        return "退出登录成功";
    }

}
