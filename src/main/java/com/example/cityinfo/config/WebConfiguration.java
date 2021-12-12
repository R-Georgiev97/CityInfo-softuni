package com.example.cityinfo.config;

import com.example.cityinfo.web.interceptor.LoggerInterceptor;
import com.example.cityinfo.web.interceptor.StatsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration  implements WebMvcConfigurer {

    private final StatsInterceptor statsInterceptor;
    private final LoggerInterceptor loggerInterceptor;

    public WebConfiguration(StatsInterceptor statsInterceptor, LoggerInterceptor loggerInterceptor) {
        this.statsInterceptor = statsInterceptor;
        this.loggerInterceptor = loggerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(statsInterceptor);
        registry.addInterceptor(loggerInterceptor);
    }
}
