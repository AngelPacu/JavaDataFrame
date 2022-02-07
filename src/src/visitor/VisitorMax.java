package visitor;

import dataFrames.DataFrame;

/**
 * Visitor that will display the maximum value from the list of values in a specified column.
 */
public class VisitorMax implements Visitor {

    @Override
    public long visit(DataFrame dataFrame, String column) {
        // We map it to long and use the ".max" function that allows us the streams to show the max element.
        return dataFrame.getData().get(column).stream().mapToLong(x->(Long) x).max().getAsLong();
    }
}
