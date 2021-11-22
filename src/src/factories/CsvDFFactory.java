package factories;

import dataFrames.CsvDF;
import dataFrames.DataFrame;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.*;


public class CsvDFFactory implements DataFrameFactory {
    @Override
    public DataFrame frame(File input) throws FileNotFoundException {

        Scanner sc = new Scanner(input);
        sc.useDelimiter("\\n");

        Map<String, List<Object>> mapList = new HashMap<>();
        ArrayList<String> categories = new ArrayList<>();

        if(sc.hasNext()){
            for (String cat:sc.next().replaceAll("[ \"]","").split(",")) {
                categories.add(cat);
                mapList.put(cat,new ArrayList<>());
            }
        }
        while (sc.hasNext()) {
            Object[] row= sc.next().replaceAll("[ \"]","").split(",");
            for (int i = 0; i < row.length; i++) {
                mapList.get(categories.get(i)).add(row[i]);
            }
        }
        sc.close();
        return new CsvDF(mapList, categories);
    }
}