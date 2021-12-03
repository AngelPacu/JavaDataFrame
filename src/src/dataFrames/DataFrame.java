

package dataFrames;

import java.util.Comparator;
import java.util.List;

/**
 * Interface de los DataFrames donde se definen las funciones.
 */
public interface DataFrame extends Iterable<Integer> {
    Object at(int row, String column);
    Object iat(int row, int column);
    int columns();
    int size();
    List<Object> sort(String column, Comparator<Object> integerComparator);
    //DataFrame query(Condicion???) -> Predicate;

}
