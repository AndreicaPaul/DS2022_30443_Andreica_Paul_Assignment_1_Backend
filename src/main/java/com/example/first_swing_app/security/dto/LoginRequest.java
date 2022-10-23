package com.example.first_swing_app.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {

    @NotEmpty(message = "{login_username_not_empty}")
    private String username;

    @NotEmpty(message = "{login_password_not_empty}")
    private String password;

    @NotEmpty(message = "{login_role_not_empty}")
    private String userRole;
}