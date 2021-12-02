package dataFrames;

import java.util.*;

public class TxtDF extends AbstractDF{
    Map<String, List<Object>> data;
    ArrayList<String> categories;
    public TxtDF(Map<String, List<Object>> mapList, ArrayList<String> cat) {
        data=mapList;
        categories=cat;
    }

}
