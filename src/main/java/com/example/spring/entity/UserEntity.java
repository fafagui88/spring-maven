package com.example.spring.entity;

/*
** --- Import JPA Annotations (Wajib) ---
** Untuk mendefinisikan Entity dan mapping ke tabel database.
** Dokumentasi: https://jakarta.ee/specifications/persistence/3.1/jakarta-persistence-spec-3.1.html
** alasan menggunakan 'jakarta.persistence' karena Spring Boot 3.x sudah menggunakan Jakarta EE 9+.
** Jakarta EE: https://jakarta.ee/about/ adalah evolusi dari Java EE (Enterprise Edition).
*/ 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
    
  @Column(name = "_token")
  private String token;
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(unique = true, nullable = false)
  private String username;
  
  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password; 

  @Column(name = "fullname")
  private String fullName;

  /*
  ** --- Default Constructor (Wajib) ---
  */
  public UserEntity() {}
  
  /*
  ** --- Getters dan Setters (Wajib) ---
  */
  public String getToken() { return token; }
  public void setToken(String token) { this.token = token; }
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }
  public String getFullName() { return fullName; }
  public void setFullName(String fullName) { this.fullName = fullName; }
}