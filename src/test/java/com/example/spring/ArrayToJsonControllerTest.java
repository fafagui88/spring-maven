package com.example.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/*
** Hanya memuat Controller yang spesifik, mengisolasi test.
*/
@WebMvcTest(arrayToJsonController.class)
@WithMockUser
public class ArrayToJsonControllerTest {

  @Autowired
  private MockMvc mockMvc;

  /**
  ** Test Case: Pengujian pengembalian List<String> sebagai JSON Array
  */
  @Test
  void testGetDummyArray() throws Exception {
      
    /*
    ** Expected JSON Array String yang diharapkan dalam response
    ** dari endpoint /api/data/array 
    */
    String expectedJson = "[\"Ariq\",\"Hans\",\"Gumilar\",\"Dewi\",\"Siti\"]";

    mockMvc.perform(get("/api/data/array"))
            
    /*
    ** Assertion: 
    ** Memastikan HTTP Status Code yang dikembalikan adalah 200 (OK)
    */
    .andExpect(status().isOk())
    
    /*
    ** Assertion:
    ** Memastikan Content Type adalah application/json
    */
    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    
    /*
    ** Assertion:
    ** Memastikan respons JSON sesuai dengan expectedJson
    */
    .andExpect(content().json(expectedJson))
    
    /*
    ** Assertion:
    ** Memastikan respons JSON adalah array dengan panjang 5
    */
    .andExpect(jsonPath("$.length()").value(5))
    
    /*
    ** Assertion:
    ** Memastikan elemen kedua dalam array adalah "Hans"
    */
    .andExpect(jsonPath("$[1]").value("Hans"));
  }
}