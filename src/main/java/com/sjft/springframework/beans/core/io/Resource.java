package com.sjft.springframework.beans.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author sjft
 * @date 2022-12-10 11:16
 */
public interface Resource {

    /**
     * 获取输入流
     *
     * @return {@code InputStream}
     * @throws IOException ioexception
     */
    InputStream getInputStream() throws IOException;
}
