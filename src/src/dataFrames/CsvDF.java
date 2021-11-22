package dataFrames;

import java.util.*;

public class CsvDF implements DataFrame{

    Map<String, List<Object>> data;
    ArrayList<String> categories;
    public CsvDF(Map<String, List<Object>> mapList, ArrayList<String> cat) {
        data=mapList;
        categories=cat;
    }

    @Override
    public Object at(int row, String column) {
        return 0;
    }

    @Override
    public Object iat(int row, int column) {
        return 0;
    }

    @Override
    public int columns() {
        return 0;
    }

    @Override
    public int size() {

        return size();
    }

    @Override
    public int[] sort(String column, Comparator<Integer> integerComparator) {
        return new int[0];
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public String toString() {
        return "CsvDF{" +
                "data=" + data +
                '}';
    }
}
