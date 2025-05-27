package com.duong.ptit_hn_ks2023b_lekhanhduong.dto.employee;

import com.duong.ptit_hn_ks2023b_lekhanhduong.model.EmployeeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeListDTO {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String avatar;
    private EmployeeStatus status;
    private LocalDateTime create_at;
    private String departmentName;
}
