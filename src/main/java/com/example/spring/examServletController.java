package com.example.spring;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController

public class examServletController {
  /*
  * Metode ini mendemonstrasikan bagaimana Servlet Container
  * meneruskan objek Request dan Response secara langsung
  * (sesuai penjelasan di materi pelatihan Anda). 
  */
  @RequestMapping("/servlet/exam")
  public void handleServletExam(
      HttpServletRequest request,
      HttpServletResponse response) throws IOException {

      /*
      ** MENGAMBIL INFORMASI DARI CLIENT (HttpServletRequest)
      ** HttpServletRequest: Menyediakan HTTP Request dari client. 
      */ 
      String clientIP = request.getRemoteAddr();
      String requestMethod = request.getMethod();

      /*
      ** MENGATUR DAN MENGIRIM RESPONSE KEMBALI (HttpServletResponse)
      ** HttpServletResponse: Digunakan untuk mengembalikan response kepada client. 
      ** Mengatur tipe konten response (Wajib saat menulis response secara manual)
      */
      response.setContentType("text/html");
      
      /*
      ** Mengatur HTTP Status Code
      ** Contoh: 200 OK, 404 Not Found, 500 Internal Server Error, dll. 
      */
      response.setStatus(HttpServletResponse.SC_OK);
    //   String jsonOutput;  
    //   switch (requestMethod) {
    //       case 200: 
    //         response.setStatus(HttpServletResponse.SC_OK);
    //         jsonOutput = String.format("{\"status\": \"success\", \"message\": \"Request berhasil diproses.\"}");
        
      /*
      ** Content HTML ke body response 
      */
      response.getWriter().println(
          """
          <html>
          <head><title>Servlet Container Demo</title></head>
          <body>
              <h2>Ini adalah Demonstrasi Objek Servlet Container</h2>
              <p>Ini menunjukkan bagaimana <b>Servlet Container</b> memanggil metode layanan  
                  dan meneruskan objek <b>HttpServletRequest</b> dan <b>HttpServletResponse</b>.</p>
              <hr>
              <h3>Informasi yang Diperoleh (HttpServletRequest):</h3>
              <ul>
                  <li><b>Metode Request (GET/POST)</b>: """ + requestMethod + "</li>\n" +
          "        <li><b>IP Client</b>: " + clientIP + "</li>\n" +
          "    </ul>\n" +
          "    <p>Anda tidak perlu menuliskan objek Response secara manual seperti ini, \n" +
          "        karena Spring Boot biasanya melakukannya untuk Anda, \n" +
          "        tetapi ini adalah inti dari cara kerja Servlet Container.</p>\n" +
          "</body>\n" +
          "</html>\n"
      );
  }
}
