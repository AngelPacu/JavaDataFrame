package visitor;

import dataFrames.DataFrame;

/**
 * This interface will allow the visitor to make the request to invoke the requested function.
 */
public interface Visitor {
    /**
     *
     * @param dataFrame: DF we use
     * @param column: The column we want to operate on.
     * @return
     */
    public long visit(DataFrame dataFrame, String column);
}
