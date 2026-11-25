package com.cantina.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:5173",          // React local
                        "http://localhost:8081",          // Expo Web
                        "https://os-croods.vercel.app"    // front na Vercel
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}