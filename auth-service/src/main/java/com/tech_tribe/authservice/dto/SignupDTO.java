package com.tech_tribe.authservice.dto;

import com.tech_tribe.authservice.utility.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
public class SignupDTO {
    @NotNull
    private String username;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NonNull
    private Integer age;
    @NonNull
    private String address;
    @NonNull
    private String contactNumber;
    @NonNull
    private String nic;
    @NotNull
    private String email;

    private UserRole role;
    @NotNull
    private String password;
}
