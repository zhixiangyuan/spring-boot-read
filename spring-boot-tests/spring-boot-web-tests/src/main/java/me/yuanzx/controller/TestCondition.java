package me.yuanzx.controller;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author zhixiang.yuan
 * @since 2019/12/31 19:34:18
 */
public class TestCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    	// todo 奇了怪了，这里会被调用四次
        return true;
    }

}
