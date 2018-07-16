package com.wallethub.parser.exception;

/**
 *
 * @author Rommel D. Medina
 */
public class AccessLogServiceException extends Exception {

    /**
     * Creates a new instance of <code>AccessLogServiceException</code> without
     * detail message.
     */
    public AccessLogServiceException() {
    }

    /**
     * Constructs an instance of <code>AccessLogServiceException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AccessLogServiceException(String msg) {
        super(msg);
    }

    public AccessLogServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessLogServiceException(Throwable cause) {
        super(cause);
    }

}
