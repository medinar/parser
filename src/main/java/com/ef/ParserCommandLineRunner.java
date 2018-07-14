package com.ef;

import com.ef.config.AppConfig;
import com.ef.domain.BlockedIp;
import com.ef.service.accesslog.AccessLogService;
import com.ef.service.accesslog.BlockedIpService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

    private final Logger logger
            = LoggerFactory.getLogger(this.getClass().getName());

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
    private BlockedIpService blockedIpService;

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
                argMap.put(ARG_DURATION, arg.split(ARG_SEP)[1].toLowerCase());
            }
            else if (containsIgnoreCase(arg, ARG_THRESHOLD)) {
                argMap.put(ARG_THRESHOLD, arg.split(ARG_SEP)[1]);
            }
        }

        // If accesslog parameter is present, parse and save the data to database.
        if (isNotBlank(argMap.get(ARG_ACCESS_LOG))) {
//            accessLogService.parseAndSave(argMap.get(ARG_ACCESS_LOG));
        }

        int threshold = 0;
        // If atleast the date and the duration is present,
        // retrieve the list of blocked ip addresses.
        // Validate duration. Duration should either be "hourly" or "daily" only.
        if (isNotBlank(argMap.get(ARG_START_DATE))
                && isNotBlank(argMap.get(ARG_DURATION))
                && (equalsIgnoreCase(argMap.get(ARG_DURATION), DURATION_HOURLY)
                || equalsIgnoreCase(argMap.get(ARG_DURATION), DURATION_DAILY))) {

            // If value for threshold is present,
            // pass it to the accessLogService as parameter.
            // Otherwise, set its value based on the passed duration value.
            // Set the value of threshold to 200 for hourly and 500 for daily.
            if (isNotBlank(argMap.get(ARG_THRESHOLD))) {
                if (isNumeric(argMap.get(ARG_THRESHOLD))) {
                    threshold = Integer.parseInt(argMap.get(ARG_THRESHOLD));
                }
            }
            else {
                threshold = config
                        .getThreshold()
                        .get(argMap.get(ARG_DURATION));
            }

            // Parse the date string to LocalDateTime object.
            DateTimeFormatter formatter
                    = DateTimeFormatter.ofPattern(config.getDatePattern());
            LocalDateTime ldtStartDate = LocalDateTime.parse(
                    argMap.get(ARG_START_DATE),
                    formatter
            );

            // Add the number of hours according to duration.
            long hours = config.getDuration().get(argMap.get(ARG_DURATION));
            String endDate = ldtStartDate.plusHours(hours).format(formatter);

            // Retrieve ip addresses for the given criteria.
            List<String> blockedIpAddresses = accessLogService
                    .getBlockedIpAddresses(
                            argMap.get(ARG_START_DATE),
                            endDate,
                            threshold
                    );

            // Save the blocked ip address with the reason why it was blocked
            // to BlockedIp object.
            List<BlockedIp> blockedIps = new ArrayList<>();
            for (String ipAddress : blockedIpAddresses) {
                blockedIps.add(new BlockedIp(
                        ipAddress,
                        String.format("%s has %s or more requests between %s and %s",
                                ipAddress,
                                threshold,
                                argMap.get(ARG_START_DATE),
                                endDate
                        )
                ));
            }

            // Batch insert all blocked ip addresses.
            blockedIpService.saveAll(blockedIps);
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
