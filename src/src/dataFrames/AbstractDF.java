package dataFrames;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        return row<size() && categories.contains(column)? data.get(column).get(row): "Index not valid, element not found";
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
     * @return: Lista de los valores ordenados
     */
    @Override
    public List<Object> sort(String column, Comparator<Object> comparator) {
        return data.get(column).stream().sorted().collect(Collectors.toList());
    }

    @Override
    public List<Object> query(String column, Predicate<Object> pred) {
        return data.get(column).stream().filter(pred).collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Object>> extendedQuery(String column, Predicate<Object> pred) {
        Map<String, List<Object>> result = new HashMap<>();
        categories.forEach(c->result.put(c, new ArrayList<>()));
        for (int i = 0; i < size(); i++) {
            if((pred.test(data.get(column).get(i)))) {
                int finalI = i;
                data.forEach((k, v)->result.get(k).add(v.get(finalI)));
            }
        }
        return result;

        /*List<Object> valoresFiltrados = data.get(column).stream().filter(pred).collect(Collectors.<Object>toList());
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
        return dataframeFiltrado;*/
    }

    @Override
    public Iterator<List<Object>> iterator() {
        int actual=0;
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return actual<size();
            }

            @Override
            public List<Object> next() {
                return data.get(categories.get(actual+1));
            }
        };
    }

    public ArrayList<String> getCategories() {
        return new ArrayList<>(categories);
    }

    @Override
    public String toString() {
        String result = "";
        for (String cat:categories) {
            result = result.concat(cat+":\t\t\t"+data.get(cat).toString()+"\n");
        }

        /*for(String cat:categories){
            result = result.concat(cat+":\t\t\t");
        }
        result = result.concat("\n");

        for (int i = 0; i < size(); i++) {
            for (String category : categories) {
                result = result.concat(data.get(category).get(i) + "\t\t\t");
            }
            result = result.concat("\n");
        }*/

        return result;
    }

}
