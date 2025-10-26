package com.example.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.model.AuthResponse;
import com.example.spring.model.RegisterRequest;
import com.example.spring.model.UserModel;
import com.example.spring.service.AuthService;
import com.example.spring.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private AuthService authService;
    @Autowired private JwtUtil jwtUtil; 
    @Autowired private UserDetailsService userDetailsService; 

    /*
    ** Endpoint Register/Generate Token (POST)
    */
    @PostMapping("/generate/token")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            UserModel newUserModel = authService.registerUser(request); 
            
            String accessToken = authService.generateToken(newUserModel.getUsername());
            String refreshToken = authService.generateRefreshToken(newUserModel.getUsername()); 

            AuthResponse response = new AuthResponse(accessToken, refreshToken);
            
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    
    /*
    ** Endpoint Refresh/Generate Token (GET)
    */
    @GetMapping("/refresh")
    public ResponseEntity<?> refreshToken(
      @RequestParam("username") String email) { 
        
      try {
          AuthResponse response = authService.generateNewTokensByUsername(email); 
          
          return ResponseEntity.ok(response);
          
      } catch (UsernameNotFoundException e) {
          
        return new ResponseEntity<>(Map.of("error", "Email not registered or user not found."), HttpStatus.NOT_FOUND); 
      } catch (Exception e) {
        
        return new ResponseEntity<>(Map.of("error", "An unexpected server error occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); 
      }
    }
}