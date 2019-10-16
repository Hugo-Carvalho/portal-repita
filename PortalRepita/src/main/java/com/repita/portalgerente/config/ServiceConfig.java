package com.repita.portalgerente.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.repita.portalgerente.service.CadastroNotaService;

@Configuration
@ComponentScan(basePackageClasses = CadastroNotaService.class)
public class ServiceConfig {

}
