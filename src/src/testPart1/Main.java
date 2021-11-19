package testPart1;

import dataFrames.DataFrame;
import factories.AbstractFactory;
import factories.DataFrameFactory;
import org.json.simple.parser.ParseException;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        File input = new File("dataFiles/cities.json");
        DataFrameFactory factory = AbstractFactory.create(input);
        try {
            DataFrame dataFile = factory.frame(input);
            System.out.println(dataFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
