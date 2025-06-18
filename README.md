# 🏥 Sistem Manajemen Rumah Sakit Daisuke

Sistem ini merupakan aplikasi manajemen rumah sakit berbasis Java, yang mengimplementasikan berbagai struktur data seperti **Linked List**, **Stack**, **Queue**, dan **Binary Search Tree (BST)** untuk menangani data pasien, dokter, dan appointment secara efisien.

---

## 🔧 Struktur & Fungsi Program

### 📁 `Patient.java`
- Menyimpan data pasien: ID, nama, umur, alamat, nomor telepon, riwayat penyakit.
- Digunakan sebagai node untuk linked list.
- Menyediakan format tampilan data pasien.

### 📁 `PatientList.java`
- Menggunakan **Linked List** untuk menyimpan data pasien.
- `addPatient()`: Menambah pasien baru.
- `removePatientById()`: Menghapus pasien berdasarkan ID.
- `findPatientByName()`: Mencari pasien berdasarkan nama.
- `displayPatient()`: Menampilkan seluruh data pasien.

### 📁 `Doctor.java`
- Menyimpan data dokter: ID, nama, spesialisasi, jam kerja, waktu login/logout, status absensi.
- Mencatat waktu hadir (absensi) secara otomatis menggunakan waktu sistem.

### 📁 `StackDoctor.java`
- Menyimpan dokter menggunakan **Stack** (LIFO).
- Dokter terakhir hadir berada di atas stack.
- `loginDoctor()`: Melakukan login pada dokter.
- `addDoctor()`: Menambah dokter ke dalam stack.
- `outAbsenDoctor()`: Melakukan logout pada dokter.
- `viewLastLoggedDoctor()`: Menampilkan dokter yang terakhir melakukan login.
- `listDokter()`: Menampilkan seluruh list dokter.

### 📁 `Appointment.java`
- Mewakili satu janji temu pasien-dokter.
- `setDokter()` dan `setPasien()`: Mengatur siapa yang terlibat dalam appointment.
- `setWaktu()`: Menentukan waktu janji temu.

### 📁 `AppointmentQueue.java`
- Mengelola antrian appointment dengan **Queue (FIFO)**.
- `enqueue()`: Menambahkan appointment ke antrian.
- `dequeue()`: Menghapus appointment yang telah selesai.
- `listAntrian()`: Menampilkan daftar janji temu.

### 📁 `BST.java`
- Menyimpan data pasien berdasarkan ID dalam struktur **Binary Search Tree**.
- `insertPatient()`: Menambahkan pasien ke tree.
- `searchPatient()`: Mencari pasien berdasarkan ID.
- `inOrderDisplay()`: Menampilkan semua data pasien secara urut berdasarkan ID.

### 📁 `Menu.java`
- Menangani alur utama program.
- Memuat seluruh sistem menu: login/register pasien, dokter, dan admin.
  - `pilPasien()`
    1. **Buat Appointment**
       -  Input nama dokter.
       -  Input waktu appointment.
       -  Validasi waktu sesuai jadwal kerja dokter.
       -  Jika valid → dimasukkan ke `AppointmentQueue`.
    2. **Lihat List Dokter**
       - Menampilkan seluruh dokter terdaftar (`listDokter.txt`).
    3. **Lihat Dokter yang Tersedia**
       - Menampilkan dokter yang sudah login (dari stack `absenDokter`).
    4. **Logout**
       - Keluar dari sistem.
  - `pilDokter()`
    1. **Absensi Dokter (Login Otomatis)**
       - Mencatat waktu kehadiran dokter (login).
       - Menandai status hadir (`absen = true`).
       - Memasukkan dokter ke stack absensi.
    2. **Melihat Daftar Appointment**
       - Menampilkan daftar janji temu (appointment) milik dokter login.
       - Diambil dari `mapQueueDokter`.
    3. **Logout**
       - Keluar dari sistem.
       - Jika diimplementasikan mencatat waktu logout.
  - `pilAdmin()`
    1. **Lihat Dokter yang Hadir Hari Ini**
       - Menampilkan dokter yang tercatat hadir (stack `absenDokter`).
    2. **Lihat Dokter yang Terakhir Absen**
       - Menampilkan dokter paling atas dalam stack (absen terakhir).
    3. **Cari Pasien Berdasarkan ID**
       - Input ID pasien → tampilkan data dari `BST`.
    4. **Lihat List Pasien**
       - Menampilkan seluruh data pasien (`PatientList`).
    5. **Bersihkan Absen Hari Ini**
       - Menghapus isi `absenDokter.txt` (reset absen harian).
    6. **Keluar**
       - Logout dari sistem admin.
