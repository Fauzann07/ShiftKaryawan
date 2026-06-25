/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ShiftKaryawan.main;
import javax.swing.SwingUtilities;
import view.SplashScreen;
import database.DBConnection;
/**
 *
 * @author Adess
 */
public class Main {
public static void main(String[] args) {
    DBConnection.getConnection();
    SwingUtilities.invokeLater(() -> {
        SplashScreen splash = new SplashScreen();
        splash.showSplash();
    });
}
}
