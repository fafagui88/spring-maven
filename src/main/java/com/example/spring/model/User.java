package com.example.spring.model;

import java.time.LocalDateTime;

public class User {
  private Integer id;
  private String username;
  private String password; // Contoh password hash
  private LocalDateTime createdAt; // Tanggal dan waktu saat ini (Current)
  private Integer createdBy;
  private String updatedAt; // Contoh tanggal Y-m-d
  private Integer isActive;
  private String isMember;

  public User() {}

  public User(Integer id, String username, String password, LocalDateTime createdAt, 
  Integer createdBy, String updatedAt, Integer isActive, String isMember) {

    this.id = id;
    this.username = username;
    this.password = password;
    this.createdAt = createdAt;
    this.createdBy = createdBy;
    this.updatedAt = updatedAt;
    this.isActive = isActive;
    this.isMember = isMember;
  }

  /*
  ** GETTER	
  ** @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
  ** getNamaProperti() Membaca data dari field private di dalam objek.	
  ** Pintu keluar data Model ke dunia luar (contoh: ke JSON). 
  ** Wajib ada agar Spring/Jackson bisa membaca dan mengirim data.
  ** @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
  ** SETTER
  ** @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
  ** setNamaProperti() Mengubah/Mengisi nilai ke field private di dalam objek.
  ** Pintu masuk data dari luar (contoh: dari JSON Request Body) 
  ** untuk diisi ke objek Model Java.
  */ 

  public Integer getId() { 
    return id; 
  }
  public void setId(Integer id) { 
    this.id = id; 
  }
  
  public String getUsername() { 
    return username; 
  }
  
  public void setUsername(String username) { 
    this.username = username; 
  }

  public String getPassword() { 
    return password; 
  }
  
  public void setPassword(String password) { 
    this.password = password; 
  }

  public LocalDateTime getCreatedAt() { 
    return createdAt; 
  }
  
  public void setCreatedAt(LocalDateTime createdAt) { 
    this.createdAt = createdAt; 
  }

  public Integer getCreatedBy() { 
    return createdBy; 
  }
  
  public void setCreatedBy(Integer createdBy) { 
    this.createdBy = createdBy; 
  }

  public String getUpdatedAt() { 
    return updatedAt; 
  }
  
  public void setUpdatedAt(String updatedAt) { 
    this.updatedAt = updatedAt; 
  }

  public Integer getIsActive() { 
    return isActive; 
  }
  
  public void setIsActive(Integer isActive) { 
    this.isActive = isActive; 
  }

  public String getIsMember() { 
    return isMember; 
  }
  
  public void setIsMember(String isMember) { 
    this.isMember = isMember; 
  }
}
