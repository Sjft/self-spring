package com.sjft.springframework.beans.factory.support;

import com.sjft.springframework.beans.factory.config.BeanDefinition;

/**
 * @author sjft
 * @date 2022-12-03 16:12
 */
public interface BeanDefinitionRegistry {
    /**
     * 在注册表中注册 beanDefinition
     *
     * @param beanName       bean名字
     * @param beanDefinition bean定义
     */
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 返回给定 bean 名称的 BeanDefinition。
     *
     * @param beanName bean名字
     * @return {@code BeanDefinition}
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 检查此注册表是否包含具有给定名称的 bean 定义。
     *
     * @param beanName bean名字
     * @return boolean
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 返回此注册表中定义的所有 bean 的名称。
     *
     * @return {@code String[]}
     */
    String[] getBeanDefinitionNames();
}
