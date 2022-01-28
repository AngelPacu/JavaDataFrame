package visitor;

import dataFrames.DataFrame;

public class VisitorMin implements Visitor {

    @Override
    public long visit(DataFrame dataFrame, String column) {
        return dataFrame.getData().get(column).stream().mapToLong(x->(Long) x).min().orElse(0);
    }
}
