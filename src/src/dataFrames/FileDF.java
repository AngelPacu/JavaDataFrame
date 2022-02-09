package dataFrames;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Object created from data received from different file formats (.txt, .json, .csv) that implements
 * methods from the parent class DataFrame, also the leaf object in the Composite structure.
 */
public class FileDF implements DataFrame {
    Map<String, List<Object>> data; // HashMap (Keys = LabelsColumns, ListObject = ListValues)
    List<String> categories;        // List to Labels

    public FileDF(Map<String, List<Object>> data, List<String> categories) {
        this.data = data;
        this.categories = categories;
    }

    @Override
    public Object at(int row, String column) { // If the row exceeds the total size of the DF, it will be an error. If not, we will obtain the element.
        return row<size() && categories.contains(column) ? data.get(column).get(row) : "Index not valid, element not found";
    }


    @Override
    public Object iat(int row, int column) {
        return at(row,categories.get(column));
    } // Call the AT function


    @Override
    public int columns() {
        return data.size();
    } //Return size of Dataframe Keys


    @Override
    public int size() {
        return data.get(categories.get(0)).size();
    } //Return size of values List.


    @Override
    public List<Object> sort(String column, Comparator<Object> comparator) {
        return data.get(column).stream().sorted().collect(Collectors.toList()); //Return a ListSorted, We will sort with function sorted of Streams
    }


    @Override
    public List<Object> query(String column, Predicate<Object> pred) {
        return data.get(column).stream().filter(pred).collect(Collectors.toList()); //Return a FilterList, We will filter with function filter of Streams and our predicate.
    }


    @Override
    public Map<String, List<Object>> extendedQuery(String column, Predicate<Object> pred) { // Returns all elements in the row that meet the condition.
        Map<String, List<Object>> result = new HashMap<>();
        categories.forEach(c->result.put(c, new ArrayList<>())); // We enter the keys from the auxiliary list that we have
        for (int i = 0; i < size(); i++) {
            if((pred.test(data.get(column).get(i)))) {          // We get the index that passes the predicate condition
                int finalI = i;                                 // We cant use var "i" because we need a temp variable for lambda
                data.forEach((k, v)->result.get(k).add(v.get(finalI))); // Enter all the elements of the row corresponding to the index.
            }
        }
        return result;
    }

    @Override
    public Iterator<List<Object>> iterator() {

        return new Iterator<>() {       // To loop through the list of values, we shall not exceed the size of the list.
            private int pos=0;
            @Override
            public boolean hasNext() {
                return pos<size()-1;
            }   //

            @Override
            public List<Object> next() {
                return data.get(categories.get(pos++));
            }   // Return to values list.
        };
    }

    @Override
    public ArrayList<String> getCategories() {
        return new ArrayList<>(categories);
    } // Return keys list.

    @Override
    public Map<String, List<Object>> getData() {   return new HashMap<>(data);}     // Return a DF

    @Override
    public String toString() {
        String result = "";
        for (String cat:categories) {
            result = result.concat(cat+":\t\t\t"+data.get(cat).toString()+"\n");
        }
        return result;

        /*for(String cat:categories){   //Alternative print in columns
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
