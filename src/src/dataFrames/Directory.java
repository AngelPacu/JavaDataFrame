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
    public Object at(int row, String column) { return row>=this.size() ? "Index not valid, element not found" : atAux(row,column,0); }
    
    private Object atAux(int row, String column, int actual) {
        return children.get(actual).getCategories().contains(column) ?
                children.get(actual).size() <= row ?
                        atAux(row - children.get(actual).size(), column, actual+1) :
                        children.get(actual).at(row, column) :
                atAux(row , column, actual+1);

    }

    @Override
    public Object iat(int row, int column) {
        return row>=this.size() && column>=this.getCategories().size() ?
                "Index not valid, element not found" :
                at(row,this.getCategories().get(column));
    }

    /*private Object iatAux(int row, int column, int actual) {
        return children.get(actual).size() <= row ?
                iatAux(row - children.get(actual).size(), column, actual+1) :
                children.get(actual).iat(row, column);
    }*/

    @Override
    public int columns() {
        return children.stream().mapToInt(DataFrame::columns).sum();
    }

    @Override
    public int size() {
        return children.stream().mapToInt(DataFrame::size).sum();
    }

    @Override
    public List<Object> sort(String column, Comparator<Object> objectComparator) {
        return children.stream().map(df -> df.sort(column, objectComparator)).
                reduce(new ArrayList<>(), (x, y)-> {
                    x.addAll(y);
                    x.sort(objectComparator);
                    return x;});
    }

    @Override
    public List<Object> query(String column, Predicate<Object> predicate) {
        return children.stream().map(df -> df.query(column, predicate)).
                reduce(new ArrayList<>(), (x, y)-> {
                    x.addAll(y);
                    return x;});
    }

    @Override
    public Map<String, List<Object>> extendedQuery(String column, Predicate<Object> predicate) {
        return children.parallelStream().map(x->x.extendedQuery(column,predicate)).
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

        return children.parallelStream().map(DataFrame::getCategories).
                reduce(new ArrayList<>(), (x, y)-> {x.addAll(y);return x;}).
                stream().distinct().collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Object>> getData() {
        return children.parallelStream().map(DataFrame::getData).
                reduce(new HashMap<>(), (x, y)-> {
                    y.forEach((k,v)->x.merge(k,v, (l1, l2)->{
                        l1.addAll(l2);
                        return l1;}));
                    return x;});
    }

    @Override
    public String toString(){
        String result = name+":\n";
        for (DataFrame child:children){
            result = result.concat("\t-Child "+children.indexOf(child)+":\n"+child.toString()+"\n");
        }
        return result;
    }

    @Override
    public Iterator<List<Object>> iterator() { return new FileDF(this.getData(), this.getCategories()).iterator(); };
    }


