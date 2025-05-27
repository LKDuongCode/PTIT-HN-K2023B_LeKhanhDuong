package com.duong.ptit_hn_ks2023b_lekhanhduong.validate.department;

import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.department.UpdateDepartmentDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.model.Department;
import com.duong.ptit_hn_ks2023b_lekhanhduong.service.department.DepartmentService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UniqueDepartmentNameUpdateImpl implements ConstraintValidator<UniqueDepartmentNameUpdate, UpdateDepartmentDTO> {

    private final DepartmentService departmentService;

    public UniqueDepartmentNameUpdateImpl(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public boolean isValid(UpdateDepartmentDTO dto, ConstraintValidatorContext context) {
        String name = dto.getName();
        int id = dto.getId();

        if (name == null || name.trim().isEmpty()) return true;

        Optional<Department> found = departmentService.findDepartmentByName(name.trim());

        if (found.isEmpty()) return true;

        return found.get().getId() == id;
    }
}
