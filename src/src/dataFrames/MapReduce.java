package dataFrames;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MapReduce {
    public static Long addColumns(Collection<DataFrame> collection) {
        return collection.parallelStream().
                map(DataFrame::columns).
                mapToLong(x->(long) x).
                reduce(0, Long::sum);
    }

    public static Long parallelAt(Collection<DataFrame> collection, int row, String column) {
        return collection.parallelStream().
                map(df->(long)df.at(row, column)).
                reduce(0L, Long::sum);
    }
    public static List<Object> filterSort(Collection<DataFrame> collection, String column, Comparator<Object> comp, Predicate<Object> pred){
        return collection.parallelStream().
                map(df->df.query(column, pred)).
                reduce(new ArrayList<>(),(x,y)->{y.addAll(x); return y;}).
                stream().sorted(comp).collect(Collectors.toList());
    }
}
