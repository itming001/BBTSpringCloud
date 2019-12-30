package com.li.bbt.common.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * @author itming
 */
@Data
public class BbtAuthUser implements Serializable {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 是否过期
     */
    private boolean accountNonExpired = true;
    /**
     * 是否锁定
     */
    private boolean accountNonLocked= true;
    /**
     * 凭证是否过期
     */
    private boolean credentialsNonExpired= true;
    /**
     * 是否可用
     */
    private boolean enabled= true;
}
