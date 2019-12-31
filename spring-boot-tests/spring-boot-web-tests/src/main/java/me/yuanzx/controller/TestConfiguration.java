package me.yuanzx.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhixiang.yuan
 * @since 2019/12/31 19:34:44
 */
@Configuration
@Conditional(TestCondition.class)
public class TestConfiguration {

    @Bean
    @Conditional(TestCondition.class)
    public Object testObject() {
        return new Object();
    }

}