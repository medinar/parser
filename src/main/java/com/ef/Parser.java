package com.ef;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Parser {

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println("arg: " + arg);
        }
        SpringApplication.run(Parser.class, args);
    }

}
