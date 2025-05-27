package com.duong.ptit_hn_ks2023b_lekhanhduong.repository.employee;

import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.employee.CreateEmployeeDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.employee.EmployeeListDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.employee.UpdateEmployeeDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepo {
    List<EmployeeListDTO> getEmployeePageDTO(int limit, int offset);
    int getTotalPages(int limit);
    boolean insertEmployee (CreateEmployeeDTO createEmployeeDTO);
    boolean updateEmployee (UpdateEmployeeDTO updateEmployeeDTO);
    boolean deleteEmployee (int id);
    List<EmployeeListDTO> searchEmployeePageDTO(String keyword, int limit, int offset);
    Optional<Employee> findEmployeeByEmail (String email);
    Optional<Employee> findEmployeeByPhone (String phone);
    Optional<Employee> findEmployeeById (int id);
}
