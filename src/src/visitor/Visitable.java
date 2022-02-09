package visitor;

/**
 * This interface will allow us to accept the visitor
 */
public interface Visitable {
    /**
     * The function we will use to accept will be implemented in the DF interface and will invoke the visitor's visit.
     * @param visitor: Object to accept.
     * @param column: Label to be used for the function
     * @return the result of the visit
     */
    long accept(Visitor visitor, String column);

}
