package org.example.services;

import org.example.dto.RegisterRequest;


public interface UserService {
    Long register(RegisterRequest registerRequest);
}
