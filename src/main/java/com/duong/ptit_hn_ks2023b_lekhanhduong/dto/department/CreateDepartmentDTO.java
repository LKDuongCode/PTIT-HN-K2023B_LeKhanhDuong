package com.duong.ptit_hn_ks2023b_lekhanhduong.dto.department;

import com.duong.ptit_hn_ks2023b_lekhanhduong.validate.department.UniqueDepartmentNameCreate;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateDepartmentDTO {
    @NotBlank(message = "Name cannot be blank!")
    @UniqueDepartmentNameCreate
    private String name;

    @NotBlank(message = "Description cannot be blank!")
    private String description;
}
