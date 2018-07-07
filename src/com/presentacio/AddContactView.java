package com.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AddContactView extends JFrame {

    private static ContactsViewController controller = ContactsViewController.getInstance();

    //Icon popupSuccessIcon = new ImageIcon("/icons/ok_60x60");
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
    private JLabel emptyCp;
    private JTextField townTextField;
    private JTextField dniNifTextField;
    private JTextField accountNumberTextField;


    public AddContactView() {
        super();
        setContentPane(rootPanel);

        labelTitle.setFont(new Font("Calibri", Font.PLAIN, 20));

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

/////////////////////////////////
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
                    if (controller.clientExists(nameTextField.getText(), surnameTextField.getText())) {
                        JOptionPane.showMessageDialog(null, "El contacte ja existeix com a client", "", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        controller.saveNewClient();
                        controller.repaintClientsTable();
                        dispose();
                    }
                } else if (!checkBoxClient.isSelected() && checkBoxProvider.isSelected()) {
                    if (controller.providerExists(nameTextField.getText(), surnameTextField.getText())) {
                        JOptionPane.showMessageDialog(null, "El contacte ja existeix com a proveedor", "", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        controller.saveNewProvider();
                        controller.repaintProvidersTable();
                        dispose();
                    }
                } else {
                    if (controller.clientExists(nameTextField.getText(), surnameTextField.getText()) && controller.providerExists(nameTextField.getText(), surnameTextField.getText())) {
                        JOptionPane.showMessageDialog(null, "El contacte ja existeix con a client i com a proveedor", "", JOptionPane.INFORMATION_MESSAGE);
                    } else if (controller.clientExists(nameTextField.getText(), surnameTextField.getText())) {
                        JOptionPane.showMessageDialog(null, "El contacte ja existeix com a client", "", JOptionPane.INFORMATION_MESSAGE);
                    } else if (controller.providerExists(nameTextField.getText(), surnameTextField.getText())) {
                        JOptionPane.showMessageDialog(null, "El contacte ja existeix com a proveedor", "", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        controller.saveNewClient();
                        controller.saveNewProvider();
                        controller.repaintClientsTable();
                        controller.repaintProvidersTable();
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

    public JCheckBox getCheckBoxClient() {
        return checkBoxClient;
    }

    public JCheckBox getCheckBoxProvider() {
        return checkBoxProvider;
    }

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
