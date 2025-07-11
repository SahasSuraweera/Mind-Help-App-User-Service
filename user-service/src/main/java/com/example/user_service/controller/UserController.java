package com.example.user_service.controller;

import com.example.user_service.data.User;
import com.example.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        boolean valid = userService.validateUser(username, password);

        Map<String, Object> response = new HashMap<>();

        if (valid) {
            response.put("message", "Login successful");
            response.put("username", username);
            return ResponseEntity.ok(response); // HTTP 200 OK
        } else {
            response.put("message", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); // HTTP 401 Unauthorized
        }
    }

    @PostMapping("/username")
    public ResponseEntity<User> getUserByUsername(@RequestBody User user) {
        String username = user.getUsername(); // Only care about username

        if (username == null || username.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        User fullUser = userService.findByUsername(username); // fetch from DB

        if (fullUser != null) {
            return ResponseEntity.ok(fullUser); // Return full object
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
