package com.repita.portalgerente.config;

import com.repita.portalgerente.service.ProtalRepitaService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ProtalRepitaService.class)
public class ServiceConfig {

}
