package com.duong.ptit_hn_ks2023b_lekhanhduong.validate.department;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueDepartmentNameCreateImpl.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueDepartmentNameCreate {
    String message() default "Department name already exists!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}