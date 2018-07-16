package com.wallethub.parser.exception;

/**
 *
 * @author Rommel D. Medina
 */
public class BlockedIpServiceException extends Exception {

    /**
     * Creates a new instance of <code>BlockedIpServiceException</code> without
     * detail message.
     */
    public BlockedIpServiceException() {
    }

    /**
     * Constructs an instance of <code>BlockedIpServiceException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public BlockedIpServiceException(String msg) {
        super(msg);
    }

    public BlockedIpServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlockedIpServiceException(Throwable cause) {
        super(cause);
    }

}
