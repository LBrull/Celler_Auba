package com.presentacio;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;
import java.awt.*;

public class AddContactView extends JFrame {

    private static final int SETUP_WIDTH = 550;
    private static final int SETUP_HEIGHT = 400;
    private static ContactsViewController controller = ContactsViewController.getInstance();

    Icon popupSuccessIcon = new ImageIcon("/src/icons/ok_60x60");

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

    public AddContactView() {
        super();
        setContentPane(rootPanel);

        labelTitle.setFont(new Font("Calibri", Font.PLAIN, 20));

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); //TODO: override window closing method
        setSize(SETUP_WIDTH, SETUP_HEIGHT);

        saveButton.addActionListener(e -> {
            if(checkBoxClient.isSelected() && !checkBoxProvider.isSelected()) {
                if (controller.clientExists(nameTextField.getText(), surnameTextField.getText())) {
                    JOptionPane.showMessageDialog (null, "El contacte ja existeix com a client", "", JOptionPane.INFORMATION_MESSAGE);
                }//TODO: show dialog client alredy exists
                else {
                    controller.saveNewClient();
                    JOptionPane.showMessageDialog (null, "Contacte desat amb èxit", "", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else if (!checkBoxClient.isSelected() && checkBoxProvider.isSelected()) {
                if (controller.providerExists(nameTextField.getText(), surnameTextField.getText())) {
                    JOptionPane.showMessageDialog (null, "El contacte ja existeix com a proveedor", "", JOptionPane.INFORMATION_MESSAGE);
                }//TODO: show dialog provider alredy exists
                else {
                    controller.saveNewClient();
                    JOptionPane.showMessageDialog (null, "Contacte desat amb èxit", "", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else if (checkBoxClient.isSelected() && checkBoxProvider.isSelected()) {
                if (controller.clientExists(nameTextField.getText(), surnameTextField.getText()) && controller.providerExists(nameTextField.getText(), surnameTextField.getText())) {
                    JOptionPane.showMessageDialog (null, "El contacte ja existeix con a client i com a proveedor", "", JOptionPane.INFORMATION_MESSAGE);
                }//TODO: show dialog client and provider alredy exist

                else if (controller.clientExists(nameTextField.getText(), surnameTextField.getText())) {
                    JOptionPane.showMessageDialog (null, "El contacte ja existeix com a client", "", JOptionPane.INFORMATION_MESSAGE);
                } //TODO: show dialog contact exists as client
                else if (controller.providerExists(nameTextField.getText(), surnameTextField.getText())) {
                    JOptionPane.showMessageDialog (null, "El contacte ja existeix com a proveedor", "", JOptionPane.INFORMATION_MESSAGE);
                } //TODO: show dialog contact exists as provider
                else {
                    controller.saveNewClient();
                    controller.saveNewProvider();
                    JOptionPane.showMessageDialog (null, "Contacte desat amb èxit", "", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog (null, "Heu de seleccionar, almenys, un tipus de contacte", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        pack();
        setVisible(true);
    }

    public JCheckBox getCheckBoxClient() {
        return checkBoxClient;
    }

    public JCheckBox getCheckBoxProvider() {
        return checkBoxProvider;
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
}
