package com.sjft.springframework.beans.factory;

import cn.hutool.core.io.IoUtil;
import com.sjft.springframework.beans.core.io.DefaultResourceLoader;
import com.sjft.springframework.beans.core.io.Resource;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author sjft
 * @date 2022-12-10 15:50
 */
public class ResourceLoaderTest {

    private DefaultResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void classpathTest() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println("content = " + content);
    }

    @Test
    public void fileTest() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println("content = " + content);
    }
}
