package com.duong.ptit_hn_ks2023b_lekhanhduong.service.employee;

import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.employee.CreateEmployeeDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.employee.EmployeeListDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.employee.UpdateEmployeeDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.model.Employee;
import com.duong.ptit_hn_ks2023b_lekhanhduong.repository.employee.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<EmployeeListDTO> getEmployeePageDTO(int limit, int offset) {
        return employeeRepo.getEmployeePageDTO(limit, offset);
    }

    @Override
    public int getTotalPages(int limit) {
        return employeeRepo.getTotalPages(limit);
    }

    @Override
    public boolean insertEmployee(CreateEmployeeDTO createEmployeeDTO) {
        return employeeRepo.insertEmployee(createEmployeeDTO);
    }

    @Override
    public boolean updateEmployee(UpdateEmployeeDTO updateEmployeeDTO) {
        return employeeRepo.updateEmployee(updateEmployeeDTO);
    }

    @Override
    public boolean deleteEmployee(int id) {
        return employeeRepo.deleteEmployee(id);
    }

    @Override
    public List<EmployeeListDTO> searchEmployeePageDTO(String keyword, int limit, int offset) {
        return employeeRepo.searchEmployeePageDTO(keyword, limit, offset);
    }

    @Override
    public Optional<Employee> findEmployeeByEmail(String email) {
        return employeeRepo.findEmployeeByEmail(email);
    }

    @Override
    public Optional<Employee> findEmployeeByPhone(String phone) {
        return employeeRepo.findEmployeeByPhone(phone);
    }

    @Override
    public Optional<Employee> findEmployeeById(int id) {
        return employeeRepo.findEmployeeById(id);
    }
}

