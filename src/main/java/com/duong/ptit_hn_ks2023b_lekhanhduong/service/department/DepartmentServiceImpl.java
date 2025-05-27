package com.duong.ptit_hn_ks2023b_lekhanhduong.service.department;

import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.department.CreateDepartmentDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.department.UpdateDepartmentDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.model.Department;
import com.duong.ptit_hn_ks2023b_lekhanhduong.repository.department.DepartmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    private final DepartmentRepo departmentRepo;

    public DepartmentServiceImpl(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepo.getAllDepartment();
    }

    @Override
    public boolean insertDepartment(CreateDepartmentDTO createDepartmentDTO) {
        return departmentRepo.insertDepartment(createDepartmentDTO);
    }

    @Override
    public boolean updateDepartment(UpdateDepartmentDTO updateDepartmentDTO) {
        return departmentRepo.updateDepartment(updateDepartmentDTO);
    }

    @Override
    public boolean deleteDepartment(int id) {
        return departmentRepo.deleteDepartment(id);
    }

    @Override
    public List<Department> searchDepartmentByNameLike(String name) {
        return departmentRepo.searchDepartmentByNameLike(name);
    }

    @Override
    public Optional<Department> findDepartmentByName(String name) {
        return departmentRepo.findDepartmentByName(name);
    }

    @Override
    public Optional<Department> findDepartmentById(int id) {
        return departmentRepo.findDepartmentById(id);
    }

    @Override
    public int countEmployeeInDepartment(int id) {
        return departmentRepo.countEmployeeInDepartment(id);
    }
}
