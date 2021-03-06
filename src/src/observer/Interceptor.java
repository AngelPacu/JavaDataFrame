package observer;

import dataFrames.DataFrame;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * It is our dynamic proxy that will implement the DF functions and for each function executed,
 * it will pass through the observer class and execute the function indicated by parameter.
 */
public class Interceptor implements DataFrame{
    DataFrame df;
    Observer editor;

    /**
     * Proxy Constructor.
     * @param df: Dataframe
     * @param observer: It will have 2 events and a list of clients.
     */
    public Interceptor(DataFrame df, Observer observer) {
        this.df = df;
        editor=observer;
    }
    // All functions will invoke the DATA FRAME function
    // And the only difference is that the editor will notify the clients that are assigned to the event.

    @Override
    public Object at(int row, String column) {
        editor.notifySubscribers("any");
        return df.at(row, column);
    }

    @Override
    public Object iat(int row, int column) {
        editor.notifySubscribers("any");
        return df.iat(row,column);
    }

    @Override
    public int columns() {
        editor.notifySubscribers("any");
        return df.columns();
    }

    @Override
    public int size() {
        editor.notifySubscribers("any");
        return df.size();
    }

    @Override
    public List<Object> sort(String column, Comparator<Object> comparator) {
        editor.notifySubscribers("any");
        return df.sort(column,comparator);
    }

    @Override
    public List<Object> query(String column, Predicate<Object> predicate) {
        editor.notifySubscribers("any");
        editor.notifySubscribers("query");
        return df.query(column,predicate);
    }

    @Override
    public Map<String, List<Object>> extendedQuery(String column, Predicate<Object> predicate) {
        editor.notifySubscribers("query");
        editor.notifySubscribers("any");
        return df.extendedQuery(column,predicate);
    }

    @Override
    public List<String> getCategories() {
        editor.notifySubscribers("any");
        return df.getCategories();
    }

    @Override
    public Map<String, List<Object>> getData() {
        editor.notifySubscribers("any");
        return df.getData();
    }

    @Override
    public Iterator<List<Object>> iterator() {
        editor.notifySubscribers("any");
        return df.iterator();
    }
}
