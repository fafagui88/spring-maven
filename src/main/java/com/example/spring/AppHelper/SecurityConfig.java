package com.example.spring.AppHelper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
            /*
            ** Otorisasi permintaan HTTP
            */
            .authorizeHttpRequests(authorize -> authorize
                /*
                ** IZINKAN SEMUA AKSES untuk endpoint API Anda dan endpoint error
                */
                .requestMatchers("/").permitAll() 
                .requestMatchers("/api/data/users").permitAll() 
                .requestMatchers("/api/data/array").permitAll() 
                .requestMatchers("/error").permitAll()

                /*
                ** Semua permintaan lainnya harus diautentikasi
                ** Untuk permintaan lainnya, tetap memerlukan otentikasi
                */
                .anyRequest().authenticated()
            )
            
            /*
            ** Nonaktifkan Form Login (agar tidak ada redirect ke halaman login)
            */
            .formLogin(AbstractHttpConfigurer::disable)
            /*
            ** Nonaktifkan CSRF (Wajib untuk REST API)
            */
            .csrf(AbstractHttpConfigurer::disable);
            
        return http.build();
      }  
}
