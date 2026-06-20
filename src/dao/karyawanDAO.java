/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import model.karyawan;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Adess
 */
public class karyawanDAO {
      // INSERT
    public boolean tambah(karyawan karyawan) {

        String sql = """
                     INSERT INTO karyawan
                     (nama,jabatan,jenis_kelamin,no_hp,alamat)
                     VALUES (?,?,?,?,?)
                     """;

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, karyawan.getNama());
            ps.setString(2, karyawan.getJabatan());
            ps.setString(3, karyawan.getJenisKelamin());
            ps.setString(4, karyawan.getNoHp());
            ps.setString(5, karyawan.getAlamat());

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

            return st.executeQuery(
                    "SELECT * FROM karyawan ORDER BY id");

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
    
    public boolean hapus(int id){

    try{

        Connection conn = DBConnection.getConnection();

        PreparedStatement ps =
                conn.prepareStatement(
                        "DELETE FROM karyawan WHERE id=?"
                );

        ps.setInt(1, id);

        ps.executeUpdate();

        return true;

    }catch(Exception e){

        e.printStackTrace();
        return false;
    }
}

public boolean update(karyawan k){

    try{

        Connection conn = DBConnection.getConnection();

        PreparedStatement ps =
                conn.prepareStatement(
                        """
                        UPDATE karyawan
                        SET nama=?,
                            jabatan=?,
                            jenis_kelamin=?,
                            no_hp=?,
                            alamat=?
                        WHERE id=?
                        """
                );

        ps.setString(1, k.getNama());
        ps.setString(2, k.getJabatan());
        ps.setString(3, k.getJenisKelamin());
        ps.setString(4, k.getNoHp());
        ps.setString(5, k.getAlamat());
        ps.setInt(6, k.getId());

        ps.executeUpdate();

        return true;

    }catch(Exception e){

        e.printStackTrace();
        return false;
    }
   }

public ResultSet cari(String keyword) {

    try {

        Connection conn = DBConnection.getConnection();

        PreparedStatement ps =
                conn.prepareStatement(
                        "SELECT * FROM karyawan WHERE LOWER(nama) LIKE ?");

        ps.setString(1, "%" + keyword.toLowerCase() + "%");

        return ps.executeQuery();

    } catch (Exception e) {

        e.printStackTrace();
        return null;
    }
}
}

