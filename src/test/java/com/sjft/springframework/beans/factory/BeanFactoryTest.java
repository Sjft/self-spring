package com.sjft.springframework.beans.factory;


import com.sjft.springframework.beans.factory.bean.UserDao;
import com.sjft.springframework.beans.factory.bean.UserService;
import com.sjft.springframework.beans.factory.config.BeanDefinition;
import com.sjft.springframework.beans.factory.config.BeanReference;
import com.sjft.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.sjft.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.junit.Test;

public class BeanFactoryTest {

    @Test
    public void getBean() {
        // 1. 初始化工厂
        DefaultListableBeanFactory<UserService> beanFactory = new DefaultListableBeanFactory<>();
        // 2. 注册 UserDao
        beanFactory.registryBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. userService 设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", 1));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        BeanDefinition<UserService> beanDefinition = new BeanDefinition<>(UserService.class, propertyValues);
        beanFactory.registryBeanDefinition("userService", beanDefinition);

        // 4. 第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService", "sjft");
        userService.queryUserInfo();

    }

    @Test
    public void getBeanByXml() {
        // 1. 初始化工厂
        DefaultListableBeanFactory<UserService> beanFactory = new DefaultListableBeanFactory<>();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:Spring.xml");


        // 3. 获取 Bean 对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();

    }

    public static void main(String[] args) {
        Class<?> aClass = null;
        try {
            aClass = Class.forName("com.sjft.springframework.beans.factory.bean.UserDao");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(aClass);
    }
}