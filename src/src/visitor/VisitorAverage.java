package visitor;

import dataFrames.DataFrame;

/**
 * Visitor that will perform the average of the list of values in a specified column.
 */
public class VisitorAverage implements Visitor {

    @Override
    public long visit(DataFrame dataFrame, String column) {
       // We map it to long and use the ".average" function that allows us the streams to calculate the average.
       // The function returns a double, so we cast it to long.
        return (long) dataFrame.getData().get(column).stream().mapToLong(x->(Long) x).average().orElse(0);
    }
}
