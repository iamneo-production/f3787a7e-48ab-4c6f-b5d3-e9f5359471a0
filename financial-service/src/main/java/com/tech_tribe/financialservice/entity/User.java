package com.tech_tribe.financialservice.entity;
import com.tech_tribe.financialservice.utility.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private String empId;
    private UserRole role;
    private String email;

}
