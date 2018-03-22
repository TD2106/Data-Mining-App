package com.company;

import java.util.HashMap;
import java.util.Vector;

/**
 * Created by Duy Le on 7/29/2017.
 */
public class Attribute {
    private String name;
    private int attributeIndex;
    private HashMap<String,Integer> stringToIntTable = new HashMap<>();
    private Vector<String> strings = new Vector<>();
    public Attribute(String[] input,int attributeIndex){
        this.attributeIndex = attributeIndex;
        for(int i=0;i<input.length;i++){
            if(i==0){
                this.name = input[i];
            }
            else{
                stringToIntTable.put(input[i],i-1);
                strings.add(input[i]);
            }
        }
    }
    public int getAttributeIndex() {
        return attributeIndex;
    }
    public String getStringValue(int index){
        return strings.get(index);
    }

    public String getName() {
        return name;
    }
    public int getNumberOfValue(){
        return stringToIntTable.size();
    }
    public int getIntValue(String value){
        return stringToIntTable.get(value);
    }


}
