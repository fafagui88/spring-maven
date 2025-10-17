package com.example.spring;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class examStatusCodeController {


    @GetMapping("/api/status/{code}")
    public void getStatusCode(
        @PathVariable int code,
        HttpServletRequest request,
        HttpServletResponse response) throws IOException {

        String clientIP = request.getRemoteAddr();

        ZoneId serverZone = ZoneId.systemDefault();
        ZonedDateTime now = ZonedDateTime.now();

        response.setContentType("application/json"); 
        String jsonOutput;

        switch (code) {
            case 200:

                response.setStatus(HttpServletResponse.SC_OK); 
                jsonOutput = String.format("""
                    {
                      "status_code": 200,
                      "result": "SUKSES",
                      "message": "Permintaan berhasil diproses.",
                      "timestamp": "%s",
                      "now": "%s",
                      "location": "%s",
                      "IP": "%s"
                    }
                    """, 
                    LocalDateTime.now(), 
                    now.toString(), 
                    serverZone.getId(),
                    clientIP
                );
                break;
            case 404:

                response.setStatus(HttpServletResponse.SC_NOT_FOUND); 
                jsonOutput = String.format("""
                    {
                      "status_code": 404,
                      "result": "GAGAL",
                      "error_type": "Client Error",
                      "message": "Sumber daya tidak ditemukan, diatur oleh Servlet Container.",
                      "timestamp": "%s",
                      "now": "%s",
                      "location": "%s",
                      "IP": "%s"
                    }
                    """, 
                    LocalDateTime.now(), 
                    now.toString(), 
                    serverZone.getId(),
                    clientIP
                );
                break;
            case 500:

                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); 
                jsonOutput = String.format("""
                    {
                      "status_code": 500,
                      "result": "GAGAL",
                      "error_type": "Server Error",
                      "message": "Terjadi kesalahan internal yang disimulasikan.",
                      "timestamp": "%s",
                      "now": "%s",
                      "location": "%s",
                      "IP": "%s"
                    }
                    """, 
                    LocalDateTime.now(), 
                    now.toString(), 
                    serverZone.getId(),
                    clientIP
                );
                break;
            default:

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); 
                 jsonOutput = String.format("""
                    {
                      "status_code": 400,
                      "result": "GAGAL",
                      "error_type": "Invalid Input",
                      "message": "Kode status yang diminta tidak valid.",
                      "timestamp": "%s",
                      "now": "%s",
                      "location": "%s",
                      "IP": "%s"
                    }
                    """, 
                    LocalDateTime.now(), 
                    now.toString(), 
                    serverZone.getId(),
                    clientIP
                );
                break;
        }


        response.getWriter().println(jsonOutput);


    }
}