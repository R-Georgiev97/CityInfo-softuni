package com.example.cityinfo.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUserUsernameValidator.class)
public @interface UniqueUserUsername {
    String message() default "Username is not unique";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
