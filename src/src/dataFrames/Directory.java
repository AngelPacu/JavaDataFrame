package dataFrames;

import java.util.*;
import java.util.function.Predicate;

public class Directory implements DataFrame {
    String name;
    List<DataFrame> children;

    public Directory(String name) {
        this.name = name;
        children = new LinkedList<DataFrame>();
    }

    public void addDataFrame(DataFrame child) {
        children.add(child);
    }

    public void removeDataFrame(DataFrame child){
        children.remove(child);
    }

    @Override
    public Object at(int row, String column) {
        Object pruebas = null;
        //children.forEach(x-> x.at(row,column));

        for (DataFrame df:children)
            pruebas = df.at(row,column);
        return pruebas;
    }

    @Override
    public Object iat(int row, int column) {
        return null;
    }

    @Override
    public int columns() {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public List<Object> sort(String column, Comparator<Object> integerComparator) {
        return null;
    }

    @Override
    public Map<String, List<Object>> query(String column, Predicate<Object> predicado) {
        return null;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }
}
