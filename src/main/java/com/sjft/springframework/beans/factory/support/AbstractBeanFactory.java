package com.sjft.springframework.beans.factory.support;

import com.sjft.springframework.beans.factory.BeanFactory;
import com.sjft.springframework.beans.factory.BeansException;
import com.sjft.springframework.beans.factory.config.BeanDefinition;

/**
 * @author sjft
 * @date 2022-12-03 15:07
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }

        BeanDefinition<Object> beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    /**
     * 得到 bean 定义
     *
     * @param beanName bean 名称
     * @return {@code BeanDefinition<T>}
     * @throws BeansException
     */
    protected abstract <T> BeanDefinition<T> getBeanDefinition(String beanName) throws BeansException;

    /**
     * 创建 bean
     *
     * @param beanName       bean 名字
     * @param beanDefinition bean 定义
     * @param args           参数
     * @return {@code Object}
     * @throws BeansException
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

}
