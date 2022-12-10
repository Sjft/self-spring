package com.sjft.springframework.beans.util;

import com.sjft.springframework.beans.core.io.ClassPathResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sjft
 * @date 2022-12-10 11:24
 */
public class ClassUtils {

    private static Logger logger = LoggerFactory.getLogger(ClassUtils.class);

    private ClassUtils() {
    }

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;

        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Exception e) {
            logger.error("Cannot access thread context ClassLoader - falling back to system class loader...");
        }

        if (cl == null) {
            cl = ClassUtils.getDefaultClassLoader();
        }
        return cl;
    }
}
