package factories;

import dataFrames.CsvDF;
import dataFrames.DataFrame;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;


public class CsvDFFactory implements DataFrameFactory {
    @Override
    public DataFrame frame(File input) throws FileNotFoundException {


        Scanner sc = new Scanner(input);
        sc.useDelimiter(",");   //sets the delimiter pattern
        while (sc.hasNext())  //returns a boolean value
        {
            //Create DF
        }
        sc.close();
        return new CsvDF();
    }
}
