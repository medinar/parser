package com.ef;

import com.ef.service.accesslog.AccessLogService;
import com.ef.service.accesslog.BatchJobService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rommel Medina
 */
@Component
public class ParserCommandLineRunner implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private static final String ARG_SEP = "=";
    private static final String ACCESS_LOG = "accesslog";
    private static final String START_DATE = "startdate";
    private static final String DURATION = "duration";
    private static final String THRESHOLD = "threshold";

    @Autowired
    private AccessLogService accessLogService;

    @Autowired
    private BatchJobService batchJobService;

    @Override
    public void run(String... args) throws Exception {

        // Store all valid arguments in a Map object.
        Map<String, String> argMap = new HashMap<>();
        for (String arg : args) {
            if (containsIgnoreCase(arg, ACCESS_LOG)) {
                argMap.put(ACCESS_LOG, arg.split(ARG_SEP)[1]);
            }
            else if (containsIgnoreCase(arg, START_DATE)) {
                argMap.put(START_DATE, arg.split(ARG_SEP)[1]);
            }
            else if (containsIgnoreCase(arg, DURATION)) {
                argMap.put(DURATION, arg.split(ARG_SEP)[1]);
            }
            else if (containsIgnoreCase(arg, THRESHOLD)) {
                argMap.put(THRESHOLD, arg.split(ARG_SEP)[1]);
            }
        }

        // Continue only if the number of valid arguments passed is either 3 or 4.
        // If 3 valid arguments passed, call findIpAddresses(...) method only.
        // If 4 valid arguments passed, call the launch() method
        // then call the findIpAddresses(...) method.
        switch (argMap.size()) {
            case 3:
                accessLogService.findIpAddresses(
                        argMap.get(START_DATE),
                        argMap.get(DURATION),
                        Integer.parseInt(argMap.get(THRESHOLD))
                );
                break;
            case 4:
                batchJobService.launch(argMap.get(ACCESS_LOG));
                accessLogService.findIpAddresses(
                        argMap.get(START_DATE),
                        argMap.get(DURATION),
                        Integer.parseInt(argMap.get(THRESHOLD))
                );
                break;
            default:
                logger.info("Invalid arguments -> {}", Arrays.toString(args));
                break;
        }
    }

}
