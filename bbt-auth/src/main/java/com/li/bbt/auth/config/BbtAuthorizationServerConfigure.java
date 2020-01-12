package com.li.bbt.auth.config;

import com.li.bbt.auth.properties.BbtAuthProperties;
import com.li.bbt.auth.properties.BbtClientsProperties;
import com.li.bbt.auth.service.BbtUserDetailService;
import com.li.bbt.auth.translator.BbtWebResopnseExceptionTranslator;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import java.util.stream.Stream;

/**
 * @author itming
 * 令牌配置
 */
@Configuration
@EnableAuthorizationServer
public class BbtAuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisConnectionFactory redisConnectionFactory;
    @Resource
    private BbtUserDetailService bbtUserDetailService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private BbtAuthProperties bbtAuthProperties;
    @Resource
    private BbtWebResopnseExceptionTranslator bbtWebResopnseExceptionTranslator;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //获取创建的客户端
        BbtClientsProperties[] clientArray = bbtAuthProperties.getClients();
        //SpringSecurity配置文件基于内存配置
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        if(ArrayUtils.isNotEmpty(clientArray)){
            for (BbtClientsProperties client :clientArray) {
                if(StringUtils.isBlank(client.getClient())){
                    throw new Exception("client不能为空");
                }
                if(StringUtils.isBlank(client.getSecret())){
                    throw new Exception("secret不能为空");
                }
                String[] grantTypes = StringUtils.splitByWholeSeparatorPreserveAllTokens(client.getGrantType(), ",");
                builder.withClient(client.getClient())
                        .secret(passwordEncoder.encode(client.getSecret()))
                        .authorizedGrantTypes(grantTypes)
                        .scopes(client.getScope());
            }

        }
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore())
                .userDetailsService(bbtUserDetailService)
                .authenticationManager(authenticationManager)
                .tokenServices(defaultTokenServices())
                //设置自定义异常翻译器
                .exceptionTranslator(bbtWebResopnseExceptionTranslator);
    }
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setAccessTokenValiditySeconds(bbtAuthProperties.getAccessTokenValiditySeconds());
        tokenServices.setRefreshTokenValiditySeconds(bbtAuthProperties.getRefreshTokenValiditySeconds());
        return tokenServices;
    }




}
