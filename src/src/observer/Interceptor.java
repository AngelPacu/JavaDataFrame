package observer;

import dataFrames.DataFrame;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Interceptor implements DataFrame{
    DataFrame df;


    public Interceptor(DataFrame df) {
        this.df = df;
    }


    @Override
    public Object at(int row, String column) {

        return df.at(row, column);
    }

    @Override
    public Object iat(int row, int column) {
        return df.iat(row,column);
    }

    @Override
    public int columns() {
        return df.columns();
    }

    @Override
    public int size() {
        return df.size();
    }

    @Override
    public List<Object> sort(String column, Comparator<Object> comparator) {
        return df.sort(column,comparator);
    }

    @Override
    public List<Object> query(String column, Predicate<Object> predicate) {
        return df.query(column,predicate);
    }

    @Override
    public Map<String, List<Object>> extendedQuery(String column, Predicate<Object> predicate) {
        return df.extendedQuery(column,predicate);
    }

    @Override
    public List<String> getCategories() {
        return df.getCategories();
    }

    @Override
    public Map<String, List<Object>> getData() {
        return df.getData();
    }

    @Override
    public Iterator<List<Object>> iterator() {
        return df.iterator();
    }
}
