# 🔑 Spring Boot API: JWT Token Generation and Persistence (MySQL)

Proyek ini mengimplementasikan arsitektur dasar Spring Boot 3+ untuk REST API yang berfungsi mendaftarkan pengguna, menghasilkan JWT Token, dan menyimpan token tersebut di database MySQL.

## 🚀 Fitur Utama

- **Pendaftaran Pengguna:** Endpoint POST `/api/auth/generate/token`.
- **Keamanan Password:** Penggunaan **BCryptPasswordEncoder** untuk *hashing* *password*.
- **JWT Generation:** Pembuatan Token JWT (HS256) setelah pendaftaran berhasil menggunakan Secret Key 256-bit.
- **Token Persistence:** JWT Token yang baru dibuat **disimpan/di-update** ke *field* `_token` di tabel `users`.
- **Validasi Duplikasi Lanjutan:** Jika *username* duplikat, API mengembalikan *username* dan *token* yang sudah tersimpan di DB.

## ⚙️ Persyaratan Sistem & Setup Awal

- **Java Development Kit (JDK) 17+**
- **Apache Maven 3.6+**
- **MySQL Server**
- **Postman / REST Client**

### Database Schema (`users` table)

Tabel `users` harus memiliki *field* yang sesuai dengan `UserEntity.java`:

| Column Name | Data Type | Key/Constraints | Keterangan |
| :--- | :--- | :--- | :--- |
| `id` | `int` | Primary Key, Auto Increment | ID unik pengguna |
| `username` | `varchar(255)` | Unique, Not Null | Nama pengguna (untuk login/identifikasi) |
| `email` | `varchar(255)` | Unique, Not Null | Alamat email |
| `password` | `varchar(255)` | Not Null | Password yang sudah di-hash BCrypt |
| `_token` | `varchar(255)` | - | **Tempat menyimpan JWT Token aktif** |
| `fullName` | `varchar(255)` | Not Null | Nama lengkap pengguna |

## 📂 Struktur Proyek

Struktur folder di bawah `src/main/java/com/example/spring/` adalah sebagai berikut:

| Folder | Class Penting | Tujuan & Tanggung Jawab |
| :--- | :--- | :--- |
| `config` | `SecurityConfig.java` | Mengizinkan *endpoint* Register (`permitAll()`) dan menyediakan *Bean* Hashing. |
| `controller` | `AuthController.java` | Menangani *request* dan *response* HTTP, memanggil `AuthService`. |
| `model` | `UserModel.java` | **POJO Domain.** Digunakan sebagai model data transfer bersih (tanpa anotasi JPA). |
| `entity` | `UserEntity.java` | **JPA Entity.** Wajib menggunakan `jakarta.persistence.*` untuk *mapping* ke tabel `users`. |
| `repository` | `UserRepository.java` | Interface JPA. Bertanggung jawab untuk operasi CRUD dan *custom query* `updateTokenByUserId`. |
| `service` | `AuthService.java` | Mengandung semua logika bisnis: Validasi Duplikasi, Hashing, Generate Token, dan **Update Token ke DB**. |
| `util` | `JwtUtil.java` | Utilitas khusus untuk *encoding* dan *decoding* JWT. |

## ⚙️ Konfigurasi Kritis (`application.properties`)

```properties
# Koneksi MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/db_spring
spring.datasource.username=root
spring.datasource.password=password_anda
spring.jpa.hibernate.ddl-auto=update

# Konfigurasi JWT (MINIMAL 32 KARAKTER/256 bits!)
jwt.secret=KunciRahasiaJWTUntukProyekSpringBootAndaYangSangatSangatPanjangDanAmanSekali12345
jwt.expiration=86400000 

# Port Aplikasi
server.port=9091
