package com.duong.ptit_hn_ks2023b_lekhanhduong.validate.employee;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class ValidPhoneImpl implements ConstraintValidator<ValidPhone, String> {

    private static final String PHONE_REGEX = "^0[3|7|8|9][0-9]{8}$";

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        if (phone == null || phone.trim().isEmpty()) return false;
        return phone.trim().matches(PHONE_REGEX);
    }
}
