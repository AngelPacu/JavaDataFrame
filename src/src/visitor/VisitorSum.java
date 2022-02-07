package visitor;

import dataFrames.DataFrame;

/**
 * Visitor that will perform the sum of the list of values in a specified column.
 */
public class VisitorSum implements Visitor {

    @Override
    public long visit(DataFrame dataFrame, String column) {
        // We map it to long and use the ".sum" function that allows us the streams to show total sum.
        return dataFrame.getData().get(column).stream().mapToLong(x->(Long) x).sum();
    }
}
