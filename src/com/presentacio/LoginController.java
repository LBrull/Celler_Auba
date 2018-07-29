package com.presentacio;

import com.persistencia.DBController;

public class LoginController {

    private static LoginController instance = null;

    private MenuController menuController;
    private DBController dbController = DBController.getInstance();

    private LoginController () {}

    public static LoginController getInstance() {
        if (instance == null) {
            instance = new LoginController();
        }
        return instance;
    }

    public void showLoginView() {
        new Login();

    }

    public String login(String username, String password) throws Exception {
        return dbController.login(username, password);
    }

    public void showMenu() {
        new Menu();
    }
}
