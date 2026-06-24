package controller;

import dao.ShiftDAO;
import model.Shift;
import java.util.List;

public class ShiftController {

    private final ShiftDAO dao = new ShiftDAO();

    public List<Shift> getShiftHariIni() {
        return dao.getAllKaryawanHariIni();
    }

    public boolean simpanShift(int karyawanId, String tanggal, String tipeShift) {
        return dao.simpanShift(karyawanId, tanggal, tipeShift);
    }

    public List<Shift> getShiftMingguIni(int karyawanId) {
        return dao.getShiftKaryawanMingguIni(karyawanId);
    }
}
