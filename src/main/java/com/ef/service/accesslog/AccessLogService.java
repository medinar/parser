package com.ef.service.accesslog;

import com.ef.exception.AccessLogServiceException;
import java.util.List;

/**
 *
 * @author rommelmedina
 */
public interface AccessLogService {

    /**
     * Get all requests that reached the given threshold for the given date
     * within the given duration.
     *
     * @param startDateString
     * @param duration
     * @param threshold
     * @return
     * @throws com.ef.exception.AccessLogServiceException
     */
    public abstract List<String> getIpAddresses(
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
