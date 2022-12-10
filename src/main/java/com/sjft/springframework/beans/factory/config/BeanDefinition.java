package com.sjft.springframework.beans.factory.config;

import com.sjft.springframework.beans.factory.PropertyValues;

/**
 * @author sjft
 * @date 2022-12-03 14:51
 * bean 对象元信息
 */
public class BeanDefinition<T> {

    private Class<T> beanClass;

    private PropertyValues propertyValues;

    public BeanDefinition(Class<T> beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class<T> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class<T> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<T> beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
