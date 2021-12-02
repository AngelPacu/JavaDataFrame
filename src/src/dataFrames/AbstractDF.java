package dataFrames;

import java.util.*;
import java.util.stream.Collectors;

public abstract  class AbstractDF implements DataFrame {
    Map<String, List<Object>> data;
    ArrayList<String> categories;


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
        return data.get(categories.get(0)).size();
    }

    @Override
    public List<Object> sort(String column, Comparator<Object> comparator) {
        List<Object> lista = data.get(column);
        lista = lista.stream().sorted(comparator).collect(Collectors.toList());
        return lista;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }
}
