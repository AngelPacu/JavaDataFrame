package visitor;

import dataFrames.DataFrame;

public class VisitorAverage implements Visitor {

    @Override
    public long visit(DataFrame dataFrame, String column) {
        return (long) dataFrame.getData().get(column).stream().mapToLong(x->(Long) x).average().orElse(0);
    }
}
