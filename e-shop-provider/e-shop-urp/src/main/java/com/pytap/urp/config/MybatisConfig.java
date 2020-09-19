package com.pytap.urp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Ecin520
 * @date 2020/8/19 15:55
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.pytap.generator.dao", "com.pytap.urp.dao"})
public class MybatisConfig {

}
