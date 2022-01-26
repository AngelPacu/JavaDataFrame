package extern;

import dataFrames.DataFrame;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MapReduce {
    public static Integer addColumns(Collection<DataFrame> collection) {
        return collection.parallelStream().
                map(DataFrame::columns).reduce(0, Integer::sum);
    }
/*
    public static Map<String, List<Object>> parallelQuery(Collection<DataFrame> collection, String column, Predicate<Object> predicate) {
        return collection.parallelStream().
                map(x -> x.extendedQuery(column, predicate)).
                reduce();
    }*/
}
