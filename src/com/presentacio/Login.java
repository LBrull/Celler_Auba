package com.presentacio;

import com.model.ServerResponse;

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
            ServerResponse res;
            try {
                String pass = new String(passwordField1.getPassword());
                res = LoginController.getInstance().login(textField1.getText(), pass);
                if (res.getStatus() == 200) {
                    dispose();
                    saveToken(res.getToken());
                    LoginController.getInstance().showMenu();
                }
                else if (res.getStatus() == 404) {
                    JOptionPane.showMessageDialog(null, "L'usuari no és vàlid", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }

                else if (res.getStatus() == 400) {
                    JOptionPane.showMessageDialog(null, "La contrasenya no és correcta", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }

                else if (res.getStatus() == 500) {
                    JOptionPane.showMessageDialog(null, "Error validant contrasenyes, torneu-ho a intentar", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Hi ha hagut un error", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
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

}
