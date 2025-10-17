package com.example.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/*
** Anotasi @RestController: Mendaftarkan kelas ini sebagai Spring Bean yang menangani REST requests.
*/
@RestController

public class home {

  
  /*
  ** Anotasi @GetMapping: Memetakan method ini ke HTTP GET request pada path "/" Home/Main. 
  */
  @GetMapping("/")
  public String index() {

    String tableList = """
        <table border="1">
          <tr>
            <th>Endpoint</th>
            <th>Deskripsi</th>
          </tr>
          <tr>
            <td>/hello-world</td>
            <td>Menampilkan pesan selamat datang.</td>
          </tr>
          <tr>
            <td>/goodbye</td>
            <td>Menampilkan pesan perpisahan.</td>
          </tr>
        </table>
        """;
    
    String htmlContent = """
        <html>
          <head>
            <title>Selamat Datang di Aplikasi Spring Boot</title>
          </head>
          <body>
            <h1>Selamat datang di aplikasi Spring Boot!</h1>
            <p>Gunakan endpoint <code>/hello-world</code> untuk melihat pesan selamat datang.</p>
            <p>Gunakan endpoint <code>/goodbye</code> untuk melihat pesan perpisahan.</p>
            <h2>Daftar Endpoint:</h2>
            """ + tableList + """
          </body>
        </html>
        """;
    return htmlContent;
  }
}
