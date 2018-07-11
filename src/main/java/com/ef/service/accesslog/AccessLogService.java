package com.ef.service.accesslog;

import java.util.List;

/**
 *
 * @author rommelmedina
 */
public interface AccessLogService {

    public abstract List<String> findIps(
            String dateString,
            String duration,
            int threshold
    );

}
