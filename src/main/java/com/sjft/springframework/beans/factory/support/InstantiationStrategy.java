package com.sjft.springframework.beans.factory.support;

import com.sjft.springframework.beans.factory.BeansException;
import com.sjft.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author sjft
 * @date 2022-12-04 10:38
 */
public interface InstantiationStrategy {

    /**
     * 实例化
     *
     * @param beanDefinition bean定义
     * @param beanName       bean名称
     * @param ctr            ctr
     * @param args           参数
     * @return {@code Object}
     * @throws BeansException
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctr, Object[] args) throws BeansException;
}
