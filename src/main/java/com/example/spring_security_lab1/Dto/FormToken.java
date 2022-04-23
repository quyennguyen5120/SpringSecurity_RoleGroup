package com.example.spring_security_lab1.Dto;

import lombok.Data;

@Data
public class FormToken {
    private String accessToken;
    private String tokenType = "Bearer";

    public FormToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
