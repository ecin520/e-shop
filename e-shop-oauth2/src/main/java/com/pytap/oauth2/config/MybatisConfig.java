package com.pytap.oauth2.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 扫描包
 * @author Ecin520
 * @date 2020/8/22 0:48
 */
@MapperScan({"com.pytap.generator.dao", "com.pytap.oauth2.dao"})
@Configuration
public class MybatisConfig {

}
