package com.sjft.springframework.beans.factory.support;

import com.sjft.springframework.beans.factory.BeansException;
import com.sjft.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author sjft
 * @date 2022-12-04 10:41
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctr, Object[] args) throws BeansException {
        Class<?> beanClass = beanDefinition.getBeanClass();

        try {
            if (ctr != null) {
                return beanClass.getConstructor(ctr.getParameterTypes()).newInstance(args);
            } else {
                return beanClass.getConstructor().newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new BeansException("Failed to instantiate [" + beanClass.getName() + "]", e);
        }
    }
}
