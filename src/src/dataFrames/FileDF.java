package dataFrames;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Clase abstracta que servirá para heredar las funciones para las clases hijas.
 */
public class FileDF implements DataFrame {
    Map<String, List<Object>> data;
    List<String> categories;

    public FileDF(Map<String, List<Object>> data, List<String> categories) {
        this.data = data;
        this.categories = categories;
    }

    @Override
    public Object at(int row, String column) {
        return row<size() && categories.contains(column)? data.get(column).get(row): "Index not valid, element not found";
    }


    @Override
    public Object iat(int row, int column) {
        return at(row,categories.get(column));
    }


    @Override
    public int columns() {
        return data.size();
    }


    @Override
    public int size() {
        return data.get(categories.get(0)).size();
    }


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

    @Override
    public ArrayList<String> getCategories() {
        return new ArrayList<>(categories);
    }

    @Override
    public Map<String, List<Object>> getData() {
        return new HashMap<>(data) {
        };
    }

    @Override
    public String toString() {
        String result = "";
        for (String cat:categories) {
            result = result.concat(cat+":\t\t\t"+data.get(cat).toString()+"\n");
        }
        return result;
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

    }

}
