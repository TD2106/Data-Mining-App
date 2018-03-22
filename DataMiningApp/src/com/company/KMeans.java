package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Vector;

/**
 * Created by Duy Le on 8/14/2017.
 */
public class KMeans {
    private int k;
    private int instanceSize;
    private double[][] KMeanArray,Prev;
    Vector<Instance> instanceVector;
    public KMeans(int k,Instances instances){
        this.k = k;
        this.instanceVector = instances.getInstances();
        this.instanceSize = this.instanceVector.get(0).getSize();
        KMeanArray = new double[k][instances.getInstanceSize()];
        Prev = new double[k][instances.getInstanceSize()];
    }
    public void processKMean(){
        for(int i=0;i<k;i++){
            for(int j=0;j<instanceSize;j++){
                KMeanArray[i][j] = (double)instanceVector.get(i).getAttributeInstance(j).getIntValue();
            }
        }
        Vector<Instance>[] cluster = new Vector[k];
        while(!stopCheck()){
            for(int i=0;i<k;i++){
                for(int j=0;j<instanceSize;j++){
                    Prev[i][j]=KMeanArray[i][j];
                }
            }
            double[][] distance = new double[k][instanceVector.size()];
            for(int i=0;i<k;i++) {
                for (int j = 0; j < instanceVector.size(); j++) {
                    double result = 0;
                    for (int l = 0; l < instanceSize; l++) {
                        result += (double) (KMeanArray[i][l] - (double) instanceVector.get(j).getAttributeInstance(l).getIntValue()) * (KMeanArray[i][l] - (double) instanceVector.get(j).getAttributeInstance(l).getIntValue());
                    }
                    distance[i][j] = Math.sqrt(result);
                }
            }

            for(int i=0;i<k;i++) cluster[i] = new Vector<>();
            for(int i=0;i<k;i++) cluster[i].clear();
            for(int i=0;i<instanceVector.size();i++){
                int minIndex = 0;
                double min = 1e9;
                for(int j=0;j<k;j++){
                    if(distance[j][i]<min){
                        min = distance[j][i];
                        minIndex = j;
                    }
                }
                cluster[minIndex].add(instanceVector.get(i));
            }
            for(int i=0;i<k;i++){
                for(int l=0;l<instanceSize;l++){
                    double result = 0;
                    for(int j=0;j<cluster[i].size();j++){
                        result+=(double)cluster[i].get(j).getAttributeInstance(l).getIntValue();
                    }
                    if(result!=0) result /= (double) cluster[i].size();
                    KMeanArray[i][l] = result;
                }
            }
        }
        try{
            File file = new File("KMeans");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int i = 0; i<k;i++){
                bufferedWriter.write("Cluster "+i+": \n");
                for(int j=0;j<cluster[i].size();j++){
                    bufferedWriter.write(cluster[i].get(j).getInfo()+"\n");
                }
            }
            bufferedWriter.close();
            fileWriter.close();
        }
        catch (Exception e){
            System.out.println("Error writing file");
        }
    }
    private boolean stopCheck(){
        for(int i=0;i<k;i++){
            for(int j=0;j<instanceSize;j++){
                if(KMeanArray[i][j] !=  Prev[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