- Mengatur penyimpanan dan pembacaan file.
- Menyimpan semua data dalam variabel global seperti `patientList`, `doctorStack`, `mapQueueDokter`, dll.
- Fitur Tambahan:
  - `Sleep()` – Memberi jeda sebelum eksekusi berikutnya.
  - `clear()` – Membersihkan tampilan terminal.
  - `artHospital()` – Menampilkan hiasan saat program dijalankan.

### 📁 `Main.java`
- Sebagai tempat utama di mana seluruh modul program dijalankan.
- Alur Eksekusi:
  - Program dimulai dengan membuat objek dari kelas `Menu`, disimpan dalam variabel `menu`.
  - Fungsi `clear()` dipanggil terlebih dahulu untuk membersihkan layar terminal.
  - Kemudian program memanggil `menu.pilihTipeUser()` untuk memilih peran pengguna (Pasien/Dokter/Admin).
  - Dibuat variabel `berhasilLogin` bertipe boolean yang awalnya bernilai `false`.
  - Jika pengguna berhasil login atau register, maka `berhasilLogin` diatur menjadi `true`.
  - Jika `berhasilLogin == true`, maka program lanjut ke `menu.lihatMenu()` sesuai peran pengguna.
  - Jika `false`, maka program berhenti.
  
---

## 🧑‍⚕️ Cara Menjalankan Program sebagai Pasien

### 1. 🔐 **Login atau Register**
- **Register** jika belum punya akun:
  - Input: nama, umur, alamat, nomor telepon, riwayat penyakit, password.
- **Login** jika sudah terdaftar:
  - Input: nama & password.
  - Salah 3x = keluar otomatis.

### 2. 📆 **Menu Pasien Setelah Login**
- `1. Appointment`:  
  - Masukkan nama dokter.  
  - Masukkan waktu janji temu (dalam jam kerja dokter).  
  - Jika valid, appointment dimasukkan ke antrian.

- `2. Lihat List Dokter`:  
  - Menampilkan semua dokter yang terdaftar di rumah sakit.

- `3. Lihat Dokter yang Tersedia`:  
  - Menampilkan hanya dokter yang sudah login (absen hari itu).

- `4. Logout`:  
  - Keluar dari sistem.

---

## 👨‍⚕️ Cara Menjalankan Sebagai Dokter

### 1. 🔐 **Login atau Register**
- **Register** jika belum punya akun:
  - Input: nama, spesialis, jam kerja, password.
- **Login** jika sudah terdaftar:
  - Input: nama & password.
  - Salah 3x = keluar otomatis.
  
### 2. 📆 **Menu Dokter Setelah Login**
- `1. Absen`:
  - Sistem akan mencatat waktu login (absen).
    
- `2. List Permintaan Appointment`:
  - Menampilkan list pasien yang membuat appointment dengan dokter tersebut.
  - Lalu memilih apakah ingin menerima appointment teratas atau tidak.
    
- `3. Lihat Appointment yang Akan Datang`:
  - Menampilkan list appointment yang sudah diterima.
    
- `4. Proses Appointment`:
  - Melakukan proses pada appointment yang telah diterima.
    
- `5. Absen Keluar`:
  - Sistem akan mencatat waktu login (absen).

- `6. Keluar`:
  - Keluar dari program (tidak dihitung logout).

---

## 🛠️ Admin Menu

### **Fitur Khusus bagi Admin**
- Menampilkan daftar dokter yang hadir.
- Menampilkan dokter terakhir absen.
- Mencari pasien berdasarkan ID.
- Melihat seluruh pasien.
- Menghapus absensi hari ini.
- Logout.

---

## 📚 Struktur Data yang Digunakan
- `Linked List`: Menyimpan daftar pasien.
- `Stack`: Menyimpan absen dokter.
- `Queue`: Menyimpan antrian janji temu.
- `BST`: Menyusun pasien berdasarkan ID.
- `Map`: Menyimpan daftar appointment per dokter.

---

## 👥 Anggota Tim beserta Jobdesknya

- Muhamad Zidan Dicky Nasuha (L0124061)  : 
- Naomira Aulin Afnan Faiza (L0124067)  : 
- Muhammad Rafian Surya Muqsith (L0124111)  : 
- Radit Alfa Anugerah Bombing (L0124116) : 

---
