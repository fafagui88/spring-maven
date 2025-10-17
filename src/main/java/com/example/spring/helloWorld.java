package com.example.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
** Anotasi @RestController: Mendaftarkan kelas ini sebagai Spring Bean yang menangani REST requests.
*/
@RestController
public class helloWorld {
  /*
  ** Anotasi @GetMapping: Memetakan method ini ke HTTP GET request pada path "/hello-world". 
  */
  @GetMapping("/hello-world")
  public String sapaAplikasi() {
    /*
    ** Ini adalah data yang akan ditampilkan di browser. 
    */
    return "Spring Boot dan Maven Berfungsi! Hello, World!";
  }
}
