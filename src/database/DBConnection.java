/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Adess
 */
public class DBConnection {
   private static final String URL =
            "jdbc:postgresql://localhost:5432/ShiftKaryawan";

    private static final String USER =
            "postgres";

    private static final String PASSWORD =
            "Rafif211206";

    public static Connection getConnection() {

        try {

            Connection conn =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD);

            System.out.println("Koneksi Berhasil");

            return conn;

        } catch (Exception e) {

            System.out.println("Koneksi Gagal");
            e.printStackTrace();

            return null;
        }
    }
} 
