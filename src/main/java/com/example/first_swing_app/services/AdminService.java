package com.example.first_swing_app.services;

import com.example.first_swing_app.models.User;
import com.example.first_swing_app.models.UserRole;
import com.example.first_swing_app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll().stream().filter((user -> user.getUserRole() != UserRole.ADMIN)).collect(Collectors.toList());
    }

    public User addUser(User user) {
        Optional<User> myUser = userRepository.findById(user.getId());
        if(myUser.isPresent()) {
            User updatedUser = myUser.get();
            updatedUser.setEmail(user.getEmail());
            updatedUser.setUsername(user.getUsername());
            return userRepository.save(updatedUser);
        } else {
            return userRepository.save(user);
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
