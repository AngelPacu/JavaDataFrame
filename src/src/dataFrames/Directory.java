package dataFrames;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Directory implements DataFrame {
    String name;
    List<DataFrame> children;

    public Directory(String name) {
        this.name = name;
        children = new LinkedList<>();
    }

    public void addDataFrame(DataFrame child) {
        children.add(child);
    }

    public void removeDataFrame(DataFrame child) {
        children.remove(child);
    }

    @Override
    public Object at(int row, String column) {
        int actual = 0;
        return children.get(actual).size() > row ?
                children.get(actual++).at(row - children.get(actual).size(), column) :
                children.get(actual).at(row, column);
    }

    @Override
    public Object iat(int row, int column) {
        int actual = 0;
        return children.get(actual).size() > row ?
                children.get(actual++).iat(row - children.get(actual).size(), column) :
                children.get(actual).iat(row, column);
    }

    @Override
    public int columns() {
        return children.stream().mapToInt(DataFrame::columns).sum();
    }

    @Override
    public int size() {
        return children.stream().mapToInt(DataFrame::size).sum();
    }

    @Override
    public List<Object> sort(String column, Comparator<Object> integerComparator) {
        return children.stream().map(df -> df.sort(column, integerComparator)).collect(Collectors.toList());
        //comprovar esto, sino cambiar por el reduce de abajo
    }

    @Override
    public List<Object> query(String column, Predicate<Object> predicate) {
        return children.stream().map(df -> df.query(column, predicate)).reduce(new ArrayList<>(), (x, y)-> {x.addAll(y);return x;});
    }

    @Override
    public Map<String, List<Object>> extendedQuery(String column, Predicate<Object> predicate) {
        return children.stream().map(x->x.extendedQuery(column,predicate)).
                reduce(new HashMap<>(), (x, y)-> {
                    y.forEach((k,v)->x.merge(k,v, (l1, l2)->{
                        l1.addAll(l2);
                        return l1;}));
                    return x;});
    }

    @Override
    public List<String> getCategories() {
        /*ArrayList<String> total = new ArrayList<>();
        children.stream().map(DataFrame::getCategories).reduce((result, x) -> x).ifPresent(total::addAll);
        return total;*/

        return children.stream().map(DataFrame::getCategories).
                reduce(new ArrayList<>(), (x, y)-> {x.addAll(y);return x;}).
                stream().distinct().collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Object>> getData() {
        return children.stream().map(DataFrame::getData).
                reduce(new HashMap<>(), (x, y)-> {
                    y.forEach((k,v)->x.merge(k,v, (l1, l2)->{
                        l1.addAll(l2);
                        return l1;}));
                    return x;});
    }

    @Override
    public Iterator<List<Object>> iterator() {
        return new CsvDF(this.getData(), this.getCategories()).iterator();
        };
    }

