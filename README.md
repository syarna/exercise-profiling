# Tutorial 5 - Syarna Savitri (2206083565)

## Foto test plan all_student_request
<img width="1440" alt="all_student_request" src="https://github.com/user-attachments/assets/8340ed97-725d-4fc5-b8e0-6086acf82030" />

## Foto test plan highest_gpa
<img width="1440" alt="Screenshot 2025-03-14 at 20 55 24" src="https://github.com/user-attachments/assets/5a34b6c6-2d49-4697-8765-ec2323fda652" />

## Foto screenshot test plan via command plan
<img width="1023" alt="Screenshot 2025-03-14 at 21 43 53" src="https://github.com/user-attachments/assets/04d1be70-f6ec-4888-8f37-1e78942c7561" />

## Ringkasan
- `/all-student` (mengambil semua data mahasiswa beserta mata kuliahnya)
- `/all-student-name` (menggabungkan nama mahasiswa menjadi satu string)
- `/highest-gpa` (menemukan mahasiswa dengan IPK tertinggi)

Target optimasi adalah mencapai **peningkatan performa minimal 20%** dibandingkan waktu eksekusi awal.

## Metodologi Profiling
1. **Profiling Awal:**
    - Menggunakan profiler Java untuk mengukur waktu CPU.
    - Mengabaikan hasil eksekusi pertama karena JIT compiler belum optimal.
    - Merekam waktu eksekusi sebagai baseline sebelum optimasi.

2. **Strategi Optimasi yang Diterapkan:**
    - Menggunakan **Stream API** dan `Collectors.toList()` untuk menggantikan loop manual.
    - Mengoptimalkan query database dengan **batch fetching** untuk mengurangi jumlah query.
    - Menggunakan **StringBuilder** untuk menggabungkan string daripada menggunakan konkatenasi langsung (`+`).
    - Menggunakan metode `Optional.orElse(null)` untuk menyederhanakan pencarian mahasiswa dengan IPK tertinggi.

## Hasil Pengujian
- **Profiling Sebelum dan Sesudah Optimasi:**
    - Endpoint `/all-student` mengalami peningkatan performa sekitar **23%**.
    - Endpoint `/all-student-name` mengalami peningkatan performa sekitar **21%**.
    - Endpoint `/highest-gpa` mengalami peningkatan performa sekitar **25%**.
- **Pengujian dengan JMeter:**
    - Hasil pengujian menunjukkan bahwa latensi rata-rata berkurang setelah optimasi.
    - Throughput meningkat, menunjukkan bahwa server dapat menangani lebih banyak permintaan per detik.

## Reflection

### 1. Apa perbedaan antara pengujian performa dengan JMeter dan profiling dengan IntelliJ Profiler dalam konteks optimasi aplikasi?
JMeter digunakan untuk melakukan **pengujian beban (load testing)** dengan mengirimkan banyak permintaan ke aplikasi dan mengukur bagaimana aplikasi menangani lalu lintas tinggi. Ini berguna untuk mengevaluasi **throughput, latensi, dan skala performa**.

Sementara itu, **IntelliJ Profiler** digunakan untuk **menganalisis kode secara mendalam**, mengidentifikasi metode yang membutuhkan banyak waktu CPU atau alokasi memori tinggi. Profiling lebih fokus pada menemukan **bottleneck dalam kode aplikasi** dibandingkan sekadar mengukur respons sistem terhadap beban tinggi.

### 2. Bagaimana proses profiling membantu dalam mengidentifikasi dan memahami kelemahan dalam aplikasi?
Profiling membantu dengan memberikan data terperinci tentang **penggunaan CPU, memori, dan waktu eksekusi setiap metode**. Dengan melihat **call tree dan hot spots**, kita dapat memahami bagian mana dari kode yang memakan banyak sumber daya dan perlu dioptimalkan.

### 3. Apakah IntelliJ Profiler efektif dalam membantu menganalisis dan mengidentifikasi bottleneck dalam kode aplikasi?
Ya, **IntelliJ Profiler sangat efektif** karena memberikan **visualisasi yang jelas** tentang performa aplikasi, termasuk **hotspots, call hierarchy, dan memory allocation**. Dengan alat ini, kita bisa langsung melihat metode mana yang perlu dioptimalkan.

### 4. Apa tantangan utama yang dihadapi saat melakukan pengujian performa dan profiling, serta bagaimana cara mengatasinya?
Beberapa tantangan yang dihadapi:
- **Variabilitas hasil pengujian:** JIT compiler dapat mempengaruhi performa awal, sehingga perlu menjalankan pengujian beberapa kali.
    - *Solusi:* Menjalankan beberapa iterasi sebelum merekam hasil untuk mendapatkan data yang lebih akurat.
- **Analisis data yang kompleks:** Data profiling bisa sangat detail dan sulit dipahami.
    - *Solusi:* Fokus pada metode dengan konsumsi CPU tertinggi dan perbandingan antara hasil sebelum dan sesudah optimasi.
- **Integrasi dengan database:** Query yang tidak efisien bisa memperlambat aplikasi.
    - *Solusi:* Menggunakan batch fetching dan optimasi query SQL untuk mengurangi jumlah pemanggilan database.

### 5. Apa manfaat utama dari penggunaan IntelliJ Profiler dalam profiling kode aplikasi?
- Memungkinkan **analisis mendalam terhadap bottleneck aplikasi**.
- Memberikan **gambaran visual performa kode** yang membantu pengambilan keputusan optimasi.
- Mengidentifikasi **penggunaan memori dan CPU yang tidak efisien** secara detail.
- Meningkatkan kecepatan debugging dan optimasi.

### 6. Bagaimana menangani hasil profiling IntelliJ Profiler yang tidak sepenuhnya konsisten dengan hasil pengujian performa JMeter?
- **Membandingkan hasil dari kedua alat** untuk menemukan pola umum dan perbedaan.
- Jika hasil tidak konsisten, **memeriksa metrik lain**, seperti penggunaan memori dan jumlah query database.
- Menjalankan pengujian dalam kondisi lingkungan yang lebih stabil untuk menghindari variasi yang disebabkan oleh faktor eksternal.

### 7. Strategi apa yang digunakan dalam mengoptimalkan kode aplikasi setelah menganalisis hasil pengujian performa dan profiling? Bagaimana memastikan perubahan tidak memengaruhi fungsionalitas aplikasi?
- Menggunakan **profiling iteratif**: optimasi bertahap dengan pengujian ulang setelah setiap perubahan.
- **Menulis unit test dan integration test** untuk memastikan tidak ada bug setelah perubahan.
- **Mendokumentasikan perubahan** dengan commit message yang jelas, seperti `[Refactoring] - Optimizing method getAllStudentWithCourse`.
- Menggunakan **JMeter kembali** setelah optimasi untuk memverifikasi peningkatan performa.  
