package com.sjft.springframework.beans.core.io;

/**
 * @author sjft
 * @date 2022-12-10 11:19
 * 资源加载器
 */
public interface ResourceLoader {

    /** Pseudo URL prefix for loading from the class path: "classpath:". */
    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * Return a Resource handle for the specified resource location.
     *
     * @param location 位置
     * @return {@code Resource}
     */
    Resource getResource(String location);
}
