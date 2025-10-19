package com.example.spring.controller;

import java.util.Map; // Murni POJO

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.model.AuthResponse;
import com.example.spring.model.RegisterRequest;
import com.example.spring.model.UserModel;
import com.example.spring.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("/generate/token")
  public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
    try {
      /*
      ** Register User dan Simpan ke Database,
      ** Mengembalikan UserModel (POJO) 
      */
      UserModel newUserModel = authService.registerUser(request); 
      
      /*
      ** Generate JWT Token berdasarkan UserModel yang baru didaftarkan
      ** Mengembalikan String token 
      */
      String token = authService.generateToken(newUserModel.getUsername());

      /*
      ** Buat response berisi token dan username
      ** Mengembalikan AuthResponse (POJO)
      */
      AuthResponse response = new AuthResponse(token, newUserModel.getUsername());
      
      return new ResponseEntity<>(response, HttpStatus.CREATED);

    } catch (RuntimeException e) {
        return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }
}