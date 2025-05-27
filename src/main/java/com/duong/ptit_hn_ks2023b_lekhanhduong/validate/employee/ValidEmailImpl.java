package com.duong.ptit_hn_ks2023b_lekhanhduong.validate.employee;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class ValidEmailImpl implements ConstraintValidator<ValidEmail, String> {

    private static final String EMAIL_REGEX = "^[a-z0-9]+@gmail\\.com$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) return false;
        return email.trim().matches(EMAIL_REGEX);
    }
}
