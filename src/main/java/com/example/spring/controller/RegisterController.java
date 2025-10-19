package com.example.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.model.RegisterModel;
import com.example.spring.model.RegisterRequest;

@RestController
@RequestMapping("/api/data")
public class RegisterController {
  
    @Autowired
    private RegisterModel registerModel;

    /**
     * @param request
     * @return
     */
    @PostMapping("/member/register") 
    public Map<String, Object> registerUser(@RequestBody RegisterRequest request) {
        
      RegisterModel.Member savedUser = registerModel.save(request);
      
      return Map.of(
          "status", "success",
          "message", "Pendaftaran pengguna berhasil diproses.",
          "data", savedUser
      );
    }
}
