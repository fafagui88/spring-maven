package com.example.spring.config;

// 💡 
import java.io.IOException;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.spring.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

      final String authHeader = request.getHeader("Authorization");
      final String jwt;
      final String username;

      if (authHeader == null || !authHeader.startsWith("Bearer ")) {
          filterChain.doFilter(request, response);
          return;
      }

      jwt = authHeader.substring(7);
      
      try {
          /*
          ** Memanggil metode dari JwtUtil
          */
          username = jwtUtil.extractUsername(jwt); 
      } catch (Exception e) {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
          response.getWriter().write("Invalid or Expired Token");
          return;
      }

      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
          
          UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

          /*
          ** Memanggil metode dari JwtUtil
          */ 
          if (jwtUtil.validateToken(jwt, userDetails)) { 
              
              UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                      userDetails,
                      null,
                      userDetails.getAuthorities() 
              );
              authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

              SecurityContextHolder.getContext().setAuthentication(authToken);
          }
      }
      
      filterChain.doFilter(request, response);
  }
}