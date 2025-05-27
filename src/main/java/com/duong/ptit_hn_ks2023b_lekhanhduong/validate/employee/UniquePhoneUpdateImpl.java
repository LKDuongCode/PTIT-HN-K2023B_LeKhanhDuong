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
public class UniquePhoneUpdateImpl implements ConstraintValidator<UniquePhoneUpdate, UpdateEmployeeDTO> {

    private final EmployeeService employeeService;

    public UniquePhoneUpdateImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public boolean isValid(UpdateEmployeeDTO dto, ConstraintValidatorContext context) {
        String phone = dto.getPhone();
        int id = dto.getId();

        if (phone == null || phone.trim().isEmpty()) return true;

        Optional<Employee> found = employeeService.findEmployeeByPhone(phone.trim());

        return found.isEmpty() || found.get().getId() == id;
    }
}
