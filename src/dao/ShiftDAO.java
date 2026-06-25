package dao;

import database.DBConnection;
import model.Shift;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShiftDAO {

    public List<Shift> getAllKaryawanHariIni() {
        List<Shift> list = new ArrayList<>();
        String sql = """
                     SELECT k.nama, k.jabatan, s.jam_masuk, s.jam_keluar, s.status
                     FROM shift s
                     JOIN karyawan k ON s.karyawan_id = k.id
                     WHERE s.tanggal = CURRENT_DATE
                     ORDER BY k.nama
                     """;
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Shift s = new Shift();
                s.setNama(rs.getString("nama"));
                s.setJabatan(rs.getString("jabatan"));
                s.setJamMasuk(rs.getString("jam_masuk"));
                s.setJamKeluar(rs.getString("jam_keluar"));
                s.setStatus(rs.getString("status"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getShift(int karyawanId, String tanggal) {
        String sql = "SELECT tipe_shift FROM shift WHERE karyawan_id=? AND tanggal=?::date";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, karyawanId);
            ps.setString(2, tanggal);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("tipe_shift");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public boolean simpanShift(int karyawanId, String tanggal, String tipeShift) {
        String jamMasuk;
        String jamKeluar;
        if ("Pagi".equals(tipeShift)) {
            jamMasuk = "08:00"; jamKeluar = "16:00";
        } else {
            jamMasuk = "20:00"; jamKeluar = "04:00";
        }

        // Cek apakah sudah ada shift di tanggal itu
        String cek = "SELECT id FROM shift WHERE karyawan_id=? AND tanggal=?::date";
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(cek)) {
                ps.setInt(1, karyawanId);
                ps.setString(2, tanggal);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Update
                        String upd = "UPDATE shift SET tipe_shift=?, jam_masuk=?, jam_keluar=?, status='Aktif' WHERE id=?";
                        try (PreparedStatement pu = conn.prepareStatement(upd)) {
                            pu.setString(1, tipeShift);
                            pu.setString(2, jamMasuk);
                            pu.setString(3, jamKeluar);
                            pu.setInt(4, rs.getInt("id"));
                            pu.executeUpdate();
                        }
                    } else {
                        // Insert
                        String ins = "INSERT INTO shift (karyawan_id,tanggal,tipe_shift,jam_masuk,jam_keluar,status) VALUES (?,?::date,?,?,?,'Aktif')";
                        try (PreparedStatement pi = conn.prepareStatement(ins)) {
                            pi.setInt(1, karyawanId);
                            pi.setString(2, tanggal);
                            pi.setString(3, tipeShift);
                            pi.setString(4, jamMasuk);
                            pi.setString(5, jamKeluar);
                            pi.executeUpdate();
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Shift> getShiftKaryawanMingguIni(int karyawanId) {
        List<Shift> list = new ArrayList<>();
        String sql = "SELECT * FROM shift WHERE karyawan_id=? AND tanggal BETWEEN CURRENT_DATE AND CURRENT_DATE+6 ORDER BY tanggal";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, karyawanId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Shift s = new Shift();
                    s.setId(rs.getInt("id"));
                    s.setKaryawanId(rs.getInt("karyawan_id"));
                    s.setTanggal(rs.getString("tanggal"));
                    s.setTipeShift(rs.getString("tipe_shift"));
                    s.setJamMasuk(rs.getString("jam_masuk"));
                    s.setJamKeluar(rs.getString("jam_keluar"));
                    s.setStatus(rs.getString("status"));
                    list.add(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Ambil data shift hari ini beserta ID shift untuk keperluan edit di Dashboard.
     */
    public List<Shift> getAllKaryawanHariIniWithId() {
        List<Shift> list = new ArrayList<>();
        String sql = """
                     SELECT s.id, k.nama, k.jabatan, s.jam_masuk, s.jam_keluar, s.status
                     FROM shift s
                     JOIN karyawan k ON s.karyawan_id = k.id
                     WHERE s.tanggal = CURRENT_DATE
                     ORDER BY k.nama
                     """;
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Shift s = new Shift();
                s.setId(rs.getInt("id"));
                s.setNama(rs.getString("nama"));
                s.setJabatan(rs.getString("jabatan"));
                s.setJamMasuk(rs.getString("jam_masuk"));
                s.setJamKeluar(rs.getString("jam_keluar"));
                s.setStatus(rs.getString("status"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Update status shift (jam masuk, jam keluar, status) dari Dashboard.
     */
    public boolean updateStatusShift(int shiftId, String jamMasuk, String jamKeluar, String status) {
        String sql = "UPDATE shift SET jam_masuk=?, jam_keluar=?, status=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, jamMasuk);
            ps.setString(2, jamKeluar);
            ps.setString(3, status);
            ps.setInt(4, shiftId);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

