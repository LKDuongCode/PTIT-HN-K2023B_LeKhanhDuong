package com.duong.ptit_hn_ks2023b_lekhanhduong.service.department;

import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.department.CreateDepartmentDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.department.UpdateDepartmentDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> getAllDepartment ();
    boolean insertDepartment (CreateDepartmentDTO createDepartmentDTO);
    boolean updateDepartment (UpdateDepartmentDTO updateDepartmentDTO);
    boolean deleteDepartment (int id);
    List<Department> searchDepartmentByNameLike (String name);
    Optional<Department> findDepartmentByName (String name);
    Optional<Department> findDepartmentById (int id);
    int countEmployeeInDepartment (int id);
}
