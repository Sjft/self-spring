package com.sjft.springframework.beans.factory;

/**
 * @author sjft
 * @date 2022-12-03 16:06
 */
public class BeansException extends RuntimeException {
    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
