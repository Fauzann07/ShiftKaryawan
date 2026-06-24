package utils;

public class Session {
    private static String currentUsername;
    private static int currentUserId = -1;

    public static void setCurrentUser(int id, String username) {
        currentUserId = id;
        currentUsername = username;
    }

    public static String getCurrentUsername() { return currentUsername; }
    public static int getCurrentUserId() { return currentUserId; }

    public static boolean isLoggedIn() { return currentUsername != null; }

    public static void clear() {
        currentUsername = null;
        currentUserId = -1;
    }
}
