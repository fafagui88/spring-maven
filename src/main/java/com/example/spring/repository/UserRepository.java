package com.example.spring.repository;

/*
** Menggunakan Entity yang tersembunyi
** di dalam package 'entity'.
*/ 
import java.util.Optional; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> { 
  Optional<UserEntity> findByUsername(String username);
  Optional<UserEntity> findByEmail(String email);

  /*
  ** Custom Query untuk mengupdate field _token di database.
  ** @param userId ID dari user yang akan diupdate.
  ** @param token String JWT Token yang baru.
  ** @return jumlah baris yang terupdate (biasanya 1).
  */

  /*
  ** Wajib untuk query DML (UPDATE, DELETE)
  */ 
  @Modifying 
  
  /*
  ** Wajib untuk menjalankan DML di JPA
  ** dalam sebuah transaksional.
  */ 
  @Transactional
  @Query("UPDATE UserEntity u SET u.token = :token WHERE u.id = :userId")
  int updateTokenByUserId(@Param("userId") Long userId, @Param("token") String token);
}