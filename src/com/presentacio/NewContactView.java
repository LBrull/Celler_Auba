package com.presentacio;

import com.model.ServerResponse;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class NewContactView extends JFrame {

    private static ContactsViewController controller = ContactsViewController.getInstance();

    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JCheckBox checkBoxProvider;
    private JCheckBox checkBoxClient;
    private JTextField telephoneTextField;
    private JTextField addressTextField;
    private JTextField emailTextField;
    private JButton saveButton;
    private JPanel rootPanel;
    private JLabel labelTitle;
    private JLabel emptyName;
    private JLabel emptySurname;
    private JLabel emptyType;
    private JLabel emptyTelephone;
    private JTextField cpTextField;
    private JTextField townTextField;
    private JTextField dniNifTextField;
    private JTextField accountNumberTextField;


    NewContactView() {
        super();
        setContentPane(rootPanel);
        labelTitle.setFont(new Font("Calibri", Font.PLAIN, 20));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        emptyName.setVisible(true);
        emptySurname.setVisible(true);
        emptyTelephone.setVisible(true);
        emptyType.setVisible(true);

        emptyName.setFont(new Font("Calibri", Font.PLAIN, 20));
        emptySurname.setFont(new Font("Calibri", Font.PLAIN, 20));
        emptyType.setFont(new Font("Calibri", Font.PLAIN, 20));
        emptyTelephone.setFont(new Font("Calibri", Font.PLAIN, 20));
        //emptyCp.setFont(new Font("Calibri", Font.PLAIN, 20));

        nameTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(nameTextField.getText().length()<=0 || nameTextField.getText().equals("")) {
                    emptyName.setVisible(true);
                }
                else {
                    emptyName.setVisible(false);
                }
            }
        });

        surnameTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(surnameTextField.getText().length()<=0 || surnameTextField.getText().equals("")) {
                    emptySurname.setVisible(true);
                }
                else {
                    emptySurname.setVisible(false);
                }
            }
        });

        telephoneTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(telephoneTextField.getText().length()<=0 || telephoneTextField.getText().equals("")) {
                    emptyTelephone.setVisible(true);
                }
                else {
                    emptyTelephone.setVisible(false);
                }
            }
        });

        checkBoxClient.addActionListener(e -> {
            if (!checkBoxClient.isSelected() && !checkBoxProvider.isSelected()) {
                emptyType.setVisible(true);
            }
            else {
                emptyType.setVisible(false);
            }
        });

        checkBoxProvider.addActionListener(e -> {
            if (!checkBoxClient.isSelected() && !checkBoxProvider.isSelected()) {
                emptyType.setVisible(true);
            }
            else {
                emptyType.setVisible(false);
            }
        });

/////////////////////////////////////////

        saveButton.addActionListener(e -> {

            if (!emptyName.isVisible() && !emptySurname.isVisible() && !emptyType.isVisible() && !emptyTelephone.isVisible()) { //si no hi ha cap error de rellenar formulari...

                if (checkBoxClient.isSelected() && !checkBoxProvider.isSelected()) {
                    ServerResponse res = null;
                    try {
                        res = controller.saveNewClient();
                    } catch (IOException | JSONException e1) {
                        e1.printStackTrace();
                    }
                    if (res != null && res.getStatus() == 200) {
                        controller.repaintClientsTable();
                        dispose();
                    }
                    else {
                        if (res != null) {
                            JOptionPane.showMessageDialog(null, res.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } else if (!checkBoxClient.isSelected() && checkBoxProvider.isSelected()) {
                    ServerResponse res = null;
                    try {
                        res = controller.saveNewProvider();
                    } catch (IOException | JSONException e1) {
                        e1.printStackTrace();
                    }
                    if (res != null && res.getStatus() == 200) {
                        controller.repaintProvidersTable();
                        dispose();
                    }
                    else {
                        if (res != null) {
                            JOptionPane.showMessageDialog(null, res.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } else {
                    ServerResponse res1 = null;
                    ServerResponse res2 = null;
                    try {
                        res1 = controller.saveNewClient();
                    } catch (IOException | JSONException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        res2 = controller.saveNewProvider();
                    } catch (IOException | JSONException e1) {
                        e1.printStackTrace();
                    }
                    if (res1 != null && res2 != null && res1.getStatus()==200 && res2.getStatus() == 200) {
                        controller.repaintClientsTable();
                        controller.repaintProvidersTable();
                        dispose();
                    }
                    else if (res1 != null && res1.getStatus()==200) {
                        controller.repaintClientsTable();
                        JOptionPane.showMessageDialog(null, "Només s'ha pogut guardar el client", "", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                    else if (res2 != null && res2.getStatus()==200) {
                        controller.repaintProvidersTable();
                        JOptionPane.showMessageDialog(null, "Només s'ha pogut guardar el proveidor", "", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Heu d'omplir els camps obligatoris", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        pack();
        setSize(700, 550);
        setVisible(true);
    }

//    public JCheckBox getCheckBoxClient() {
//        return checkBoxClient;
//    }
//
//    public JCheckBox getCheckBoxProvider() {
//        return checkBoxProvider;
//    }

    public JTextField getCpTextField() {
        return cpTextField;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getSurnameTextField() {
        return surnameTextField;
    }

    public JTextField getTelephoneTextField() {
        return telephoneTextField;
    }

    public JTextField getAddressTextField() {
        return addressTextField;
    }

    public JTextField getEmailTextField() {
        return emailTextField;
    }

    public JTextField getTownTextField() {
        return townTextField;
    }

    public JTextField getDniNifTextField() {
        return dniNifTextField;
    }

    public JTextField getAccountNumberTextField() {
        return accountNumberTextField;
    }
}
