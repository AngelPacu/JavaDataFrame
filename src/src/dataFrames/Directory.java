package dataFrames;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * File of the Composite pattern, implements functions recursively for its children
 */
public class Directory implements DataFrame {
    String name;
    List<DataFrame> children;

    /**
     * Constructor method of the directory
     * @param name: Directory's name
     */
    public Directory(String name) {
        this.name = name;
        children = new LinkedList<>();
    }

    /**
     * Adds a new child
     * @param child: DataFrame that is added to the directory, either FileDf or Directory
     */
    public void addDataFrame(DataFrame child) {
        children.add(child);
    }

    /**
     * Removes a child from the directory
     * @param child: DataFrame to be removed
     */
    public void removeDataFrame(DataFrame child) {
        children.remove(child);
    }

    /**
     * This implementation uses recursion also treats columns with the same label in different
     * Dataframes as the same column, adding the number of the rows.
     */
    @Override
    public Object at(int row, String column) {
        return row>=this.size() ? "Index not valid, element not found"  // If the row exceeds the total size of the Directory, it will be an error.
                : atAux(row,column,0); }                          // If not, we call the aux recursive function.
    
    private Object atAux(int row, String column, int actual) {          // Double ternary
        return children.get(actual).getCategories().contains(column) ?  // If the current position has the desired column, we will perform the following ternary.
                children.get(actual).getData().get(column).size() <= row ?                                               // If the size of the DF is smaller or equal to the row,
                        atAux(row - children.get(actual).getData().get(column).size(), column, actual+1) :    // we reduce the row number according to the size of the visited DF.
                        children.get(actual).at(row, column) :                                                           // Else, we have found the position we are looking for.
                atAux(row , column, actual+1);                    // Else, we will call recursively looking for the next child in the Directory.
    }

    /**
     * Searches for the label corresponding to the column and reuses the auxiliary function
     */
    @Override
    public Object iat(int row, int column) {
        return row>=this.size() && column>=this.getCategories().size() ?    // If the row exceeds the total size of the Directory, it will be an error.
                "Index not valid, element not found" :
                atAux(row,this.getCategories().get(column),0);        // Else, call the function atAux, Since we perform the same algorithm
    }

    @Override
    public int columns() {
        return children.stream().mapToInt(DataFrame::columns).sum(); // We map all the columns to int by the local method and accumulate the result.
    }

    @Override
    public int size() {
        return children.stream().mapToInt(DataFrame::size).sum();   // We map all the rows to int by the local method and accumulate the result.
    }

    @Override
    public List<Object> sort(String column, Comparator<Object> objectComparator) { // We sort all the elements of the directory and not only the elements of each dataframe.
        return children.stream().map(df -> df.sort(column, objectComparator)).
                reduce(new ArrayList<>(), (x, y)-> { // For each DataFrame in the directory, we obtain the sorted label,
                    x.addAll(y);                     // Accumulate by combining both lists
                    x.sort(objectComparator);        // We sort the new list (was already partially sorted).
                    return x;});
    }

    @Override
    public List<Object> query(String column, Predicate<Object> predicate) {
        return children.stream().map(df -> df.query(column, predicate)). // For each DF we perform the query and
                reduce(new ArrayList<>(), (x, y)-> {                     // reduce it by combining both lists
                    x.addAll(y);
                return x;});
    }

    @Override
    public Map<String, List<Object>> extendedQuery(String column, Predicate<Object> predicate) {
        return children.parallelStream().map(x->x.extendedQuery(column,predicate)). // For each DF we perform the query
                reduce(new HashMap<>(), (x, y)-> {
                    y.forEach((k,v)->x.merge(k,v, (l1, l2)->{       // For each DF treated we will merge it into a new map,
                        l1.addAll(l2);                              // Rows with the same label will be concatenated
                        return l1;}));
                    return x;});
    }

    @Override
    public List<String> getCategories() {
        /*ArrayList<String> total = new ArrayList<>();              //Alternative implementation
        children.stream().map(DataFrame::getCategories).reduce((result, x) -> x).ifPresent(total::addAll);
        return total;*/
        return children.parallelStream().map(DataFrame::getCategories).
                reduce(new ArrayList<>(), (x, y)-> {x.addAll(y);return x;}). // For each DF, we reduce the list and obtain its keys.
                        stream().distinct().collect(Collectors.toList());    // We will also delete the duplicates
    }

    /**
     * Method to get the merged data of all the children
     * @return a merged map
     */
    @Override
    public Map<String, List<Object>> getData() {
        return children.parallelStream().map(DataFrame::getData).
                reduce(new HashMap<>(), (x, y)-> {
                    y.forEach((k,v)->x.merge(k,v, (l1, l2)->{
                        l1.addAll(l2);
                        return l1;}));
                    return x;});
    }

    public List<DataFrame> getChildren(){
        return List.copyOf(children);
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
    public Iterator<List<Object>> iterator() { return new FileDF(this.getData(), this.getCategories()).iterator(); } //Iterates over columns.
    }


