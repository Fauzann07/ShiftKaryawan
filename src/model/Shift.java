package model;

public class Shift {

    private String nama;
    private String jabatan;
    private String jamMasuk;
    private String jamKeluar;
    private String status;

    public Shift(String nama, String jabatan, String jamMasuk, String jamKeluar, String status) {
        this.nama = nama;
        this.jabatan = jabatan;
        this.jamMasuk = jamMasuk;
        this.jamKeluar = jamKeluar;
        this.status = status;
    }

    public String getNama() {
        return nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public String getJamMasuk() {
        return jamMasuk;
    }

    public String getJamKeluar() {
        return jamKeluar;
    }

    public String getStatus() {
        return status;
    }
}
