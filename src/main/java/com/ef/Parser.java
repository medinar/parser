package com.ef;

import java.util.Properties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Parser {

    // Make a regular main class and call joblauncher here.
    public static void main(String[] args) {
        new SpringApplicationBuilder(Parser.class)
                .sources(Parser.class)
                .properties(getProperties())
                .run(args);
    }

    static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("spring.profiles.active",
                       "dev"
        //                System.getProperty("bobAuditLogging.profiles.active")
        );
//        props.put("spring.config.location",
//                System.getProperty("bobAuditLogging.config.location")
//        );

        return properties;
    }

}
