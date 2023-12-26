package org.example.services;

import org.example.Exception.InvalidDetailsFormat;
import org.example.data.model.User;
import org.example.data.repository.UserRepository;
import org.example.dto.RegisterRequest;
import org.example.util.EncryptPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.example.util.Verification.*;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public Long register(RegisterRequest registerRequest) {
        if(!(verifyEmail(registerRequest.getEmail())))throw new InvalidDetailsFormat("Wrong Email Format");
        if(!(verifyPassword(registerRequest.getPassword())))throw new InvalidDetailsFormat("Invalid Password");
        if(!(verifyPhoneNumber(registerRequest.getPhoneNumber())))throw new InvalidDetailsFormat("Invalid Phone Number");
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setPassword(EncryptPassword.hashPassword(registerRequest.getPassword()));
        userRepository.save(user);
        return user.getId();
    }
}
