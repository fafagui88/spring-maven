package com.example.spring.util;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

  // Nilai diambil dari application.properties
  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private long expiration; 


  public String generateToken(String username) {
      Map<String, Object> claims = new HashMap<>();
      
      return Jwts.builder()
              .setClaims(claims)
              .setSubject(username)
              .setIssuedAt(new Date(System.currentTimeMillis()))
              // Menentukan masa berlaku token
              .setExpiration(new Date(System.currentTimeMillis() + expiration)) 
              .signWith(getSigningKey(), SignatureAlgorithm.HS256)
              .compact();
  }

  private Key getSigningKey() {
      // Mengubah Base64 Secret Key menjadi objek Key
      byte[] keyBytes = Decoders.BASE64.decode(secret);
      return Keys.hmacShaKeyFor(keyBytes);
  }

  /*
  ** Fungsi ini bertugas mem-parsing, memverifikasi Signature, 
  ** dan mengekstrak seluruh Payload (Claims) dari token.
  */
  private Claims extractAllClaims(String token) {
      return Jwts
              .parserBuilder()
              .setSigningKey(getSigningKey())
              .build()
              .parseClaimsJws(token)
              .getBody();
  }

  /*
  ** Fungsi ini bertugas mengambil Subject (Username) 
  ** dari Payload token yang sudah diverifikasi.
  */
  public String extractUsername(String token) {
      return extractClaim(token, Claims::getSubject);
  }

  /*
  ** Fungsi ini adalah helper generik 
  ** untuk mengambil klaim spesifik apa pun 
  ** (seperti ID atau Role) dari Payload.
  */
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
      final Claims claims = extractAllClaims(token);
      return claimsResolver.apply(claims);
  }

  /*
  ** Fungsi ini bertugas memeriksa apakah Username di token cocok 
  ** dengan UserDetails dan memastikan token belum kedaluwarsa.
  */ 
  public Boolean validateToken(String token, UserDetails userDetails) {
      final String username = extractUsername(token);
      return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
  
  /*
  ** Fungsi ini bertugas mengambil nilai 
  ** waktu kedaluwarsa (Expiration Time) dari Payload token.
  */ 
  private Date extractExpiration(String token) {
      return extractClaim(token, Claims::getExpiration);
  }

  /*
  ** Fungsi ini bertugas memeriksa apakah waktu kedaluwarsa 
  ** token sudah berlalu (sebelum waktu saat ini).
  */ 
  private Boolean isTokenExpired(String token) {
      return extractExpiration(token).before(new Date());
  }

  /*
  ** Fungsi ini bertugas membuat Refresh Token 
  ** (saat ini menggunakan masa berlaku yang sama dengan Access Token dari @Value).
  */ 
  public String generateRefreshToken(String username) {
    return generateToken(username);
  }
    
}