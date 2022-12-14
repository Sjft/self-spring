package com.sjft.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.sjft.springframework.beans.core.io.Resource;
import com.sjft.springframework.beans.core.io.ResourceLoader;
import com.sjft.springframework.beans.factory.BeansException;
import com.sjft.springframework.beans.factory.PropertyValue;
import com.sjft.springframework.beans.factory.config.BeanDefinition;
import com.sjft.springframework.beans.factory.config.BeanReference;
import com.sjft.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.sjft.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author sjft
 * @date 2022-12-10 14:29
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        super(beanDefinitionRegistry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        super(beanDefinitionRegistry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) {

        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }

    }

    @Override
    public void loadBeanDefinitions(Resource... resources) {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    private void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);

            // ????????????
            if (!(item instanceof Element)) {
                continue;
            }

            // ????????????
            if (!"bean".equals(item.getNodeName())) {
                continue;
            }

            // ????????????
            Element bean = (Element) item;
            final String id = bean.getAttribute("id");
            final String name = bean.getAttribute("name");
            final String className = bean.getAttribute("class");

            Class<?> clazz = Class.forName(className);
            // ????????? id > name
            String beanName = StrUtil.isNotBlank(id) ? id : name;
            if (StrUtil.isBlank(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // ?????? bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            // ?????????????????????
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                Node propertyNode = bean.getChildNodes().item(j);
                if (!(propertyNode instanceof Element)) {
                    continue;
                }

                if (!"property".equals(propertyNode.getNodeName())) {
                    continue;
                }

                // ???????????????propertyNode
                Element property = (Element) propertyNode;
                final String attrName = property.getAttribute("name");
                final String attrValue = property.getAttribute("value");
                final String attrRef = property.getAttribute("ref");

                // ??????????????????????????????????????????
                Object value = StrUtil.isNotBlank(attrRef) ? new BeanReference(attrRef) : attrValue;
                // ??????????????????
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }

            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }

            // ?????? BeanDefinition
            getRegistry().registryBeanDefinition(beanName, beanDefinition);

        }
    }
}
