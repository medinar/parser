package com.wallethub.parser.service.batch.item.processor;

import com.wallethub.parser.domain.AccessLog;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rommel Medina
 */
@Component
public class AccessLogItemProcessor implements ItemProcessor<AccessLog, AccessLog> {

    // private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Override
    public AccessLog process(AccessLog accessLog) throws Exception {
        // logger.info(accessLog.toString());
        // Do item processing here.
        return accessLog;
    }

}
