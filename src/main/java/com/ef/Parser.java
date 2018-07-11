package com.ef;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Parser {

    // Make a regular main class and call joblauncher here.
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println("arg: " + arg);
        }
        SpringApplication.run(Parser.class, args);
    }

}
