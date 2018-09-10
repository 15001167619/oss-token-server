package com.app.token.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 武海升
 * @date 2018/9/5 17:05
 */

@SpringBootApplication
@Slf4j
public class TokenServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TokenServerApplication.class, args);
        log.info("Application Spring Boot TokenServer Start-Up is success!");
    }

}
