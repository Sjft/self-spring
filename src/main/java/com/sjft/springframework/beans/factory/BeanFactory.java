package com.sjft.springframework.beans.factory;

/**
 * @author sjft
 * @date 2022-12-03 14:53
 */
public interface BeanFactory {

    /**
     * 获取 bean 实例
     *
     * @param name 名字
     * @return {@code Object}
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;

    /**
     * 获取 bean 实例
     *
     * @param name 名字
     * @param args 参数
     * @return {@code Object}
     * @throws BeansException
     */
    Object getBean(String name, Object... args) throws BeansException;

    /**
     * 获取 bean 实例
     *
     * @param name         名字
     * @param requiredType 所需类型
     * @return {@code T}
     * @throws BeansException 豆子例外
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
