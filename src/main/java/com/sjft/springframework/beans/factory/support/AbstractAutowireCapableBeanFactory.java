package com.sjft.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.sjft.springframework.beans.factory.BeansException;
import com.sjft.springframework.beans.factory.PropertyValue;
import com.sjft.springframework.beans.factory.PropertyValues;
import com.sjft.springframework.beans.factory.config.BeanDefinition;
import com.sjft.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * @author sjft
 * @date 2022-12-03 15:57
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {

        Object bean;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
            // 给 bean 填充属性
            applyPropertyValues(bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();

        Constructor<?> constructorToUse = null;
        for (Constructor<?> constructor : declaredConstructors) {
            if (args != null && constructor.getParameterTypes().length == args.length) {
                constructorToUse = constructor;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) {

        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                // A 依赖 B, 获取 B 的实例化对象
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getBeanName());
            }
            // 属性填充
            BeanUtil.setFieldValue(bean, name, value);
        }
    }
}
