package com.company;

import java.util.Vector;

/**
 * Created by Duy Le on 7/29/2017.
 */
public class Instance {
    private int index;
    private Vector<AttributeInstance> attributeInstanceVector = new Vector<>();
    public Instance(int index,Vector<Attribute> attributeVector,String[] input){
        this.index=index;
        for(int i=0;i<input.length;i++){
            AttributeInstance attributeInstance = new AttributeInstance(attributeVector.get(i),input[i]);
            attributeInstanceVector.add(attributeInstance);
        }
    }
    public int getSize(){
        return attributeInstanceVector.size();
    }
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Vector<AttributeInstance> getAttributeInstanceVector() {
        return attributeInstanceVector;
    }

    public void setAttributeInstanceVector(Vector<AttributeInstance> attributeInstanceVector) {
        this.attributeInstanceVector = attributeInstanceVector;
    }
    public AttributeInstance getAttributeInstance(int i){
        return attributeInstanceVector.get(i);
    }
    public String getInfo(){
        String result = new String();
        for(int i=0;i<attributeInstanceVector.size();i++){
            result = result.concat(attributeInstanceVector.get(i).getValue()+" ");
        }
        return result;
    }
    public double calculateDistance(Instance otherInstance){
        double result = 0;
        for(int i=0;i<attributeInstanceVector.size();i++){
            result+=(double)(this.attributeInstanceVector.get(i).getIntValue()-otherInstance.getAttributeInstance(i).getIntValue())*(this.attributeInstanceVector.get(i).getIntValue()-otherInstance.getAttributeInstance(i).getIntValue());
        }
        result = Math.sqrt(result);
        return result;
    }
}
