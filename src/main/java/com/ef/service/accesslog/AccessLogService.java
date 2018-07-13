package com.ef.service.accesslog;

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
     */
    public abstract List<String> getRequests(
            String startDateString,
            String duration,
            int threshold
    );

}
