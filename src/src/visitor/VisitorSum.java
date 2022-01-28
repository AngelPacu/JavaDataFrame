package visitor;

import dataFrames.DataFrame;

public class VisitorSum implements Visitor {

    @Override
    public long visit(DataFrame dataFrame, String column) {
        return dataFrame.getData().get(column).stream().mapToLong(x->(Long) x).sum();
    }
}
