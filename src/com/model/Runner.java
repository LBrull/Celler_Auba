package com.model;

import com.persistencia.DBController;
import com.presentacio.ContactsViewController;

import javax.swing.*;
import java.awt.*;

public class Runner {

    private static Color backgroundColor = new Color(60, 63, 65);
    private static Color textColor = new Color(187,187,187);
    //private static Color colorAccent = new Color(231, 158, 109);

    private static DBController dbController = null;

    public static void main(String[] args) {

        configureLookAndFeel();
        configureDB();

        ContactsViewController presentationController = ContactsViewController.getInstance();
        presentationController.run();

    }

    private static void configureDB() {
        dbController = DBController.getInstance();
        dbController.connect();
    }

    private static void configureLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //Windows Look and feel
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        UIManager.put("Button.background", backgroundColor);

        UIManager.put("Panel.background", backgroundColor);
        UIManager.put("Label.foreground", textColor);
        UIManager.put("List.background", backgroundColor);

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
