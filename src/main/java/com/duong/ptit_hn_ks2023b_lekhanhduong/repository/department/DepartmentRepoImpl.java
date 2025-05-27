package com.duong.ptit_hn_ks2023b_lekhanhduong.repository.department;

import com.duong.ptit_hn_ks2023b_lekhanhduong.connection.DatabaseConnection;
import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.department.CreateDepartmentDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.department.UpdateDepartmentDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.model.Department;
import com.duong.ptit_hn_ks2023b_lekhanhduong.model.DepartmentStatus;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepoImpl implements DepartmentRepo{
    private Department extractResult (ResultSet rs) throws SQLException{
        Department d = new Department();
        d.setId(rs.getInt("id"));
        d.setName(rs.getString("name"));
        d.setDescription(rs.getString("description"));
        d.setStatus(DepartmentStatus.valueOf(rs.getString("status")));
        return d;
    }

    @Override
    public List<Department> getAllDepartment() {
        List<Department> departments = new ArrayList<>();
        try (
                Connection c = DatabaseConnection.connection();
                CallableStatement call = c.prepareCall("{call sp_get_all_department()}")
        ) {
            try (ResultSet rs = call.executeQuery()){
                while (rs.next()){
                    departments.add(extractResult(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định " + e.getMessage());
        }
        return departments;
    }

    @Override
    public boolean insertDepartment(CreateDepartmentDTO createDepartmentDTO) {
        try (
                Connection c = DatabaseConnection.connection();
                CallableStatement call = c.prepareCall("{call sp_insert_department (?,?)}")
        ) {
            call.setString(1,createDepartmentDTO.getName());
            call.setString(2,createDepartmentDTO.getDescription());

            return call.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateDepartment(UpdateDepartmentDTO updateDepartmentDTO) {
        try (
                Connection c = DatabaseConnection.connection();
                CallableStatement call = c.prepareCall("{call sp_update_department (?,?,?,?)}")

        ) {
            call.setInt(1,updateDepartmentDTO.getId());
            call.setString(2,updateDepartmentDTO.getName());
            call.setString(3,updateDepartmentDTO.getDescription());
            call.setString(4,updateDepartmentDTO.getStatus().toString());

            return call.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteDepartment(int id) {
        try (
                Connection c = DatabaseConnection.connection();
                CallableStatement call = c.prepareCall("{call sp_delete_department (?)}")
        ) {
            call.setInt(1,id);

            return call.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Department> searchDepartmentByNameLike(String name) {
        List<Department> departments = new ArrayList<>();
        try (
                Connection c = DatabaseConnection.connection();
                CallableStatement call = c.prepareCall("{call sp_search_department_by_name_like(?)}")
        ) {
            call.setString(1, name);
            try (ResultSet rs = call.executeQuery()) {
                while (rs.next()) {
                    departments.add(extractResult(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định " + e.getMessage());
        }
        return departments;
    }


    @Override
    public Optional<Department> findDepartmentByName(String name) {
        try (
                Connection c = DatabaseConnection.connection();
                CallableStatement call = c.prepareCall("{call sp_find_department_by_name(?) }")
        ) {
            call.setString(1,name);
            try (ResultSet rs = call.executeQuery()){
                if(rs.next()){
                    return Optional.of(extractResult(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Department> findDepartmentById(int id) {
        try (
                Connection c = DatabaseConnection.connection();
                CallableStatement call = c.prepareCall("{call sp_find_department_by_id(?) }")
        ) {
            call.setInt(1,id);
            try (ResultSet rs = call.executeQuery()){
                if(rs.next()){
                    return Optional.of(extractResult(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public int countEmployeeInDepartment(int id) {
        try (
                Connection c = DatabaseConnection.connection();
                CallableStatement call = c.prepareCall("{call sp_check_department_empty (?)}")
        ) {
            call.setInt(1,id);
            try (ResultSet rs = call.executeQuery()){
                if(rs.next()) return rs.getInt("countEmployee");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định " + e.getMessage());
        }
        return -1;
    }
}
