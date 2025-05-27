package com.duong.ptit_hn_ks2023b_lekhanhduong.dto.employee;

import com.duong.ptit_hn_ks2023b_lekhanhduong.model.EmployeeStatus;
import com.duong.ptit_hn_ks2023b_lekhanhduong.validate.employee.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CreateEmployeeDTO {

    @NotBlank(message = "Name cannot be blank!")
    private String name;

    @NotBlank(message = "Email cannot be blank!")
    @ValidEmail
    @UniqueEmail
    private String email;

    @NotBlank(message = "Phone cannot be blank!")
    @ValidPhone
    @UniquePhone
    private String phone;

    private String avatar;

    @NotNull(message = "Status must not be null!")
    private EmployeeStatus status;

    @Min(value = 1, message = "Please select a department!")
    private int department_id;

    @ValidImage
    private MultipartFile imageFile;

    public CreateEmployeeDTO(String name, int department_id, EmployeeStatus status, String avatar, String phone, String email) {
        this.name = name;
        this.department_id = department_id;
        this.status = status;
        this.avatar = avatar;
        this.phone = phone;
        this.email = email;
    }
}
