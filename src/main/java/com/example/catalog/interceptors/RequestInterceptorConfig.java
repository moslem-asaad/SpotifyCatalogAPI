package com.example.catalog.interceptors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Objects;

@Configuration
public class RequestInterceptorConfig implements WebMvcConfigurer {

    @Value("${rate-limit.enabled}")
    private String rateLimitEnabled;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (Objects.equals(rateLimitEnabled, "true")) {
            registry.addInterceptor(new RateLimit());
        }
    }
}
