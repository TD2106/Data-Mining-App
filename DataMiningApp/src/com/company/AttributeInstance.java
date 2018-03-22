package com.company;

/**
 * Created by Duy Le on 7/29/2017.
 */
public class AttributeInstance {
    private String name;
    private int attributeIndex;
    private int intValue;
    private String value;

    public AttributeInstance(Attribute attribute,String value){
        this.name = attribute.getName();
        this.attributeIndex = attribute.getAttributeIndex();
        this.value = value;
        intValue = attribute.getIntValue(value);
    }

    public String getName() {
        return name;
    }

    public int getAttributeIndex() {
        return attributeIndex;
    }

    public int getIntValue() {
        return intValue;
    }

    public String getValue() {
        return value;
    }
}
