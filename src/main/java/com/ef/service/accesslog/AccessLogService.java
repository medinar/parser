package com.ef.service.accesslog;

import com.ef.exception.AccessLogServiceException;
import java.util.List;

/**
 *
 * @author rommelmedina
 */
public interface AccessLogService {

    static final String PATH = "path";
    static final String TIME = "time";

    /**
     * Get IP addresses that reached the maximum number of requests allowed.
     *
     * @param startDateString
     * @param duration
     * @param threshold
     * @return
     * @throws com.ef.exception.AccessLogServiceException
     */
    public abstract List<String> getBlockedIpAddresses(
            String startDateString,
            String duration,
            int threshold
    ) throws AccessLogServiceException;

    /**
     * Parses the given file path and save it to database.
     *
     * @param path
     * @throws com.ef.exception.AccessLogServiceException
     */
    public abstract void parseAndSave(String path) throws AccessLogServiceException;

}
