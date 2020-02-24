package com.example.demo;

import com.example.demo.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        Config.init();
        SpringApplication.run(DemoApplication.class, args);
    }

}
