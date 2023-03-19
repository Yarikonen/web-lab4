package com.yarikonen.web44.CORS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins(
                                "http://arlet.space:3000", "https://arlet.space:3000",
                                "http://localhost:3000", "https://localhost:3000",
                                "http://arlet.su:3000", "https://arlet.su:3000"
                        );
            }
        };
    }
}