package testPart1;

import dataFrames.*;
import factories.*;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;


public class Main {
    public static void main(String[] args)  {

        File input = new File("dataFiles/cities.json");
        DataFrameFactory factory = AbstractFactory.create(input);
        try {
            DataFrame dataFile = factory.frame(input);
            System.out.println(dataFile.size());
            System.out.println(dataFile.sort("LatD", (x, y) -> Long.compare((Long) x, (Long) y)));
            System.out.println(dataFile.sort("State", (x, y) -> ((String) x).compareTo((String)y)));
            Predicate<Object> pruebaString = (x) -> x.equals("OH");

            System.out.println(dataFile.query("LatD",(x) -> (Long)x>48));
            //Predicate<Long> prueba = dataFile.iterator().;


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
