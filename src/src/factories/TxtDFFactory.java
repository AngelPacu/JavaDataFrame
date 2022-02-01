package factories;

import dataFrames.DataFrame;
import dataFrames.FileDF;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Carga del fichero <b>.txt</b>
 */
public class TxtDFFactory implements DataFrameFactory {

    @Override
    public DataFrame frame(File input, String... delim) throws FileNotFoundException {
        Scanner sc = new Scanner(input);
        sc.useDelimiter(delim.length > 0 ? delim[0] : "\\n");

        Map<String, List<Object>> mapList = new HashMap<>();
        ArrayList<String> categories = new ArrayList<>();

        if (sc.hasNext()) {
            for (String cat : sc.next().replaceAll("[ \"\\r]", "").split(delim.length > 1 ? delim[1] : ",")) {
                categories.add(cat);
                mapList.put(cat, new ArrayList<>());
            }
        }
        while (sc.hasNext()) {
            String[] row = sc.next().replaceAll("[ \"\\r]", "").split(delim.length > 1 ? delim[1] : ",");
            for (int i = 0; i < row.length; i++) {
                if (!row[i].isEmpty()) {
                    try {
                        mapList.get(categories.get(i)).add(Long.parseLong(row[i]));
                    } catch (NumberFormatException e) {
                        mapList.get(categories.get(i)).add(row[i]);
                    }
                }
            }
        }
        sc.close();
        return new FileDF(mapList, categories);
    }
}
