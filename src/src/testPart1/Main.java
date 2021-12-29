package testPart1;

import dataFrames.CsvDF;
import dataFrames.DataFrame;
import dataFrames.Directory;
import factories.AbstractFactory;
import factories.DataFrameFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.function.Predicate;


public class Main {

    public DataFrame readFile(){
        File input = new File("dataFiles/cities.json");
        DataFrameFactory factory = AbstractFactory.create(input);
        DataFrame dataFile = null;
        try {
            dataFile = factory.frame(input);
        } catch (Exception e) {
            System.out.println("Error en la creación:"+e);
        }
        return dataFile;
    }

    @Test
    public void part1(){
        DataFrame dataFile = readFile();
        System.out.println(dataFile);
        System.out.println("Size: "+dataFile.size());
        System.out.println("Columns: "+dataFile.columns());
        System.out.println(dataFile.at(23,"F"));
        System.out.println(dataFile.at(23,"LatD"));
        System.out.println(dataFile.iat(23,5));
        System.out.println(dataFile.sort("LatD", (x, y) -> Long.compare((Long) x, (Long) y)));
        System.out.println(dataFile.sort("State", (x, y) -> ((String) x).compareTo((String)y)));
        System.out.println(dataFile.query("LatD",(x) -> (Long)x>48));
        System.out.println(dataFile.extendedQuery("LatD",(x) -> (Long)x>48));
    }

    @Test
    public void part2(){
        DataFrame dataFile = readFile();
        System.out.println(dataFile);
        Directory directoriProva= new Directory("Arrel");
        directoriProva.addDataFrame(dataFile);
        directoriProva.addDataFrame(new CsvDF(dataFile.extendedQuery("LatD", (x) -> (Long)x>48), (ArrayList<String>) dataFile.getCategories()));
        System.out.println(directoriProva.extendedQuery("LatD",(x) -> (Long)x>48));
        System.out.println(directoriProva.getCategories());
    }
}
