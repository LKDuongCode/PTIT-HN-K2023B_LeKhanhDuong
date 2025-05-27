package com.duong.ptit_hn_ks2023b_lekhanhduong.validate.department;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = UniqueDepartmentNameUpdateImpl.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueDepartmentNameUpdate {
    String message() default "Department name already exists!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
