package com.example.spring.controller;

import com.example.spring.model.RegisterModel;
import com.example.spring.model.RegisterRequest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegisterController.class) 

@WithMockUser
public class RegisterControllerTest {
    
  @Autowired
  private MockMvc mockMvc;

  @MockBean 
  private RegisterModel registerModel;

  private static class DummyMember extends RegisterModel.Member {
    public DummyMember(String id, String username, String email) {
      super(Integer.valueOf(id), username, email, LocalDateTime.now(), 0, "", 0, "");
    }
  }


  @Test
  void testRegisterUserSuccess() throws Exception {
      
    String requestBodyJson = """
        {
            "username": "ariq_gumilar",
            "email": "ariq@example.com",
            "password": "password123" 
        }
    """;
    
    DummyMember savedUserMock = new DummyMember("987", "ariq_gumilar", "ariq@example.com");

    when(registerModel.save(any(RegisterRequest.class))).thenReturn(savedUserMock);

    mockMvc.perform(post("/api/data/member/register")
    .contentType(MediaType.APPLICATION_JSON) 
    .content(requestBodyJson)
    .with(csrf())) 
    
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.status").value("success"))
    .andExpect(jsonPath("$.message").value("Pendaftaran pengguna berhasil diproses."))
    .andExpect(jsonPath("$.data").exists())
    .andExpect(jsonPath("$.data.username").value("ariq_gumilar"))
    
    .andExpect(jsonPath("$.data.id").value(987)); 
  }
}