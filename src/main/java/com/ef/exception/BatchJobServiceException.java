package com.ef.exception;

/**
 *
 * @author Rommel D. Medina
 */
public class BatchJobServiceException extends Exception {

    /**
     * Creates a new instance of <code>BatchJobServiceException</code> without
     * detail message.
     */
    public BatchJobServiceException() {
    }

    /**
     * Constructs an instance of <code>BatchJobServiceException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public BatchJobServiceException(String msg) {
        super(msg);
    }

    public BatchJobServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BatchJobServiceException(Throwable cause) {
        super(cause);
    }

}
