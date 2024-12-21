package com.example.loginservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.loginservice.entities.Admin;
import com.example.loginservice.entities.BankerLogin;
import com.example.loginservice.entities.Login;
import com.example.loginservice.security.JwtUtil;
import com.example.loginservice.service.LoginService;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    
    @Autowired
    private JwtUtil jwtUtil;

    // Admin Login
    @PostMapping("/admin")
    public ResponseEntity<String> adminLogin(@RequestBody Admin admin) {
        UserDetails userDetails = loginService.authenticateAdmin(admin.getAdminId(), admin.getPassword());
        if (userDetails != null) {
            String token = jwtUtil.generateToken(userDetails.getUsername(), "admin");
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid Admin credentials");
    }

    // Banker Login
    @PostMapping("/bank")
    public ResponseEntity<String> bankerLogin(@RequestBody BankerLogin banker) {
        UserDetails userDetails = loginService.authenticateBanker(banker.getBankerId(), banker.getPassword());
        if (userDetails != null) {
            String token = jwtUtil.generateToken(userDetails.getUsername(), "bank");
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid Banker credentials");
    }
    // User Login
    @PostMapping("/user")
    public ResponseEntity<String> userLogin(@RequestBody Login login) {
        UserDetails userDetails = loginService.authenticateUser(login.getUserId(), login.getPassword());
        if (userDetails != null) {
            String token = jwtUtil.generateToken(userDetails.getUsername(), "user");
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid User credentials");
    }
}
