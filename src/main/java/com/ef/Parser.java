package com.ef;

import java.util.Properties;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Parser {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Parser.class)
                .bannerMode(Banner.Mode.OFF)
                .sources(Parser.class)
                .properties(getProperties())
                .run(args);
    }

    static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("spring.profiles.active", "dev");
//        props.put("spring.config.location",
//                System.getProperty("bobAuditLogging.config.location")
//        );
        return properties;
    }

}
