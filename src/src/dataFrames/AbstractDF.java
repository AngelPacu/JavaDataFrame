package dataFrames;

import javax.xml.crypto.Data;
import java.util.*;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Clase abstracta que servirá para heredar las funciones para las clases hijas.
 */
public abstract class AbstractDF implements DataFrame {
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

    public Map<String, List<Object>> query(String column, Predicate<Object> predicado) {
        List<Object> valoresFiltrados = data.get(column).stream().filter(predicado).collect(Collectors.<Object>toList());
        List<Object> copiaDF = new ArrayList<>(data.get(column));
        List<Integer> listaPos = new ArrayList<>();
        Map<String,List<Object>> dataframeFiltrado = new HashMap<>();
        for (int i=0; i<valoresFiltrados.size();i++){       //Query gitana
            listaPos.add(copiaDF.indexOf(valoresFiltrados.get(i)));
            copiaDF.set(listaPos.get(i),null);
        }
        for (int i = 0; i<categories.size();i++){       //Recorremos todas las labels asignandoles los valores obtenido con las indexaciones que hemos obtenido.
            copiaDF.removeAll(copiaDF);                 //Reutilizamos la lista borrando todos los elementos.
            for (int j = 0; j<listaPos.size();j++) {
                copiaDF.add(data.get(categories.get(i)).get(listaPos.get(j))); //Añadimos todos los valores filtrados segun cantidad de posiciones obtenidas
            }
            dataframeFiltrado.put(categories.get(i),new ArrayList<Object>(copiaDF));        //Añadimos al dataframe la label como key y la lista de valores filtrados.
        }
        return dataframeFiltrado;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

}
