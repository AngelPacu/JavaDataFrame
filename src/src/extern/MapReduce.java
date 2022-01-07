package extern;

import dataFrames.DataFrame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MapReduce {
    public List<Integer> evenValues (Collection<DataFrame> collection, String column){
        return collection.parallelStream().
                map(x->x.getData().get(column)).
                map(Collection::stream).collect(Collectors.toList());
    }
    public ???
}
