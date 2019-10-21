package com.repita.portalgerente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PortalRepitaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalRepitaApplication.class, args);
    }
}
