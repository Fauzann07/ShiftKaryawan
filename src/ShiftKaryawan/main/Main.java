/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ShiftKaryawan.main;
import javax.swing.SwingUtilities;
import view.LoginFrame;
import database.DBConnection;
import dao.karyawanDAO;
import model.karyawan;

/**
 *
 * @author Adess
 */
public class Main {
 public static void main(String[] args) {

        DBConnection.getConnection();

        karyawan k = new karyawan();

        k.setNama("Budi");
        k.setJabatan("Kasir");
        k.setJenisKelamin("Laki-Laki");
        k.setNoHp("08123456789");
        k.setAlamat("Bekasi");

        karyawanDAO dao = new karyawanDAO();

        if (dao.tambah(k)) {
            System.out.println("Data berhasil ditambah");
        } else {
            System.out.println("Data gagal ditambah");
        }

        SwingUtilities.invokeLater(() -> {
            LoginFrame login = new LoginFrame();
            login.setVisible(true);
            login.setLocationRelativeTo(null);
        });
    }
}
