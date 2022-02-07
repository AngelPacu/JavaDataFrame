package dataFrames;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Directory implements DataFrame {
    String name;
    List<DataFrame> children; //DataFrame list.

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
        return row>=this.size() ? "Index not valid, element not found"  // If the row exceeds the total size of the DF/Directory, it will be an error.
                : atAux(row,column,0); }                          // If not, we call the aux function.
    
    private Object atAux(int row, String column, int actual) { // Double ternary
        return children.get(actual).getCategories().contains(column) ?  // If the current position has the indicated column, we will perform the following ternary.
                children.get(actual).size() <= row ?
                        atAux(row - children.get(actual).size(), column, actual+1) :     // If the size of the DF is smaller or equal to the row, we reduce the row number according to the size of the visited DF.
                        children.get(actual).at(row, column) :                                      // If not, we have found the position we are looking for.
                atAux(row , column, actual+1);                  // If not, we will call recursively looking for the next position in the Directory,

    }

    @Override
    public Object iat(int row, int column) {
        return row>=this.size() && column>=this.getCategories().size() ? // If the row exceeds the total size of the DF/Directory, it will be an error.
                "Index not valid, element not found" :
                atAux(row,this.getCategories().get(column),0);                // If not, call the function atAux, Since we perform the same algorithm
    }

    /*private Object iatAux(int row, int column, int actual) {
        return children.get(actual).size() <= row ?
                iatAux(row - children.get(actual).size(), column, actual+1) :
                children.get(actual).iat(row, column);
    }*/

    @Override
    public int columns() {
        return children.stream().mapToInt(DataFrame::columns).sum(); // We map all the columns to int and call the ".sum" function that allows us the streams.
    }

    @Override
    public int size() {
        return children.stream().mapToInt(DataFrame::size).sum();   // We map all the rows to int and call the ".sum" function that allows us the streams.
    }

    @Override
    public List<Object> sort(String column, Comparator<Object> objectComparator) { // We order all the elements of a directory and not only the elements of each dataframe.
        return children.stream().map(df -> df.sort(column, objectComparator)).
                reduce(new ArrayList<>(), (x, y)-> { // For each DataFrame in the directory, we perform a sort to order it and move it from the list, reduce it from the directory
                    x.addAll(y);                     //  And add it to a new list.
                    x.sort(objectComparator);        // We sort the new list.
                    return x;});
    }

    @Override
    public List<Object> query(String column, Predicate<Object> predicate) {
        return children.stream().map(df -> df.query(column, predicate)). // For each DF we perform the query
                reduce(new ArrayList<>(), (x, y)-> {                     // And reduce it from the directory
                    x.addAll(y);                                         // And add the result to a new list.
                return x;});
    }

    @Override
    public Map<String, List<Object>> extendedQuery(String column, Predicate<Object> predicate) { // Returns all elements in the row that meet the condition.
        return children.parallelStream().map(x->x.extendedQuery(column,predicate)). // For each DF we perform the query
                reduce(new HashMap<>(), (x, y)-> {
                    y.forEach((k,v)->x.merge(k,v, (l1, l2)->{       // For each DF treated we will reduce it from the direct and and if it meets the condition,
                        l1.addAll(l2);                              // We will add all the elements of the row.
                        return l1;}));
                    return x;});
    }

    @Override
    public List<String> getCategories() {
        /*ArrayList<String> total = new ArrayList<>();
        children.stream().map(DataFrame::getCategories).reduce((result, x) -> x).ifPresent(total::addAll);
        return total;*/
        return children.parallelStream().map(DataFrame::getCategories).
                reduce(new ArrayList<>(), (x, y)-> {x.addAll(y);return x;}). // For each DF, we reduce the list and obtain its keys.
                        stream().distinct().collect(Collectors.toList());   // In case we already have them, we do not add them using the ".Distinct".
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
    public Iterator<List<Object>> iterator() { return new FileDF(this.getData(), this.getCategories()).iterator(); }; //Iterate only categories.
    }


