package com.example.spring.service;

import java.util.Optional; // Murni POJO

import org.springframework.beans.factory.annotation.Autowired; // Entity JPA
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring.entity.UserEntity;
import com.example.spring.model.RegisterRequest;
import com.example.spring.model.UserModel;
import com.example.spring.repository.UserRepository;
import com.example.spring.util.JwtUtil;

@Service
public class AuthService {

  @Autowired private UserRepository userRepository;
  @Autowired private BCryptPasswordEncoder passwordEncoder;
  @Autowired private JwtUtil jwtUtil;

  public UserModel registerUser(RegisterRequest request) { 
      
    /*
    ** validasi ke database duplikasi data di field username.
    ** Jika ditemukan, ambil username dan token lama untuk ditampilkan di error.
    */
    Optional<UserEntity> existingUser = userRepository.findByUsername(request.getUsername());
    
    if (existingUser.isPresent()) {
        UserEntity user = existingUser.get();
        /*
        ** Tampilkan username dan token 
        ** yang sudah ada di database 
        */
        String errorMessage = String.format(
            "Username sudah terdaftar. Username: %s, Token Tersimpan: %s",
            user.getUsername(),
            user.getToken() != null ? user.getToken() : "Belum Ada Token"
        );
        throw new RuntimeException(errorMessage);
    }

    /*
    ** Mapping dari Request ke Entity JPA 
    */
    UserEntity entity = new UserEntity();
    entity.setUsername(request.getUsername());
    entity.setEmail(request.getEmail());
    entity.setFullName(request.getFullName());
    entity.setPassword(passwordEncoder.encode(request.getPassword()));
    
    /*
    ** Save ke Database 
    */
    UserEntity savedEntity = userRepository.save(entity);
    
    /*
    ** UPDATE TOKEN DI DATABASE
    ** Generate Token
    */
    String token = generateToken(savedEntity.getUsername());
    
    userRepository.updateTokenByUserId(savedEntity.getId(), token);

    /*
    ** Mapping kembali dari Entity ke POJO (UserModel) 
    ** untuk dikirim ke Controller
    */
    UserModel userModel = new UserModel(); 
    userModel.setId(savedEntity.getId());
    userModel.setUsername(savedEntity.getUsername());
    userModel.setEmail(savedEntity.getEmail());
    userModel.setFullName(savedEntity.getFullName());
    userModel.setPassword(savedEntity.getPassword());
    userModel.setToken(token);

    return userModel; 
  }
  
  public String generateToken(String username) {
    return jwtUtil.generateToken(username);
  }
}