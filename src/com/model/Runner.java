package com.model;

import com.persistencia.DBController;
import com.presentacio.MenuController;

import javax.swing.*;
import java.awt.*;

public class Runner {

    private static Color backgroundColor = new Color(60, 63, 65);
    private static Color secondBackgroundColor = new Color(89, 91, 93);
    private static Color textColor = new Color(187,187,187);
    //private static Color colorAccent = new Color(231, 158, 109);

    public static void main(String[] args) {

        configureLookAndFeel();
        //configureDB();
        MenuController menuController = MenuController.getInstance();
        menuController.showMenuView();
//        LoginController loginController = LoginController.getInstance();
//        loginController.showLoginView();
    }

    private static void configureDB() {
        DBController dbController = DBController.getInstance();
        dbController.DBconnect();
    }

    private static void configureLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); //Windows Look and feel
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        //UIManager.put("OptionPane.background", backgroundColor);
        //UIManager.put("OptionPane.foreground", textColor);
        //UIManager.put("OptionPane.messageForeground", textColor);

        UIManager.put("Button.background", backgroundColor);
        UIManager.put("Button.foreground", textColor);
        //UIManager.put("Panel.background", backgroundColor);
        UIManager.put("Label.foreground", textColor);
        UIManager.put("List.background", backgroundColor);
        UIManager.put("CheckBox.background",backgroundColor);
        UIManager.put("FormattedTextField.background", secondBackgroundColor);

        //Change font of application
        setUIFont (new javax.swing.plaf.FontUIResource("Calibri", Font.PLAIN,14));
    }

    private static void setUIFont (javax.swing.plaf.FontUIResource f){
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put (key, f);
        }
    }

}
