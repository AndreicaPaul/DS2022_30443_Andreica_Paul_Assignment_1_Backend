package com.example.first_swing_app.security.jwt;

import com.example.first_swing_app.models.User;
import com.example.first_swing_app.security.dto.AuthenticatedUserDto;
import com.example.first_swing_app.security.dto.LoginRequest;
import com.example.first_swing_app.security.dto.LoginResponse;
import com.example.first_swing_app.security.mapper.UserMapper;
import com.example.first_swing_app.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService {

    private final UserService userService;

    private final JwtTokenManager jwtTokenManager;

    private final AuthenticationManager authenticationManager;

    public LoginResponse getLoginResponse(LoginRequest loginRequest) {

        final String username = loginRequest.getUsername();
        final String password = loginRequest.getPassword();
        final String userRole = loginRequest.getUserRole();

        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        final AuthenticatedUserDto authenticatedUserDto = userService.findAuthenticatedUserByUsername(username);

        final User user = UserMapper.INSTANCE.convertToUser(authenticatedUserDto);
        if(!Objects.equals(user.getUserRole().toString(), userRole)) {
            throw new IllegalArgumentException("User role is does not match");
        }
        final String token = jwtTokenManager.generateToken(user);

        log.info(" {} has successfully logged in!", user.getUsername());

        return new LoginResponse(token);
    }

}
