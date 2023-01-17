package com.example.first_swing_app;

import com.example.first_swing_app.controllers.SensorController;
import com.example.first_swing_app.services.RabbitMQConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaRepositories
@EnableAspectJAutoProxy
public class FirstSwingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstSwingAppApplication.class, args);
        RabbitMQConfig rabbitMQConfig = new RabbitMQConfig();
        rabbitMQConfig.subscribeToRabbitMQMessageBus();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
