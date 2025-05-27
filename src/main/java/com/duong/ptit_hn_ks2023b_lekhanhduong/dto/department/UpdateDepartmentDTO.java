package com.duong.ptit_hn_ks2023b_lekhanhduong.dto.department;

import com.duong.ptit_hn_ks2023b_lekhanhduong.model.DepartmentStatus;
import com.duong.ptit_hn_ks2023b_lekhanhduong.validate.department.UniqueDepartmentNameUpdate;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@UniqueDepartmentNameUpdate
public class UpdateDepartmentDTO {
    private int id;
    @NotBlank(message = "Name cannot be blank!")
    private String name;

    @NotBlank(message = "Description cannot be blank!")
    private String description;
    private DepartmentStatus status;
}
