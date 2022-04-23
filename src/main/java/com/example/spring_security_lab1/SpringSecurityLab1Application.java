package com.example.spring_security_lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class SpringSecurityLab1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityLab1Application.class, args);
    }

}
