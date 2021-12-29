package factories;

import dataFrames.DataFrame;
import dataFrames.TxtDF;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Carga del fichero <b>.txt</b>
 */
public class TxtDFFactory implements DataFrameFactory {

    @Override
    public DataFrame frame(File input) throws FileNotFoundException {

        Scanner sc = new Scanner(input);
        sc.useDelimiter("\\n");
        Map<String, List<Object>> mapList = new HashMap<>();
        ArrayList<String> categories = new ArrayList<>();

        for (String cat : sc.nextLine().replaceAll("[ \"]", "").split(";")) {       //Split sirve para ignorar espacios, este for solo recorre la primera linea.
            categories.add(cat);
            mapList.put(cat,new ArrayList<Object>());
        }

        while (sc.hasNext()) {
            String[] row = sc.nextLine().replaceAll("[ \"]","").split(";");
            for (int i = 0; i < row.length; i++) {
                mapList.get(categories.get(i)).add(row[i]);
            }
        }
        sc.close();
        return new TxtDF(mapList,categories );
    }
}
