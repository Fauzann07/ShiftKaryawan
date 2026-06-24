package controller;

import dao.karyawanDAO;
import model.karyawan;
import java.sql.ResultSet;

public class KaryawanController {

    private final karyawanDAO dao = new karyawanDAO();

    public boolean tambah(karyawan k) { return dao.tambah(k); }
    public boolean update(karyawan k) { return dao.update(k); }
    public boolean hapus(int id) { return dao.hapus(id); }
    public ResultSet getAll() { return dao.getAll(); }
    public ResultSet cari(String keyword) { return dao.cari(keyword); }
}
