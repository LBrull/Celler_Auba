package com.presentacio;

import javax.swing.*;
import java.awt.*;
import java.util.prefs.Preferences;

public class Login extends JFrame{
    private JPanel rootPanel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JLabel titleLabel;
    private JButton iniciarSessióButton;

    public Login () {
        super();
        setContentPane(rootPanel);
        titleLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        iniciarSessióButton.addActionListener(l -> {
            String token = null;
            try {
                String pass = new String(passwordField1.getPassword());
                token = LoginController.getInstance().login(textField1.getText(), pass);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (token!=null) {
                dispose();
                saveToken(token);
                LoginController.getInstance().showMenu();
            }
            else {
                showWrongCredentialsAdvert();
            }
        });

        pack();
        setSize(500, 300);
        setVisible(true);
    }

    private void saveToken(String token) {
        Preferences root = Preferences.userRoot();
        root.put("token", token);
    }

    public void showWrongCredentialsAdvert() {
        JOptionPane.showMessageDialog(null, "Credencials no vàlides", "ERROR", JOptionPane.INFORMATION_MESSAGE);
    }

}
