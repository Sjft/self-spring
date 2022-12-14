package com.sjft.springframework.beans.core.io;


import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author sjft
 * @date 2022-12-10 11:20
 */
public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {

        Assert.notNull(location, "Location must not be null");

        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                return new UrlResource(new URL(location));
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }
}
