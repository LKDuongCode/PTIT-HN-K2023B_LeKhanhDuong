package com.duong.ptit_hn_ks2023b_lekhanhduong.validate.department;

import com.duong.ptit_hn_ks2023b_lekhanhduong.service.department.DepartmentService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueDepartmentNameCreateImpl implements ConstraintValidator<UniqueDepartmentNameCreate, String> {
    private final DepartmentService departmentService;

    public UniqueDepartmentNameCreateImpl(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (name == null || name.trim().isEmpty()) return true;

        return departmentService.findDepartmentByName(name.trim()).isEmpty();
    }
}
