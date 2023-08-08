package control.ed.lista.exception;

public class PosicionException extends Exception {

    /**
     * Creates a new instance of <code>PositionException</code> without detail
     * message.
     */
    public PosicionException() {
    }

    /**
     * Constructs an instance of <code>PositionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PosicionException(String msg) {
        super(msg);
    }
}
