package control.ed.lista.exception;

public class VacioException extends Exception {

    /**
     * Creates a new instance of <code>EmptyException</code> without detail
     * message.
     */
    public VacioException() {
    }

    /**
     * Constructs an instance of <code>EmptyException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public VacioException(String msg) {
        super(msg);
    }
}
