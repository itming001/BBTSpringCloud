package com.li.bbt.auth.properties;

import lombok.Data;

/**
 * @author itming
 * 创建client配置类
 */
@Data
public class BbtClientsProperties {
    /**
     * 对应client_id
     */
    private String client;
    /**
     * 对应client_security
     */
    private String secret;
    /**
     * 对应授权类型
     */
    private String grantType = "password,authorization_code,refresh_token";
    /**
     * 授权范围
     */
    private String scope="all";
}
