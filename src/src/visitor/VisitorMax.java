package visitor;

import dataFrames.DataFrame;

public class VisitorMax implements Visitor {

    @Override
    public long visit(DataFrame dataFrame, String column) {
        return dataFrame.getData().get(column).stream().mapToLong(x->(Long) x).max().orElse(0);
    }
}
