package com.example.first_swing_app.security.service;

import com.example.first_swing_app.models.User;
import com.example.first_swing_app.security.dto.AuthenticatedUserDto;
import com.example.first_swing_app.security.dto.RegistrationRequest;
import com.example.first_swing_app.security.dto.RegistrationResponse;

public interface UserService {

    User findByUsername(String username);

    RegistrationResponse registration(RegistrationRequest registrationRequest);

    AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

}
