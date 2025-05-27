package com.duong.ptit_hn_ks2023b_lekhanhduong.validate.employee;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailImpl.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "Email already exists!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
