package com.company;

import java.io.*;
import java.util.Vector;

/**
 * Created by Duy Le on 8/12/2017.
 */
public class Instances {
    private String name;
    private Vector<Instance> instanceVector = new Vector<>();
    private Vector<Attribute> attributeVector = new Vector<>();

    public String getName() {
        return name;
    }

    public Vector<Instance> getInstances() {
        return instanceVector;
    }
    public int getSize(){
        return instanceVector.size();
    }
    public int getInstanceSize(){
        return instanceVector.get(0).getSize();
    }
    public void getInput(String fileName){
        int index = 0;
        try{
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String inputLine;
            while((inputLine=bufferedReader.readLine())!=null){
                if(inputLine.isEmpty()) continue;
                else if(inputLine.charAt(0)=='%') continue;
                else if(inputLine.contains("@relation")) {
                    inputLine = inputLine.replace("@relation","");
                    inputLine = inputLine.replaceAll(" ","");
                    this.name = inputLine;
                }
                else if(inputLine.contains("@attribute")){
                    inputLine = inputLine.replace("@attribute ","");
                    inputLine = inputLine.replace("{","");
                    inputLine = inputLine.replace("}","");
                    inputLine = inputLine.replaceAll(",","");
                    inputLine = inputLine.replaceAll("\t"," ");
                    String inputStringArray[] = inputLine.split(" +");
                    Attribute attribute = new Attribute(inputStringArray,index);
                    index++;
                    attributeVector.add(attribute);
                }
                else if(inputLine.contains("@data")){
                    index = 0;
                }
                else{
                    String inputStringArray[] = inputLine.split(",");
                    Instance instance = new Instance(index,attributeVector,inputStringArray);
                    instanceVector.add(instance);
                }
            }
            bufferedReader.close();
            fileReader.close();
        }
        catch (Exception exception){
            System.out.println(exception);
        }
    }
    public void printData(String outputFileName){
        try{
            File file = new File(outputFileName);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int i = 0; i<instanceVector.size();i++){
               // System.out.println(instanceVector.get(i).getInfo());
                bufferedWriter.write(instanceVector.get(i).getInfo()+"\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public int getNumberOfValueOfAttribute(int i){
        return attributeVector.get(i).getNumberOfValue();
    }

    public Vector<Instance> getInstanceVector() {
        return instanceVector;
    }

    public Vector<Attribute> getAttributeVector() {
        return attributeVector;
    }
}
