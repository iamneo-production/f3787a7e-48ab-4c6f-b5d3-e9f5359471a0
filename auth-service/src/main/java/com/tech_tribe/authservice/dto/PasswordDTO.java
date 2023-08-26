package com.tech_tribe.authservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDTO {
    @NotNull
    private String currentpassword;
    @NotNull
    private String newpassword;
}
