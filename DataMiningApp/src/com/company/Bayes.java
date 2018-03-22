package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Vector;

/**
 * Created by Duy Le on 8/14/2017.
 */
public class Bayes {
    private Vector<Instance> instanceVector;
    private Vector<Attribute> attributeVector;
    private int instanceSize;
    private int numberOfClassAttributeValue;
    public Bayes(Instances instances){
        this.attributeVector = instances.getAttributeVector();
        this.instanceVector = instances.getInstanceVector();
        instanceSize = instanceVector.get(0).getSize();
    }
    public void processBayes(int attributeIndex){
        Attribute classAttribue = attributeVector.get(attributeIndex);
        numberOfClassAttributeValue = classAttribue.getNumberOfValue();
        double[] probabilityClassAttribute = new double[numberOfClassAttributeValue];
        double[][][] probabilityAttribute = new double[instanceSize][numberOfClassAttributeValue][];
        int[] totalNumOfClassAttributeValue = new int[numberOfClassAttributeValue];
        for(int i=0;i<instanceVector.size();i++){
            probabilityClassAttribute[instanceVector.get(i).getAttributeInstance(attributeIndex).getIntValue()]++;
            totalNumOfClassAttributeValue[instanceVector.get(i).getAttributeInstance(attributeIndex).getIntValue()]++;
        }
        for(int i=0;i<classAttribue.getNumberOfValue();i++){
            probabilityClassAttribute[i] /= (double)instanceVector.size();
        }
        for(int i=0;i<instanceSize;i++){
            if(i==attributeIndex) continue;
            else{
                for(int j=0;j<numberOfClassAttributeValue;j++){
                    probabilityAttribute[i][j] = new double[attributeVector.get(i).getNumberOfValue()];
                }
                for(int j=0;j<instanceVector.size();j++){
                    Instance temp = instanceVector.get(j);
                    probabilityAttribute[i][temp.getAttributeInstance(attributeIndex).getIntValue()][temp.getAttributeInstance(i).getIntValue()]++;
                }
                for(int j=0;j<attributeVector.get(i).getNumberOfValue();j++){
                    for(int k=0;k<numberOfClassAttributeValue;k++){
                        probabilityAttribute[i][k][j]/=(double)totalNumOfClassAttributeValue[k];
                    }
                }
            }
        }
        try{
            File file = new File("Bayes");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.format("%-20s",""));
            for(int i=0;i<numberOfClassAttributeValue;i++){
                stringBuilder.append(String.format("%-20s",attributeVector.get(attributeIndex).getStringValue(i)));
            }
            stringBuilder.append("\n");
            for(int i=0;i<instanceSize;i++){
                if(i==attributeIndex) continue;
                else{
                    stringBuilder.append(attributeVector.get(i).getName()+"\n");
                    for(int j=0;j<attributeVector.get(i).getNumberOfValue();j++){
                        stringBuilder.append(String.format("%-20s",attributeVector.get(i).getStringValue(j)));
                        for(int k=0;k<numberOfClassAttributeValue;k++){
                            stringBuilder.append(String.format("%-20f",probabilityAttribute[i][k][j]));
                        }
                        stringBuilder.append("\n");
                    }
                    stringBuilder.append("\n");
                }
            }
            bw.write(stringBuilder.toString());
            bw.close();
            fw.close();
        }
        catch (Exception exception){
            System.out.println(exception);
        }
    }
}
