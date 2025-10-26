package com.example.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

/*
** Hanya memuat Controller yang spesifik, mengisolasi test.
*/
@WebMvcTest(examStatusCodeController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ExamStatusCodeControllerTest {

  /*
  ** Inject MockMvc yang akan mensimulasikan permintaan HTTP ke Controller.
  */
  @Autowired
  private MockMvc mockMvc;

  /*
  ** IP Klien yang akan disimulasikan dalam pengujian.
  */
  private final String MOCK_CLIENT_IP = "192.168.1.100";
  
  /*
  ** Content Type yang diharapkan dalam respons JSON.
  */
  private final String JSON_CONTENT_TYPE = "application/json";
  
  /**
  ** Test Case 1: Pengujian Kode Status 200 (OK)
  */
  @Test
  void testGetStatusCode200() throws Exception {
      
    /*
    ** Melakukan permintaan GET ke endpoint, 
    ** mensimulasikan IP klien
    */
    mockMvc.perform(get("/api/data/status/200")
    .remoteAddress(MOCK_CLIENT_IP))
    
    /*
    ** Assertion:
    ** Memastikan HTTP Status Code yang dikembalikan adalah 200 (OK)
    */
    .andExpect(status().isOk())
    
    /*
    ** Assertion:
    ** Memastikan Content Type adalah application/json
    */
    .andExpect(content().contentType(JSON_CONTENT_TYPE))
    
    /*
    ** Assertion:
    ** Memastikan respons berisi substring penting
    */ 
    .andExpect(result -> {
        String responseBody = result.getResponse().getContentAsString();
        assertTrue(responseBody.contains("\"status_code\": 200"), "Response harus berisi status code 200.");
        assertTrue(responseBody.contains("\"result\": \"SUKSES\""), "Response harus berisi result SUKSES.");
        assertTrue(responseBody.contains("\"IP\": \"" + MOCK_CLIENT_IP + "\""), "Response harus berisi IP klien yang disimulasikan.");
    });
  }
  
  /**
  ** Test Case 2: Pengujian Kode Status 404 (NOT FOUND)
  */
  @Test
  void testGetStatusCode404() throws Exception {
      
    /*
    ** Melakukan permintaan GET ke endpoint 
    */
    mockMvc.perform(get("/api/data/status/404"))
            
    /*
    ** Assertion: 
    ** Memastikan HTTP Status Code yang dikembalikan adalah 404 (NOT FOUND)
    */
    .andExpect(status().isNotFound())
    
    /*
    ** Assertion:
    ** Memastikan Content Type adalah application/json
    */
    .andExpect(content().contentType(JSON_CONTENT_TYPE))
    
    /*
    ** Assertion:
    ** Memastikan respons berisi substring penting
    */
    .andExpect(result -> {
        String responseBody = result.getResponse().getContentAsString();
        assertTrue(responseBody.contains("\"status_code\": 404"), "Response harus berisi status code 404.");
        assertTrue(responseBody.contains("\"error_type\": \"Client Error\""), "Response harus berisi error_type Client Error.");
    });
  }

  /*
  ** Test Case 3: Pengujian Kode Status Default (400 - BAD REQUEST)
  */
  @Test
  void testGetStatusCodeDefault() throws Exception {
      
    /*
    ** Menguji dengan kode status yang tidak didefinisikan (misalnya 401)
    */
    mockMvc.perform(get("/api/data/status/401"))
            
    /*
    ** Assertion: 
    ** Memastikan HTTP Status Code yang dikembalikan adalah 400 (BAD REQUEST)
    */
    .andExpect(status().isBadRequest()) 
    
    /*
    ** Assertion:
    ** Memastikan Content Type adalah application/json
    */
    .andExpect(content().contentType(JSON_CONTENT_TYPE))
    
    /*
    ** Assertion:
    ** Memastikan respons berisi substring penting
    */
    .andExpect(result -> {
        String responseBody = result.getResponse().getContentAsString();
        assertTrue(responseBody.contains("\"status_code\": 400"), "Response harus berisi status code 400 dari case default.");
        assertTrue(responseBody.contains("\"error_type\": \"Invalid Input\""), "Response harus berisi error_type Invalid Input.");
    });
  }
}