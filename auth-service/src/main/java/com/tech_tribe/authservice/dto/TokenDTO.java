package com.tech_tribe.authservice.dto;

import com.tech_tribe.authservice.utility.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO {

    private String userId;
    private UserRole role;
    private String accessToken;
    private String refreshToken;
}
