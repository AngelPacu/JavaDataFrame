package dataFrames;

import java.util.*;

public class JsonDF implements DataFrame{

    Map<String, List<Object>> data;
    ArrayList<String> categories;

    public JsonDF(Map<String, List<Object>> mapList, ArrayList<String> cat) {
        data=mapList;
        categories=cat;
    }

    @Override
    public Object at(int row, String column) {
        return data.get(column).get(row);
    }

    @Override
    public Object iat(int row, int column) {
        return at(row,categories.get(column));
    }

    @Override
    public int columns() {
        return data.size();
    }

    @Override
    public int size() {
        /* Esto es lo mismo que ese return.
        Collection<List<Object>> listaPrueba = data.values();
        return listaPrueba.size();
         */
        return data.values().size();

        //return data.get("LatD").size();
    }

    @Override
    public int[] sort(String column, Comparator<Integer> integerComparator) {
        List<Object> lista = data.get(column);
        //lista.sort();       //Como comparamos las letras?
        return new int[0];
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }
}
