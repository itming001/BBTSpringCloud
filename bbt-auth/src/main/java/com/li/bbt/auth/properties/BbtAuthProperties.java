package com.li.bbt.auth.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author itming
 * 创建认证相关的配置类
 */
@Data
/**
 * 效果同@Component
 */
@SpringBootConfiguration
/**
 * 所有加载的配置文件
 */
@PropertySource("classpath:bbt-auth.properties")
/**
 * 加载配置文件的属性名的前缀
 */
@ConfigurationProperties(prefix = "bbt.auth")
public class BbtAuthProperties {
    /**
     * 可能会有多个客户端
     */
    private BbtClientsProperties[] clients = {};
    /**
     * access_token的有效时间   默认的配置时间
     */
    private int accessTokenValiditySeconds = 60 * 60 * 24;
    /**
     * 刷新令牌的有效时间    默认的配置时间
     */
    private int refreshTokenValiditySeconds = 60 * 60 * 24 * 7;
}
