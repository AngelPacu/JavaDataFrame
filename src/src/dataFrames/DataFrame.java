package dataFrames;

import visitor.Visitable;
import visitor.Visitor;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Interface defining all objects conforming a Composite pattern, required methods and method signature
 * Iterable over its Columns (labels) and visitable by Visitor pattern
 */
public interface DataFrame extends Iterable<List<Object>>, Visitable {
    /**
     * Returns the value at the desired row and column.
     * @param row: Number of the document row.
     * @param column: Category label.
     * @return The value that corresponds to the position.
     */
    Object at(int row, String column);
    /**
     * Returns the value at the desired row and column.
     * @param row: Number of the document row.
     * @param column: Number of the category.
     * @return The value that corresponds to the position.
     */
    Object iat(int row, int column);
    /**
     * Indicates the total number of columns.
     * @return an integer corresponding to the total number of columns.
     */
    int columns();
    /**
     * Indicates the total number of rows.
     * @return an integer corresponding to the total number of rows.
     */
    int size();

    /**
     * Sorts the desired column as specified by the comparator parameter.
     * @param column: The key to get the list to sort.
     * @param comparator: Lambda that will indicate how to sort the list.
     * @return List of the sorted values.
     */
    List<Object> sort(String column, Comparator<Object> comparator);
    /**
     * Displays the elements that meet the condition provided by the user.
     * @param column: The key to obtain the list to filter.
     * @param predicate: The predicate that will filter the results.
     * @return List of the columns that meet the condition.
     */
    List<Object> query(String column, Predicate<Object> predicate);
    /**
     * An enhancement to the "query" function that will display the entire row of the position that meets the condition.
     * @param column: The key to get the list to filter.
     * @param predicate: The predicate that will filter the results.
     * @return A Hash map with all the data associated to the objects that meet the condition.
     */
    Map<String, List<Object>> extendedQuery(String column, Predicate<Object> predicate);

    /**
     * Getter of categories
     * @return A list of categories.
     */
    List<String> getCategories();

    /**
     * Getter of the data map
     * @return a map with the data
     */
    Map<String, List<Object>> getData();


    /**
     * Implemented function that accepts the visitor indicating the column to be operated.
     * @param visitor: object that demands to perform an operation.
     * @param column: the column we want to operate on.
     * @return The result of the visitor's operation.
     */
    default long accept(Visitor visitor, String column){
        return visitor.visit(this,column);
    }

}
