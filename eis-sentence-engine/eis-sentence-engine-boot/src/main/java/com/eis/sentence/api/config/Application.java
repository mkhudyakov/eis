package com.eis.sentence.api.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZoneOffset;
import java.util.TimeZone;

/**
 * Main class to run Spring Boot Application
 * @author Maksym Khudiakov
 */
@SpringBootApplication(scanBasePackages = "com.eis.sentence")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
    }
}
