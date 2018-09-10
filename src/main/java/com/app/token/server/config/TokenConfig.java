package com.app.token.server.config;

import com.app.token.server.config.properties.TokenProperties;
import com.app.token.server.service.IAuthorizationService;
import com.app.token.server.service.impl.AuthorizationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 武海升
 * @date 2018/9/5 17:26
 */
@Configuration
public class TokenConfig {

    @Autowired
    private TokenProperties tokenProperties;

    @Bean
    public IAuthorizationService authorizationService(){
        IAuthorizationService authorizationService = new AuthorizationServiceImpl();
        authorizationService.setAccessKeyID(tokenProperties.getAccessKeyID());
        authorizationService.setAccessKeySecret(tokenProperties.getAccessKeySecret());
        authorizationService.setRoleArn(tokenProperties.getRoleArn());
        authorizationService.setTokenExpireTime(tokenProperties.getTokenExpireTime());
        return authorizationService;
    }

}
