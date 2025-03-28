# 🛠️ QA Automation - Login Page Testing

## 📌 Apa yang Diuji?
Proyek ini adalah **automated test** untuk fitur **Login** pada situs **[OrangeHRM Demo](https://opensource-demo.orangehrmlive.com/)**.  
Berikut skenario pengujian yang dilakukan:

- ✅ **Login dengan kredensial valid** (username & password benar)
- ❌ **Login dengan kredensial tidak terdaftar** (username & password salah)
- ❌ **Login dengan input kosong** (tidak mengisi username/password)
- ❌ **SQL Injection pada input login**
- ✅ **Validasi elemen di halaman login** (logo, input fields, tombol login, dll.)

---

## 🛠️ Tools yang Digunakan
- **IntelliJ IDEA** - IDE untuk pengembangan
- **Java (JDK 21+)** - Bahasa pemrograman
- **Selenium WebDriver** - Automasi browser
- **TestNG** - Framework untuk menjalankan test
- **Maven** - Manajemen dependensi
- **ExtentReports** - Generate laporan hasil testing
- **Jenkins** - CI/CD untuk menjalankan test otomatis
- **GitHub Actions** - Alternatif CI/CD jika diperlukan

---

## 🚀 Cara Menjalankan Test

### **1️⃣ Clone Repository**
Clone repositori ini ke lokal dengan perintah:
```sh
git clone https://github.com/username/QA-Automation-Login.git
cd QA-Automation-Login