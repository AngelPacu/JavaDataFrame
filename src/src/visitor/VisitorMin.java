package visitor;

import dataFrames.DataFrame;

/**
 *  * Visitor that will display the minimum value from the list of values in a specified column.
 */
public class VisitorMin implements Visitor {

    @Override
    public long visit(DataFrame dataFrame, String column) {
        // We map it to long and use the ".min" function that allows us the streams to show the min element.
        return dataFrame.getData().get(column).stream().mapToLong(x->(Long) x).min().orElse(0);
    }
}
