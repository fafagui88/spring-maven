package com.example.spring.controller;

/*
** Import model yang dibuat di package model
*/ 
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.model.User;

@RestController
@RequestMapping("/api/data")

public class UserController {
  
  /*
  ** Inisiasi encoder untuk hashing password (BEST PRACTICE: Gunakan @Autowired)
  ** Dalam contoh sederhana ini, kita inisiasi langsung
  */ 
  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  private final String passwordToHash = "123";

  /**
  ** Menangani permintaan GET ke /api/data/users
  ** dan mengembalikan List<User> yang akan di-encode otomatis ke JSON Array.
  ** @return List<User> dummy data array object.
  */
  
  @GetMapping("/users")
  public List<User> getDummyUsers() {

    String hashedPassword = passwordEncoder.encode(passwordToHash);
    LocalDateTime currentDateTime = LocalDateTime.now();
    String exampleUpdateDate = "2025-10-17";

    List<User> users = new ArrayList<>();

    /*
    ** Objek User 1: hans
    */
    users.add(new User(
        10, 
        "hans", 
        hashedPassword, // Password 123 sudah di-hashing BCrypt
        currentDateTime, // Tanggal saat ini (Current)
        1, 
        exampleUpdateDate, // Contoh tanggal Y-m-d
        1, 
        "unverify"
    ));

    /*
    ** Objek User 2: ariq
    */
    users.add(new User(
        11, 
        "ariq", 
        hashedPassword, // Password 123 yang sama sudah di-hashing
        currentDateTime, // Tanggal saat ini (Current)
        1, 
        exampleUpdateDate, // Contoh tanggal Y-m-d
        1, 
        "verifyed"
    ));
    
    /*
    ** Spring secara otomatis akan mengubah List<User> 
    ** ini menjadi JSON Array
    */
    return users;
  }
}
