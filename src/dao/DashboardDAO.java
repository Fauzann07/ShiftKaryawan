package dao;

import database.DBConnection;
import java.sql.*;

public class DashboardDAO {

    public int getTotalKaryawan() {
        return getCount("SELECT COUNT(*) FROM karyawan");
    }

    public int getShiftPagiHariIni() {
        return getCountParam("SELECT COUNT(*) FROM shift WHERE tanggal=CURRENT_DATE AND tipe_shift=?", "Pagi");
    }

    public int getShiftSiangHariIni() {
        return getCountParam("SELECT COUNT(*) FROM shift WHERE tanggal=CURRENT_DATE AND tipe_shift=?", "Siang");
    }

    public int getShiftMalamHariIni() {
        return getCountParam("SELECT COUNT(*) FROM shift WHERE tanggal=CURRENT_DATE AND tipe_shift=?", "Malam");
    }

    public int getSedangBekerja() {
        return getCount("SELECT COUNT(*) FROM shift WHERE tanggal=CURRENT_DATE AND status='Aktif'");
    }

    private int getCount(String sql) {
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int getCountParam(String sql, String param) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, param);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
