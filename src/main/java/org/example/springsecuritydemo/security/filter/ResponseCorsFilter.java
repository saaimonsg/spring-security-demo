package org.example.springsecuritydemo.security.filter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;



public class ResponseCorsFilter {

    public static CorsConfigurationSource corsConfigurationSource = request -> {
        var cors = new CorsConfiguration();
        cors.setAllowedOrigins(List.of("*"));
        cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        cors.setAllowedHeaders(List.of("*"));
        return cors;
    };
}
