package model;

public class Kehadiran {
    private int id;
    private int karyawanId;
    private String namaKaryawan;
    private String jabatan;
    private int bulan;
    private int tahun;
    private int hadir;
    private int sakit;
    private int cuti;
    private int izin;
    private int alpa;
    private int telat;
    private String kinerja;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getKaryawanId() { return karyawanId; }
    public void setKaryawanId(int karyawanId) { this.karyawanId = karyawanId; }

    public String getNamaKaryawan() { return namaKaryawan; }
    public void setNamaKaryawan(String namaKaryawan) { this.namaKaryawan = namaKaryawan; }

    public String getJabatan() { return jabatan; }
    public void setJabatan(String jabatan) { this.jabatan = jabatan; }

    public int getBulan() { return bulan; }
    public void setBulan(int bulan) { this.bulan = bulan; }

    public int getTahun() { return tahun; }
    public void setTahun(int tahun) { this.tahun = tahun; }

    public int getHadir() { return hadir; }
    public void setHadir(int hadir) { this.hadir = hadir; }

    public int getSakit() { return sakit; }
    public void setSakit(int sakit) { this.sakit = sakit; }

    public int getCuti() { return cuti; }
    public void setCuti(int cuti) { this.cuti = cuti; }

    public int getIzin() { return izin; }
    public void setIzin(int izin) { this.izin = izin; }

    public int getAlpa() { return alpa; }
    public void setAlpa(int alpa) { this.alpa = alpa; }

    public int getTelat() { return telat; }
    public void setTelat(int telat) { this.telat = telat; }

    public String getKinerja() { return kinerja; }
    public void setKinerja(String kinerja) { this.kinerja = kinerja; }
}
