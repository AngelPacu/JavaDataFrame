package factories;

import dataFrames.CsvDF;
import dataFrames.DataFrame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import dataFrames.JsonDF;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonDFFactory implements DataFrameFactory {
    @Override
    public DataFrame frame(File input) throws IOException, ParseException {

        FileReader reader = new FileReader(input);
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
        HashMap<String, List<Object>> listaMap = new HashMap();
        for (int i = 0; i<jsonArray.size(); i++){
            JSONObject objeto = (JSONObject) jsonArray.get(i);
            System.out.println(objeto); //Sacar valores a partir de esto
        }










        return null;
    }


}
