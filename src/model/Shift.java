package model;

public class Shift {
    private int id;
    private int karyawanId;
    private String nama;
    private String jabatan;
    private String tanggal;
    private String tipeShift;
    private String jamMasuk;
    private String jamKeluar;
    private String status;

    public Shift() {}

    public Shift(String nama, String jabatan, String jamMasuk, String jamKeluar, String status) {
        this.nama = nama;
        this.jabatan = jabatan;
        this.jamMasuk = jamMasuk;
        this.jamKeluar = jamKeluar;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getKaryawanId() { return karyawanId; }
    public void setKaryawanId(int karyawanId) { this.karyawanId = karyawanId; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getJabatan() { return jabatan; }
    public void setJabatan(String jabatan) { this.jabatan = jabatan; }

    public String getTanggal() { return tanggal; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }

    public String getTipeShift() { return tipeShift; }
    public void setTipeShift(String tipeShift) { this.tipeShift = tipeShift; }

    public String getJamMasuk() { return jamMasuk; }
    public void setJamMasuk(String jamMasuk) { this.jamMasuk = jamMasuk; }

    public String getJamKeluar() { return jamKeluar; }
    public void setJamKeluar(String jamKeluar) { this.jamKeluar = jamKeluar; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
