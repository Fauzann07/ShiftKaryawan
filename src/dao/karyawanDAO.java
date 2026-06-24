package dao;

import database.DBConnection;
import model.karyawan;
import java.sql.*;

public class karyawanDAO {

    // INSERT
    public boolean tambah(karyawan k) {
        String sql = """
                     INSERT INTO karyawan
                     (nama,jabatan,jenis_kelamin,no_hp,alamat)
                     VALUES (?,?,?,?,?)
                     """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, k.getNama());
            ps.setString(2, k.getJabatan());
            ps.setString(3, k.getJenisKelamin());
            ps.setString(4, k.getNoHp());
            ps.setString(5, k.getAlamat());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // SELECT ALL
    public ResultSet getAll() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            return st.executeQuery("SELECT * FROM karyawan ORDER BY id");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // DELETE
    public boolean hapus(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM karyawan WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // UPDATE
    public boolean update(karyawan k) {
        String sql = """
                     UPDATE karyawan
                     SET nama=?,
                         jabatan=?,
                         jenis_kelamin=?,
                         no_hp=?,
                         alamat=?
                     WHERE id=?
                     """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, k.getNama());
            ps.setString(2, k.getJabatan());
            ps.setString(3, k.getJenisKelamin());
            ps.setString(4, k.getNoHp());
            ps.setString(5, k.getAlamat());
            ps.setInt(6, k.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // SEARCH
    public ResultSet cari(String keyword) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM karyawan WHERE LOWER(nama) LIKE ?");
            ps.setString(1, "%" + keyword.toLowerCase() + "%");
            return ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // GET ALL for table (returns ResultSet of id, nama, jabatan for shift table)
    public ResultSet getAllForShift() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            return st.executeQuery("SELECT id, nama, jabatan FROM karyawan ORDER BY nama");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
