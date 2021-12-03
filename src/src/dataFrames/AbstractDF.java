package dataFrames;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Clase abstracta que servirá para heredar las funciones para las clases hijas.
 */
public abstract  class AbstractDF implements DataFrame {
    Map<String, List<Object>> data;
    ArrayList<String> categories;

    /**
     * Devuelve el valor de la fila y la columna asignada.
     * @param row: Número de la fila del documento.
     * @param column: Etiqueta de la categoría.
     * @return El valor que corresponde a la posición.
     */
    @Override
    public Object at(int row, String column) {
        return data.get(column).get(row);
    }

    /**
     *
     * Devuelve el valor de la fila y la columna asignada.
     * @param row: Número de la fila del documento.
     * @param column: Número de la categoría.
     * @return El valor que corresponde a la posición.
     */
    @Override
    public Object iat(int row, int column) {
        return at(row,categories.get(column));
    }

    /**
     * Indica la cantidad total de columnas.
     * @return: Devuelve un int indicando la cantidad total de columnas.
     */
    @Override
    public int columns() {
        return data.size();
    }

    /**
     * Indica la cantidad total de la "list" que tiene el hashmap como valor.
     * @return: Devuelve un int indicando la cantidad total de filas.
     */
    @Override
    public int size() {
        return data.get(categories.get(0)).size();
    }

    /**
     * Ordena la lista que tenemos asociado con una key (indicada por parámetro).
     * @param column: La key para obtener la lista a ordenar.
     * @param comparator: Lamda que indicará como ordenar la lista.
     * @return
     */
    @Override
    public List<Object> sort(String column, Comparator<Object> comparator) {
        return data.get(column).stream().sorted().collect(Collectors.toList());
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }
}
