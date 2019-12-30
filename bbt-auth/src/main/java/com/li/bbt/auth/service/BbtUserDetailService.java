package com.li.bbt.auth.service;

import com.li.bbt.common.entity.BbtAuthUser;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author itming
 */
@Service
public class BbtUserDetailService implements UserDetailsService {
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = passwordEncoder.encode("123456") ;
        BbtAuthUser user = new BbtAuthUser();
        user.setUsername(username);
        user.setPassword(password);
        return new User(username,password, AuthorityUtils.commaSeparatedStringToAuthorityList("user:add"));
    }
}
