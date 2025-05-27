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
@UniqueEmailUpdate
@UniquePhoneUpdate
public class UpdateEmployeeDTO {

    private int id;

    @NotBlank(message = "Name cannot be blank!")
    private String name;

    @NotBlank(message = "Email cannot be blank!")
    @ValidEmail
    private String email;

    @NotBlank(message = "Phone cannot be blank!")
    @ValidPhone
    private String phone;

    private String avatar;

    @NotNull(message = "Status must not be null!")
    private EmployeeStatus status;

    private LocalDateTime create_at;

    @Min(value = 1, message = "Please select a department!")
    private int department_id;

    private MultipartFile imageFile;

    public UpdateEmployeeDTO(int id, String name, String email, String phone, String avatar, EmployeeStatus status, LocalDateTime create_at, int department_id) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.status = status;
        this.create_at = create_at;
        this.department_id = department_id;
    }
}

