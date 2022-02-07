package dataFrames;

import visitor.Visitable;
import visitor.Visitor;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Object created from data received from different file formats (.txt, .json, .csv) that will allow to
 * execute several control operations on these files.
 */
public interface DataFrame extends Iterable<List<Object>>, Visitable {
    /**
     * Returns the value of the row and the assigned column.
     * @param row: Number of the document row.
     * @param column: Category label.
     * @return The value that corresponds to the position.
     */
    Object at(int row, String column);
    /**
     * Returns the value of the row and the assigned column.
     * @param row: Number of the document row.
     * @param column: Number of the category.
     * @return The value that corresponds to the position.
     */
    Object iat(int row, int column);
    /**
     * Indica la cantidad total de columnas.
     * @return Devuelve un int indicando la cantidad total de columnas.
     */
    int columns();
    /**
     * Indicates the total number of columns.
     * @return an int indicating the total number of columns.
     */
    int size();

    /**
     * Sorts the list we have associated with a key (specified by parameter).
     * @param column: The key to get the list to sort.
     * @param comparator: Lambda that will indicate how to sort the list.
     * @return List of the sorted values.
     */
    List<Object> sort(String column, Comparator<Object> comparator);
    /**
     * Displays the elements that meet the condition indicated by the user.
     * @param column: The key to obtain the list to filter.
     * @param predicate: The predicate that will filter the results.
     * @return List of the columns that meet the condition.
     */
    List<Object> query(String column, Predicate<Object> predicate);
    /**
     * An enhancement to the "query" function that will display the entire row of the position that meets the condition.
     * @param column: The key to get the list to filter.
     * @param predicate: The predicate that will filter the results.
     * @return A Hash list with all the data associated to the column that meets the condition.
     */
    Map<String, List<Object>> extendedQuery(String column, Predicate<Object> predicate);

    /**
     * Getter of categories
     * @return A list of categories.
     */
    List<String> getCategories();

    /**
     * Getter of the data
     * @return a map with the data
     */
    Map<String, List<Object>> getData();


    /**
     * Implemented function that accepts the visitor indicating the column to be operated.
     * @param visitor: person who wants to perform an operation.
     * @param column: the column we want to operate on.
     * @return The result of the visitor's operation.
     */
    default long accept(Visitor visitor, String column){
        return visitor.visit(this,column);
    };


}
