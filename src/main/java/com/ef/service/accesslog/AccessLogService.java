package com.ef.service.accesslog;

import java.util.List;

/**
 *
 * @author rommelmedina
 */
public interface AccessLogService {

    public abstract List<String> findIpAddresses(
            String startDateString,
            String duration,
            int threshold
    );

}
