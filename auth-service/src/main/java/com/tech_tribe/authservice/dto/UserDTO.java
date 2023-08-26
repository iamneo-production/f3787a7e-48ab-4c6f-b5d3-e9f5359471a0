package com.tech_tribe.authservice.dto;

import com.tech_tribe.authservice.entity.User;
import com.tech_tribe.authservice.utility.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {
    @NotNull
    private Integer id;
    @NotNull
    private String username;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String email;
    @NotNull
    private UserRole role;

    public static UserDTO from(User user){
        return builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
