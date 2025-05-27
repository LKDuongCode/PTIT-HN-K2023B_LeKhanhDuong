package com.duong.ptit_hn_ks2023b_lekhanhduong.validate.employee;
import com.duong.ptit_hn_ks2023b_lekhanhduong.validate.employee.ValidEmailImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidEmailImpl.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {
    String message() default "Invalid email format!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
