package com.company;

public class Main {

    public static void main(String[] args) {
	    Instances instances = new Instances();
	    instances.getInput("contact-lenses.arff");
	    instances.printData("outRawData");
	    KMeans kMeans = new KMeans(3,instances);
	    kMeans.processKMean();
	    Bayes bayes = new Bayes(instances);
	    bayes.processBayes(2);
    }
}
