package com.example.first_swing_app.security.dto;

import com.example.first_swing_app.models.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticatedUserDto {

    private String username;

    private String password;

    private UserRole userRole;

}
