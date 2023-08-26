package com.tech_tribe.goalsservice.entity;
import com.tech_tribe.goalsservice.utility.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private String empId;
    private UserRole role;
    private String email;

}
