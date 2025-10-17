package com.example.spring;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
public class arrayToJsonController {
  /**
   * Menangani permintaan GET ke /api/data/array
   * dan mengembalikan List<String> yang akan di-encode otomatis ke JSON.
   * @return List<String> dummy data array object.
   */
  @GetMapping("/array")
  public List<String> getDummyArray() {
    /*
    ** Buat Dummy Data Array Object (menggunakan List<String>)
    */
    List<String> dummyData = Arrays.asList("Ariq", "Hans", "Gumilar", "Dewi", "Siti");

    /*
    ** Kembalikan List<String>
    ** yang akan di-encode
    ** menjadi JSON Array oleh Spring library saat dikirim sebagai response.
    */
    return dummyData;
    }
}
