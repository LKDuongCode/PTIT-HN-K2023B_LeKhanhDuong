package com.duong.ptit_hn_ks2023b_lekhanhduong.validate.employee;

import com.duong.ptit_hn_ks2023b_lekhanhduong.service.employee.EmployeeService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailImpl implements ConstraintValidator<UniqueEmail, String> {

    private final EmployeeService employeeService;

    public UniqueEmailImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.trim().isEmpty()) return true;
        return employeeService.findEmployeeByEmail(email.trim()).isEmpty();
    }
}
