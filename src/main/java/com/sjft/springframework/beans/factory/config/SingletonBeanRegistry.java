package com.sjft.springframework.beans.factory.config;

/**
 * @author sjft
 * @date 2022-12-03 14:52
 */
public interface SingletonBeanRegistry {

    /**
     * 获取单例 bean
     *
     * @param beanName bean名字
     * @return {@code Object}
     */
    Object getSingleton(String beanName);

}
