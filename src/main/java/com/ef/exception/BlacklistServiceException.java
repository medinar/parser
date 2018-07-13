package com.ef.exception;

/**
 *
 * @author Rommel D. Medina
 */
public class BlacklistServiceException extends Exception {

    /**
     * Creates a new instance of <code>BlacklistServiceException</code> without
     * detail message.
     */
    public BlacklistServiceException() {
    }

    /**
     * Constructs an instance of <code>BlacklistServiceException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public BlacklistServiceException(String msg) {
        super(msg);
    }

    public BlacklistServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlacklistServiceException(Throwable cause) {
        super(cause);
    }

}
