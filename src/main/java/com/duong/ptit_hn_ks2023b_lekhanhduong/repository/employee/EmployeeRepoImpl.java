package com.duong.ptit_hn_ks2023b_lekhanhduong.repository.employee;

import com.duong.ptit_hn_ks2023b_lekhanhduong.connection.DatabaseConnection;
import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.employee.CreateEmployeeDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.employee.EmployeeListDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.employee.UpdateEmployeeDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.model.Employee;
import com.duong.ptit_hn_ks2023b_lekhanhduong.model.EmployeeStatus;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepoImpl implements EmployeeRepo {
    private static final DateTimeFormatter MYSQL_DATETIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Employee extractResult(ResultSet rs) throws SQLException {
        Employee e = new Employee();
        e.setId(rs.getInt("id"));
        e.setName(rs.getString("name"));
        e.setEmail(rs.getString("email"));
        e.setPhone(rs.getString("phone"));
        e.setAvatar(rs.getString("avatar"));
        e.setStatus(EmployeeStatus.valueOf(rs.getString("status")));

        String createdAtStr = rs.getString("created_at");
        if (createdAtStr != null) {
            e.setCreate_at(LocalDateTime.parse(createdAtStr, MYSQL_DATETIME_FORMAT));
        }

        e.setDepartment_id(rs.getInt("department_id"));
        return e;
    }

    private EmployeeListDTO extractListDTO(ResultSet rs) throws SQLException {
        EmployeeListDTO dto = new EmployeeListDTO();
        dto.setId(rs.getInt("id"));
        dto.setName(rs.getString("name"));
        dto.setEmail(rs.getString("email"));
        dto.setPhone(rs.getString("phone"));
        dto.setAvatar(rs.getString("avatar"));
        dto.setStatus(EmployeeStatus.valueOf(rs.getString("status")));

        String createdAtStr = rs.getString("created_at");
        if (createdAtStr != null) {
            dto.setCreate_at(LocalDateTime.parse(createdAtStr, MYSQL_DATETIME_FORMAT));
        }

        dto.setDepartmentName(rs.getString("department_name"));
        return dto;
    }


    @Override
    public List<EmployeeListDTO> getEmployeePageDTO(int limit, int offset) {
        List<EmployeeListDTO> list = new ArrayList<>();
        try (
                Connection c = DatabaseConnection.connection();
                CallableStatement call = c.prepareCall("{call sp_get_employee_page(?, ?)}")
        ) {
            call.setInt(1, limit);
            call.setInt(2, offset);

            try (ResultSet rs = call.executeQuery()) {
                while (rs.next()) {
                    list.add(extractListDTO(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định " + e.getMessage());
        }
        return list;
    }

    @Override
    public int getTotalPages(int limit) {
        try (
                Connection c = DatabaseConnection.connection();
                CallableStatement call = c.prepareCall("{call sp_get_total_employee_pages(?)}")
        ) {
            call.setInt(1, limit);

            try (ResultSet rs = call.executeQuery()) {
                if (rs.next()) return rs.getInt("total_pages");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định " + e.getMessage());
        }
        return 0;
    }


    @Override
    public List<EmployeeListDTO> searchEmployeePageDTO(String keyword, int limit, int offset) {
        List<EmployeeListDTO> list = new ArrayList<>();
        try (
                Connection c = DatabaseConnection.connection();
                CallableStatement call = c.prepareCall("{call sp_search_employee_page(?, ?, ?)}")
        ) {
            call.setString(1, keyword);
            call.setInt(2, limit);
            call.setInt(3, offset);

            try (ResultSet rs = call.executeQuery()) {
                while (rs.next()) {
                    list.add(extractListDTO(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định " + e.getMessage());
        }
        return list;
    }


    @Override
    public boolean insertEmployee(CreateEmployeeDTO createEmployeeDTO) {
        try (
                Connection c = DatabaseConnection.connection();
                CallableStatement call = c.prepareCall("{call sp_insert_employee(?,?,?,?,?,?)}");
        ) {
            call.setString(1, createEmployeeDTO.getName());
            call.setString(2, createEmployeeDTO.getEmail());
            call.setString(3, createEmployeeDTO.getPhone());
            call.setString(4, createEmployeeDTO.getAvatar());
            call.setString(5, createEmployeeDTO.getStatus().toString());
            call.setInt(6, createEmployeeDTO.getDepartment_id());

            return call.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateEmployee(UpdateEmployeeDTO updateEmployeeDTO) {
        try (
                Connection c = DatabaseConnection.connection();
                CallableStatement call = c.prepareCall("{call sp_update_employee(?,?,?,?,?,?,?)}");
        ) {
            call.setInt(1, updateEmployeeDTO.getId());
            call.setString(2, updateEmployeeDTO.getName());
            call.setString(3, updateEmployeeDTO.getEmail());
            call.setString(4, updateEmployeeDTO.getPhone());
            call.setString(5, updateEmployeeDTO.getAvatar());
            call.setString(6, updateEmployeeDTO.getStatus().toString());
            call.setInt(7, updateEmployeeDTO.getDepartment_id());

            return call.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(int id) {
        try (
                Connection c = DatabaseConnection.connection();
                CallableStatement call = c.prepareCall("{call sp_delete_employee(?)}");
        ) {
            call.setInt(1, id);
            return call.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định " + e.getMessage());
        }
        return false;
    }



    @Override
    public Optional<Employee> findEmployeeByEmail(String email) {
        try (
                Connection c = DatabaseConnection.connection();
                CallableStatement call = c.prepareCall("{call sp_find_employee_by_email(?)}")
        ) {
            call.setString(1, email);
            try (ResultSet rs = call.executeQuery()) {
                if (rs.next()) return Optional.of(extractResult(rs));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Employee> findEmployeeByPhone(String phone) {
        try (
                Connection c = DatabaseConnection.connection();
                CallableStatement call = c.prepareCall("{call sp_find_employee_by_phone(?)}")
        ) {
            call.setString(1, phone);
            try (ResultSet rs = call.executeQuery()) {
                if (rs.next()) return Optional.of(extractResult(rs));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Employee> findEmployeeById(int id) {
        try (
                Connection c = DatabaseConnection.connection();
                CallableStatement call = c.prepareCall("{call sp_find_employee_by_id(?)}")
        ) {
            call.setInt(1, id);
            try (ResultSet rs = call.executeQuery()) {
                if (rs.next()) return Optional.of(extractResult(rs));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định " + e.getMessage());
        }
        return Optional.empty();
    }

}
