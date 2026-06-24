package utils;

public class Validator {

    public static boolean isNotEmpty(String... fields) {
        for (String f : fields) {
            if (f == null || f.trim().isEmpty()) return false;
        }
        return true;
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("^0\\d{9,13}$");
    }

    public static boolean isMinLength(String text, int min) {
        return text != null && text.length() >= min;
    }

    public static boolean isPasswordMatch(String pass1, String pass2) {
        return pass1 != null && pass1.equals(pass2);
    }
}
