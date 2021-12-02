package testPart1;

import dataFrames.DataFrame;
import factories.AbstractFactory;
import factories.DataFrameFactory;

import java.io.File;


public class Main {
    public static void main(String[] args)  {

        File input = new File("dataFiles/cities.json");
        DataFrameFactory factory = AbstractFactory.create(input);
        try {
            DataFrame dataFile = factory.frame(input);
            System.out.println(dataFile.size());
            System.out.println(dataFile.sort("LatD", (x, y) -> Long.compare((Long) x, (Long) y)));
            System.out.println(dataFile.sort("State", (x, y) -> ((String) x).compareTo((String)y)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
