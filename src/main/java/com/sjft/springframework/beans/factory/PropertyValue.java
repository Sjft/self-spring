package com.sjft.springframework.beans.factory;

/**
 * @author sjft
 * @date 2022-12-07 13:26
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
