package dataFrames;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Objeto creado a partir de unos datos recibidos de diferentes formatos de ficheros (.txt, .json, .csv) que permitirá
 * ejecutar varias operaciones de control sobre estos.
 */
public interface DataFrame extends Iterable<List<Object>> {
    /**
     * Devuelve el valor de la fila y la columna asignada.
     * @param row: Número de la fila del documento.
     * @param column: Etiqueta de la categoría.
     * @return El valor que corresponde a la posición.
     */
    Object at(int row, String column);
    /**
     * Devuelve el valor de la fila y la columna asignada.
     * @param row: Número de la fila del documento.
     * @param column: Número de la categoría.
     * @return El valor que corresponde a la posición.
     */
    Object iat(int row, int column);
    /**
     * Indica la cantidad total de columnas.
     * @return Devuelve un int indicando la cantidad total de columnas.
     */
    int columns();
    /**
     * Indica la cantidad total de elementos de la "list" que tiene el hashmap como valor.
     * @return Devuelve un int indicando la cantidad total de filas.
     */
    int size();

    /**
     * Ordena la lista que tenemos asociado con una key (indicada por parámetro).
     * @param column: La key para obtener la lista a ordenar.
     * @param comparator: Lambda que indicará como ordenar la lista.
     * @return Lista de los valores ordenados
     */
    List<Object> sort(String column, Comparator<Object> comparator);
    /**
     * Muestra los elementos que cumplan la condición indicada por el usuario.
     * @param column: La key para obtener la lista a filtrar.
     * @param predicate: El predicado que filtrará los resultados.
     * @return Lista de las columnas que cumplan la condición.
     */
    List<Object> query(String column, Predicate<Object> predicate);
    /**
     * Una mejora de la función "query" que mostrará toda la fila de la posición que cumpla la condición.
     * @param column: La key para obtener la lista a filtrar.
     * @param predicate: El predicado que filtrará los resultados.
     * @return Una lista Hash con todos los datos asociados a la columna que cumpla la condición.
     */
    Map<String, List<Object>> extendedQuery(String column, Predicate<Object> predicate);

    /**
     * Getter de las categorías
     * @return Una lista de categorías.
     */
    List<String> getCategories();

    /**
     * Getter de los datos
     * @return un mapa con los datos
     */
    Map<String, List<Object>> getData();
}
