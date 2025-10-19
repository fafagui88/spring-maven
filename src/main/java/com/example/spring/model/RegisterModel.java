package com.example.spring.model;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterModel {

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  
  /**
   * @param request
   * @return
   */
  public Member save(RegisterRequest request) {
    
    String hashedPassword = passwordEncoder.encode(request.getPassword());
    
    Integer id = (int) (Math.random() * 10);
    String username = request.getUsername();
    String password = hashedPassword;
    String createdBy = String.valueOf(request.getCreatedBy());

    Integer createdById = Integer.valueOf(new String(request.getCreatedBy()));
    Member newUser = new Member(
        id,
        username,
        password,
        LocalDateTime.now(),
        createdById,
        createdBy,
        0,
        "" 
    );
    
    return newUser;
  }

  // Simple Member model to fix missing type error
  public static class Member {
    private final Integer id;
    private final String username;
    private final String password;
    private final LocalDateTime createdAt;
    private final Integer createdById;
    private final String createdBy;
    private final int status;

    public Member(Integer id, String username, String password, LocalDateTime createdAt,
        Integer createdById, String createdBy, int status, String note) {
      this.id = id;
      this.username = username;
      this.password = password;
      this.createdAt = createdAt;
      this.createdById = createdById;
      this.createdBy = createdBy;
      this.status = status;
    }

    public Integer getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Integer getCreatedById() { return createdById; }
    public String getCreatedBy() { return createdBy; }
    public int getStatus() { return status; }
  }
}