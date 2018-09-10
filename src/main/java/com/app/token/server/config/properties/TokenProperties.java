package com.app.token.server.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 武海升
 * @date 2018/9/5 17:26
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "alitoken")
public class TokenProperties {

    private String accessKeyID;
    private String accessKeySecret;
    private String roleArn;
    private Long tokenExpireTime;

}
