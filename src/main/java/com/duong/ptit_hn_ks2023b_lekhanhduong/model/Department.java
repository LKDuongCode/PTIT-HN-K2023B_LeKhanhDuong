package com.duong.ptit_hn_ks2023b_lekhanhduong.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private int id;
    private String name;
    private String description;
    private DepartmentStatus status; // ACTIVE,INACTIVE
}
