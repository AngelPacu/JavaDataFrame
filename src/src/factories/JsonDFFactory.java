package factories;

import dataFrames.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import dataFrames.JsonDF;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonDFFactory implements DataFrameFactory {

    @Override
    public DataFrame frame(File input) throws IOException, ParseException {

        HashMap<String, List<Object>> mapList = new HashMap<>();
        ArrayList<String> categories = new ArrayList<>();
        FileReader reader = new FileReader(input);                      //JsonParse allows us to read the JsonFile.
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);     //We need to cast it in an array to iterate it.

        for (int i = 0; i<jsonArray.size(); i++){
            JSONObject objectFile = (JSONObject) jsonArray.get(i);          //A JSONObject is a list
            Iterator<String> keys = objectFile.keySet().iterator();         //To iterate the keys.
            Iterator<Object> values = objectFile.values().iterator();       //To iterate the values.
            String key;

            while (values.hasNext()){
                List<Object> listValues = new ArrayList<>();
                key = keys.next();
                if(i==0) categories.add(key);                           //Only add all keys 1 time in the arrayList.
                if(mapList.containsKey(key)) listValues = (mapList.get(key));   //Si tenemos la key en la mapList, añadiremos en la lista el valor del objeto con esa clave.
                listValues.add(values.next());
                mapList.put(key,listValues);                            //Add to mapList a new Values.
            }
        }
        return new JsonDF(mapList, categories);
    }


}
