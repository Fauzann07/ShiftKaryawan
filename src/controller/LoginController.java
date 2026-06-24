package controller;

import dao.UserDAO;
import model.User;
import utils.Session;

public class LoginController {

    private final UserDAO userDAO = new UserDAO();

    public boolean login(String username, String password) {
        User user = userDAO.login(username, password);
        if (user != null) {
            Session.setCurrentUser(user.getId(), user.getUsername());
            return true;
        }
        return false;
    }

    public void logout() {
        Session.clear();
    }

    public boolean gantiPassword(String oldPass, String newPass) {
        if (!Session.isLoggedIn()) return false;
        return userDAO.updatePassword(Session.getCurrentUsername(), oldPass, newPass);
    }
}
