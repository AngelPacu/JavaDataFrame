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
     * @return the result of the operation performed
     */
    long visit(DataFrame dataFrame, String column);
}
