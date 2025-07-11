package com.example.user_service.service;

import com.example.user_service.controller.UserController;
import com.example.user_service.data.User;
import com.example.user_service.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

@Autowired
private UserRepository userRepository;

    public boolean validateUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user != null;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}


