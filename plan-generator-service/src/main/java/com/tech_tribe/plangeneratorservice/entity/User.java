package com.tech_tribe.plangeneratorservice.entity;

import com.tech_tribe.plangeneratorservice.utility.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private String empId;
    private UserRole role;
    private String email;

}
