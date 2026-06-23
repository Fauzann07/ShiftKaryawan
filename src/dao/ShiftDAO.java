package dao;

import model.Shift;
import java.util.ArrayList;
import java.util.List;

public class ShiftDAO {

    public List<Shift> getAllKaryawanHariIni() {
        List<Shift> list = new ArrayList<>();
        list.add(new Shift("Budi", "Admin", "08:00", "16:00", "Aktif"));
        list.add(new Shift("Siti", "Kasir", "09:00", "17:00", "Selesai"));
        return list;
    }
}
