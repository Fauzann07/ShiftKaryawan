package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateHelper {

    private static final Locale ID = new Locale("id");

    public static String formatTanggal(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", ID));
    }

    public static String getNamaHari(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("EEEE", ID));
    }

    public static String getBulanTahun(int bulan, int tahun) {
        LocalDate d = LocalDate.of(tahun, bulan, 1);
        return d.format(DateTimeFormatter.ofPattern("MMMM yyyy", ID));
    }

    public static int getBulanSekarang() { return LocalDate.now().getMonthValue(); }
    public static int getTahunSekarang() { return LocalDate.now().getYear(); }
}
