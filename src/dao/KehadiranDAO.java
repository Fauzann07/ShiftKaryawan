package dao;

import database.DBConnection;
import model.Kehadiran;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KehadiranDAO {

    public List<Kehadiran> getByBulan(int bulan, int tahun) {
        List<Kehadiran> list = new ArrayList<>();
        String sql = """
                     SELECT ke.*, k.nama, k.jabatan
                     FROM kehadiran ke
                     JOIN karyawan k ON ke.karyawan_id = k.id
                     WHERE ke.bulan = ? AND ke.tahun = ?
                     ORDER BY k.nama
                     """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bulan);
            ps.setInt(2, tahun);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Kehadiran kh = new Kehadiran();
                    kh.setId(rs.getInt("id"));
                    kh.setKaryawanId(rs.getInt("karyawan_id"));
                    kh.setNamaKaryawan(rs.getString("nama"));
                    kh.setJabatan(rs.getString("jabatan"));
                    kh.setBulan(rs.getInt("bulan"));
                    kh.setTahun(rs.getInt("tahun"));
                    kh.setHadir(rs.getInt("hadir"));
                    kh.setSakit(rs.getInt("sakit"));
                    kh.setCuti(rs.getInt("cuti"));
                    kh.setIzin(rs.getInt("izin"));
                    kh.setAlpa(rs.getInt("alpa"));
                    kh.setTelat(rs.getInt("telat"));
                    kh.setKinerja(rs.getString("kinerja"));
                    list.add(kh);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalCuti(int bulan, int tahun) {
        return getSum("cuti", bulan, tahun);
    }

    public int getTotalAlpa(int bulan, int tahun) {
        return getSum("alpa", bulan, tahun);
    }

    public int getTotalTelat(int bulan, int tahun) {
        return getSum("telat", bulan, tahun);
    }

    private int getSum(String kolom, int bulan, int tahun) {
        String sql = "SELECT COALESCE(SUM(" + kolom + "),0) FROM kehadiran WHERE bulan=? AND tahun=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bulan);
            ps.setInt(2, tahun);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
