package com.ef.service.accesslog;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rommel Medina
 */
@Service
public class AccessLogServiceImpl implements AccessLogService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public List<String> getRequests(String startDateString, String duration, int threshold) {
        logger.info(
                "startDateString -> {}, duration -> {}, threshold -> {}",
                startDateString, duration, threshold
        );
        return new ArrayList<>();
    }

}
