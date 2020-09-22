package com.pytap.order.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Ecin520
 * @date 2020/9/9 17:23
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.pytap.generator.dao"})
public class MybatisConfig {

}
