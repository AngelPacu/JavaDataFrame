package factories;

import dataFrames.DataFrame;
import dataFrames.FileDF;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Loading of the <b>.json</b> file.
 */
public class JsonDFFactory implements DataFrameFactory {

    @Override
    public DataFrame frame(File input, String... delim) throws IOException, ParseException {

        HashMap<String, List<Object>> mapList = new HashMap<>();
        ArrayList<String> categories = new ArrayList<>();
        FileReader reader = new FileReader(input);                      //JsonParse allows us to read the JsonFile.
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);     //We need to cast it in an array to iterate it.

        JSONObject first = (JSONObject) jsonArray.get(0);
        for (Object key: first.keySet()) {
            mapList.put((String) key, new ArrayList<>());               //Creates an empty ArrayList for each category
            categories.add((String) key);
        }

        for (Object o : jsonArray) {
            JSONObject row = (JSONObject) o;          //A JSONObject is a list
            for (int j = 0; j < row.size(); j++) {
                mapList.get(categories.get(j)).add(row.get(categories.get(j)));
            }
        }
        return new FileDF(mapList, categories);
    }


}
