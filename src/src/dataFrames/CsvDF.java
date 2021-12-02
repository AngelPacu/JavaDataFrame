package dataFrames;

import java.util.*;

public class CsvDF extends AbstractDF{
    Map<String, List<Object>> data;
    ArrayList<String> categories;
    public CsvDF(Map<String, List<Object>> mapList, ArrayList<String> cat) {
        data=mapList;
        categories=cat;
    }
}
