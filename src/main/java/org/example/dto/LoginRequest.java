package org.example.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private Long id;
    private String  password;
}
