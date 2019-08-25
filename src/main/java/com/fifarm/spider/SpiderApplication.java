package com.fifarm.spider;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpiderApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "classpath:real-application.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpiderApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

}