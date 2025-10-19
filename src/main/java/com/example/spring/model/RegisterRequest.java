package com.example.spring.model;

public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String fullName;
    
    // Wajib: Default Constructor
    public RegisterRequest() {}

    // --- Getters dan Setters (Wajib) ---
    // (Anda bisa membuat ini secara otomatis di IDE atau ketik manual)
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public char[] getCreatedBy() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getCreatedBy'");
    }
}