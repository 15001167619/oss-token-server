package com.app.token.server.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.app.token.server.config.properties.DruidProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author 武海升
 * @date 2018/9/6 9:09
 */
@Configuration
public class DruidConfig {

    @Resource
    private DruidProperties druidProperties;

    /**
     * 核心数据源
     */
    private DruidDataSource coreDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }


    /**
     * 单数据源连接池配置
     */
    @Bean
    public DruidDataSource singleDatasource() {
        return coreDataSource();
    }

}
