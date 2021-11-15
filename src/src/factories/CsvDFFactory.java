package factories;

import dataFrames.CsvDF;
import dataFrames.DataFrame;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class CsvDFFactory implements DataFrameFactory {
    @Override
    public DataFrame frame(File input) throws FileNotFoundException {

        Scanner line = new Scanner(input);
        line.useDelimiter(",|\\r|\\n");   //Don't work with writespace
        //Map<String, List<Object>> mapList = new HashMap<String,List<Object>>(); //Key -> Diferentes columnas, Value -> ArrayList con todos los valores de la columna.
        while (line.hasNextLine())  //returns a boolean value
        {
            String imprimir = line.next();
            //String array[] = imprimir.split("\"");        //Problemon, cuando separa las comillas doble, no imprime valor y el siguiente print peta.
            //System.out.println(array[1]);
            System.out.println(imprimir);
            //Create DF
            //mapList.put(sc.next(),null);
        }
        line.close();
        return new CsvDF();
    }
}
