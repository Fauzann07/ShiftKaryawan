
-- tabel Users/ADMIN (lOGIN)
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);
 
-- tabel Karyawan
CREATE TABLE IF NOT EXISTS karyawan (
    id SERIAL PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    jabatan VARCHAR(50),
    jenis_kelamin VARCHAR(20),
    no_hp VARCHAR(20),
    alamat TEXT
);

-- tabel Shift
CREATE TABLE IF NOT EXISTS shift (
    id SERIAL PRIMARY KEY,
    karyawan_id INT REFERENCES karyawan(id) ON DELETE CASCADE,
    tanggal DATE NOT NULL,
    tipe_shift VARCHAR(20) NOT NULL,
    jam_masuk VARCHAR(10),
    jam_keluar VARCHAR(10),
    status VARCHAR(20) DEFAULT 'Aktif'
);

-- tabel Kehadiran (ringkasan bulanan)
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
INSERT INTO users (username, password) VALUES ('admin', '12345')
ON CONFLICT (username) DO NOTHING;

