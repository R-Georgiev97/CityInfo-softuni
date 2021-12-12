package com.example.cityinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CityInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityInfoApplication.class, args);
    }

}
