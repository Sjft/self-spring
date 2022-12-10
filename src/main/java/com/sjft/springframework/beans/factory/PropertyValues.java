package com.sjft.springframework.beans.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sjft
 * @date 2022-12-07 13:27
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue) {
        propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        PropertyValue[] propertyValues = getPropertyValues();
        for (PropertyValue propertyValue : propertyValues) {
            if (propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }

        return null;
    }

}
