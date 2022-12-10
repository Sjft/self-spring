package com.sjft.springframework.beans.factory.support;

import com.sjft.springframework.beans.core.io.Resource;
import com.sjft.springframework.beans.core.io.ResourceLoader;

/**
 * @author sjft
 * @date 2022-12-10 14:15
 */
public interface BeanDefinitionReader {

    /**
     * 返回用于 bean 注册的 bean 工厂
     *
     * @return {@code BeanDefinitionRegistry}
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 返回要用于资源位置的资源加载器
     *
     * @return {@code ResourceLoader}
     */
    ResourceLoader getResourceLoader();

    /**
     * 从指定的资源加载 bean 定义。
     *
     * @param resource 资源
     */
    void loadBeanDefinitions(Resource resource);

    /**
     * 从指定的资源加载 bean 定义。
     *
     * @param resources 资源
     */
    void loadBeanDefinitions(Resource... resources);

    /**
     * 从指定的资源位置加载 bean 定义。
     *
     * @param location 位置
     */
    void loadBeanDefinitions(String location);
}
