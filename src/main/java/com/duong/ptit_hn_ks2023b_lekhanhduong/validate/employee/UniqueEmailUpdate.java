package com.duong.ptit_hn_ks2023b_lekhanhduong.validate.employee;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailUpdateImpl.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmailUpdate {
    String message() default "Email already exists!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
