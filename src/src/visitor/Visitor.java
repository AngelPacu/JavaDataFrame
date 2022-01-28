package visitor;

import dataFrames.DataFrame;

public interface Visitor {
    public long visit(DataFrame dataFrame, String column);
}
