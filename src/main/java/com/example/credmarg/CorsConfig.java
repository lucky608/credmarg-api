package com.example.credmarg;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/credmarg/**") // Apply to all APIs under "/api"
                .allowedOrigins("http://localhost:3000") // Allow requests from http://localhost:3000
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specified methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true) // Allow sending cookies
                .maxAge(3600); // Max age of the CORS options request
    }
}
