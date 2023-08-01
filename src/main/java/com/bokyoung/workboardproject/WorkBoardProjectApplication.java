package com.bokyoung.workboardproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class WorkBoardProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkBoardProjectApplication.class, args);
    }

}
