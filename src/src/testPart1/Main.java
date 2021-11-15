package testPart1;

import dataFrames.DataFrame;
import factories.AbstractFactory;
import factories.DataFrameFactory;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;


public class Main {
    public static void main(String[] args) {

        File input = new File("dataFiles/cities.csv");
        DataFrameFactory factory = AbstractFactory.create(input);
        try {
            DataFrame dataFile = factory.frame(input);
            System.out.println(dataFile.at(1,"Hola"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }








    }
}
