package com.duong.ptit_hn_ks2023b_lekhanhduong.validate.employee;

import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.employee.UpdateEmployeeDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.model.Employee;
import com.duong.ptit_hn_ks2023b_lekhanhduong.service.employee.EmployeeService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UniqueEmailUpdateImpl implements ConstraintValidator<UniqueEmailUpdate, UpdateEmployeeDTO> {

    private final EmployeeService employeeService;

    public UniqueEmailUpdateImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public boolean isValid(UpdateEmployeeDTO dto, ConstraintValidatorContext context) {
        String email = dto.getEmail();
        int id = dto.getId();

        if (email == null || email.trim().isEmpty()) return true;

        Optional<Employee> found = employeeService.findEmployeeByEmail(email.trim());

        return found.isEmpty() || found.get().getId() == id;
    }
}
