package dataFrames;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Methods featuring the usage of Parallel Stream, Map and Reduce
 */
public class MapReduce {
    /**
     * Total amount of columns in the collection
     * @param collection: Collection of DataFrames with which we operate
     * @return total amount of Columns
     */
    public static Long addColumns(Collection<DataFrame> collection) {
        return collection.parallelStream().
                map(DataFrame::columns).
                mapToLong(x->(long) x).
                reduce(0, Long::sum);
    }

    /**
     * Adds the value of the element in a specified position for every DataFrame
     * @param collection: Collection of DataFrames with which we operate
     * @param row: Specified row
     * @param column: Specified column
     * @return The sum of every element
     */
    public static Long parallelAt(Collection<DataFrame> collection, int row, String column) {
        return collection.parallelStream().
                map(df->(long)df.at(row, column)).
                reduce(0L, Long::sum);
    }

    /**
     * Performs a query in every Dataframe and sorts the result
     * @param collection: Collection of DataFrames with which we operate
     * @param column: Specified column
     * @param comp: Comparator used to sort
     * @param pred: Condition used to filter
     * @return the items that fulfill the condition in a sorted list
     */
    public static List<Object> filterSort(Collection<DataFrame> collection, String column, Comparator<Object> comp, Predicate<Object> pred){
        return collection.parallelStream().
                map(df->df.query(column, pred)).
                reduce(new ArrayList<>(),(x,y)->{y.addAll(x); return y;}).
                stream().sorted(comp).collect(Collectors.toList());
    }
}
