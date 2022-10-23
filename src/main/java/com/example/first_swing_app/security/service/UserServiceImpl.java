package com.example.first_swing_app.security.service;
import com.example.first_swing_app.models.User;
import com.example.first_swing_app.models.UserRole;
import com.example.first_swing_app.repositories.UserRepository;
import com.example.first_swing_app.security.dto.AuthenticatedUserDto;
import com.example.first_swing_app.security.dto.RegistrationRequest;
import com.example.first_swing_app.security.dto.RegistrationResponse;
import com.example.first_swing_app.security.mapper.UserMapper;
import com.example.first_swing_app.services.UserValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String REGISTRATION_SUCCESSFUL = "registration_successful";

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserValidationService userValidationService;


    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public RegistrationResponse registration(RegistrationRequest registrationRequest) {

        userValidationService.validateUser(registrationRequest);

        final User user = UserMapper.INSTANCE.convertToUser(registrationRequest);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(Objects.equals(registrationRequest.getUserRole(), UserRole.ADMIN.toString())) {
            user.setUserRole(UserRole.ADMIN);
        } else {
            user.setUserRole(UserRole.USER);
        }

        userRepository.save(user);

        final String username = registrationRequest.getUsername();

        log.info("{} registered successfully!", username);

        return new RegistrationResponse(REGISTRATION_SUCCESSFUL);
    }

    @Override
    public AuthenticatedUserDto findAuthenticatedUserByUsername(String username) {

        final User user = findByUsername(username);

        return UserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
    }
}
