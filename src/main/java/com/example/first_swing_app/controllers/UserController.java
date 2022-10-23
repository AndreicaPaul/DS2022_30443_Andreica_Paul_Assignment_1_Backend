package com.example.first_swing_app.controllers;

import com.example.first_swing_app.models.User;
import com.example.first_swing_app.security.jwt.JwtTokenManager;
import com.example.first_swing_app.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final JwtTokenManager jwtTokenManager;

    @Autowired
    private JavaMailSender emailSender;

    @PostMapping
    public ResponseEntity<User> getByToken(@RequestParam String token) {
        return ResponseEntity.ok(userService.findByUsername(jwtTokenManager.getUsernameFromToken(token)));
    }

    @GetMapping("/mail")
    public Object sendMail(){
        log.info("Sending email...");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("paulpaulandreica@gmail.com");
        message.setTo("paulpaulandreica@gmail.com");
        message.setSubject("TEST");
        message.setText("Test email from Utility Platform");
        emailSender.send(message);
        return "Email sent!";
    }
}
