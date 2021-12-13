package dataFrames;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface DataFrame extends Iterable<Integer> {


    Object at(int row, String column);
    Object iat(int row, int column);
    int columns();
    int size();
    List<Object> sort(String column, Comparator<Object> integerComparator);
    Map<String, List<Object>> query(String column, Predicate<Object> predicado);

}
