package com.example.cityinfo.util;

import org.springframework.stereotype.Component;

@Component
public interface ValidationUtil {
    <E> boolean isValid(E entity);
}
