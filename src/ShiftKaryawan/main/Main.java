/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ShiftKaryawan.main;
import javax.swing.SwingUtilities;
import view.LoginFrame;
import database.DBConnection;

/**
 *
 * @author Adess
 */
public class Main {

    public static void main(String[] args) {
        DBConnection.getConnection();

        SwingUtilities.invokeLater(() -> {
            LoginFrame login = new LoginFrame();
            login.setVisible(true);
            login.setLocationRelativeTo(null);
        });
    }
    }