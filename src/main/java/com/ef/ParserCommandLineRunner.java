package com.ef;

import com.ef.config.AppConfig;
import com.ef.service.accesslog.AccessLogService;
import com.ef.service.accesslog.BatchJobService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;
import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.isNumeric;
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

    @Autowired
    AppConfig config;

    private static final String ARG_SEP = "=";
    private static final String ARG_ACCESS_LOG = "accesslog";
    private static final String ARG_START_DATE = "startdate";
    private static final String ARG_DURATION = "duration";
    private static final String ARG_THRESHOLD = "threshold";
    private static final String DURATION_HOURLY = "hourly";
    private static final String DURATION_DAILY = "daily";

    @Autowired
    private AccessLogService accessLogService;

    @Autowired
    private BatchJobService batchJobService;

    @Override
    public void run(String... args) throws Exception {

        // Store all valid arguments in a Map object.
        Map<String, String> argMap = new HashMap<>();
        for (String arg : args) {
            if (containsIgnoreCase(arg, ARG_ACCESS_LOG)) {
                argMap.put(ARG_ACCESS_LOG, arg.split(ARG_SEP)[1]);
            }
            else if (containsIgnoreCase(arg, ARG_START_DATE)) {
                argMap.put(ARG_START_DATE, arg.split(ARG_SEP)[1]);
            }
            else if (containsIgnoreCase(arg, ARG_DURATION)) {
                argMap.put(ARG_DURATION, arg.split(ARG_SEP)[1]);
            }
            else if (containsIgnoreCase(arg, ARG_THRESHOLD)) {
                argMap.put(ARG_THRESHOLD, arg.split(ARG_SEP)[1]);
            }
        }

        // If accesslog parameter is present, parse and save the data to database.
        if (isNotBlank(argMap.get(ARG_ACCESS_LOG))) {
            batchJobService.launch(argMap.get(ARG_ACCESS_LOG));
        }

        int threshold = 0;
        // If the atleast the date and the duration is present,
        // call getRequests(...) method.
        if (isNotBlank(argMap.get(ARG_START_DATE))
                && isNotBlank(argMap.get(ARG_DURATION))) {

            // Check if the threshold. If present, pass it to the accessLogService.
            // Otherwise, check the passed duration value.
            // Set the value of threshold to 200 for hourly and 500 for daily.
            if (isNotBlank(argMap.get(ARG_THRESHOLD))) {
                if (isNumeric(argMap.get(ARG_THRESHOLD))) {
                    threshold = Integer.parseInt(argMap.get(ARG_THRESHOLD));
                }
            }
            else {
                if (equalsIgnoreCase(argMap.get(ARG_DURATION), DURATION_HOURLY)) {
                    threshold = config.getHourlyThreshold();
                }
                else if (equalsIgnoreCase(argMap.get(ARG_DURATION), DURATION_DAILY)) {
                    threshold = config.getDailyThreshold();
                }
                logger.info("Threshold is blank.");
            }
            accessLogService.getRequests(
                    argMap.get(ARG_START_DATE),
                    argMap.get(ARG_DURATION),
                    threshold);
        }
        else {
            logger.info("Invalid arguments. Actual: "
                    + "{}, Expected: Atleast {} and {} is required.",
                        Arrays.toString(args),
                        ARG_START_DATE,
                        ARG_DURATION
            );
        }
    }

}
