# Permainan Dungeon

Ini adalah permainan dungeon sederhana yang dibuat menggunakan Java. Pemain harus mencari jalan keluar pada labirin, melawan monster, dan menemukan peti dengan senjata untuk menyelesaikan permainan.

## Persiapan

Untuk menjalankan permainan ini, ikuti langkah-langkah berikut:

1. Clone repositori ini.
2. Buka proyek di IDE Anda.
3. Jalankan kelas `Main`.

Atau unduh versi yang sudah dicompile di halaman [release](https://github.com/nurazizUGM/UAS_PraktikumPBO/releases).

## Kontrol

Pemain dapat bergerak menggunakan tombol W, A, S, D. Menekan tombol ESC akan menjeda permainan. Pemain menang ketika mereka membuka peti dan menyelesaikan permainan.

## Logika Permainan

Permainan ini terbagi menjadi dua kelas utama: `GamePanel` dan `KeyHandler`. `GamePanel` bertanggung jawab atas pembuatan jendela permainan dan pembaruan keadaan permainan, sedangkan `KeyHandler` mengelola masukan dari pemain.

Dunia permainan direpresentasikan oleh kelas `TileManager`, yang memuat peta permainan dan monster dari file. Pemain direpresentasikan oleh kelas `Player`, yang dapat bergerak melalui dunia permainan dan melawan monster. Permainan berakhir ketika pemain mati atau membuka peti.

Keadaan permainan diperbarui di kelas `GamePanel`, yang menangani deteksi tabrakan dan pergerakan monster.

## TODO

-   Tambahkan efek suara.
-   Tambahkan layar kemenangan.
-   Tambahkan sistem menu yang lebih baik.
-   Tambahkan lebih banyak level.
