package com.spring.mmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
public class MmmApplication {

    public static void main(String[] args) {
        SpringApplication.run(MmmApplication.class, args);
    }

}
