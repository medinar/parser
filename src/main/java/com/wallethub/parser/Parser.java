package com.wallethub;

import java.util.Properties;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Parser {

    private static final String SPRING_PROFILES_ACTIVE = "spring.profiles.active";
    private static final String SPRING_CONFIG_LOCATION = "spring.config.location";

    public static void main(String[] args) {
        new SpringApplicationBuilder(Parser.class)
                .bannerMode(Banner.Mode.OFF)
                .sources(Parser.class)
                .properties(getProperties())
                .run(args);
    }

    private static Properties getProperties() {
        // Loading external properties.
        Properties prop = new Properties();
        prop.put(SPRING_PROFILES_ACTIVE, System.getProperty(SPRING_PROFILES_ACTIVE));
        prop.put(SPRING_CONFIG_LOCATION, System.getProperty(SPRING_CONFIG_LOCATION));
        return prop;
    }

}
