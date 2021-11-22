package dataFrames;

import java.util.Comparator;

public interface DataFrame extends Iterable<Integer> {

    //Matriz? filas i columnas -> Map de Listas, cada lista es una columna

    Object at(int row, String column);
    Object iat(int row, int column);
    int columns();
    int size();
    int[] sort(String column, Comparator<Integer> integerComparator);
    //DataFrame query(Condicion???) -> Predicate;

}
