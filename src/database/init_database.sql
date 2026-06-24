-- ============================================
-- SQL Script: ShiftKaryawan Database Setup
-- Database: PostgreSQL
-- ============================================

-- 1. Tabel Users (untuk login)
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);
 
-- 2. Tabel Karyawan
CREATE TABLE IF NOT EXISTS karyawan (
    id SERIAL PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    jabatan VARCHAR(50),
    jenis_kelamin VARCHAR(20),
    no_hp VARCHAR(20),
    alamat TEXT
);

-- 3. Tabel Shift
CREATE TABLE IF NOT EXISTS shift (
    id SERIAL PRIMARY KEY,
    karyawan_id INT REFERENCES karyawan(id) ON DELETE CASCADE,
    tanggal DATE NOT NULL,
    tipe_shift VARCHAR(20) NOT NULL,
    jam_masuk VARCHAR(10),
    jam_keluar VARCHAR(10),
    status VARCHAR(20) DEFAULT 'Aktif'
);

-- 4. Tabel Kehadiran (ringkasan bulanan)
CREATE TABLE IF NOT EXISTS kehadiran (
    id SERIAL PRIMARY KEY,
    karyawan_id INT REFERENCES karyawan(id) ON DELETE CASCADE,
    bulan INT NOT NULL,
    tahun INT NOT NULL,
    hadir INT DEFAULT 0,
    sakit INT DEFAULT 0,
    cuti INT DEFAULT 0,
    izin INT DEFAULT 0,
    alpa INT DEFAULT 0,
    telat INT DEFAULT 0,
    kinerja VARCHAR(50),
    UNIQUE(karyawan_id, bulan, tahun)
);

-- Insert default admin user
INSERT INTO users (username, password) VALUES ('admin', 'admin123')
ON CONFLICT (username) DO NOTHING;

-- Insert sample karyawan
INSERT INTO karyawan (nama, jabatan, jenis_kelamin, no_hp, alamat) VALUES
('Budi Santoso', 'Admin', 'Laki-laki', '081234567890', 'Jakarta'),
('Siti Nurhaliza', 'Kasir', 'Perempuan', '081298765432', 'Bandung'),
('Ahmad Fauzi', 'Staff', 'Laki-laki', '085678901234', 'Surabaya')
ON CONFLICT DO NOTHING;
