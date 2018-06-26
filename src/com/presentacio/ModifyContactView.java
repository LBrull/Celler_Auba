package com.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ModifyContactView extends JFrame{

    private static ContactsViewController controller = ContactsViewController.getInstance();

    private JButton saveButton;
    private JCheckBox oldProviderCheckBox;
    private JCheckBox oldClientCheckBox;
    private JCheckBox newProviderCheck;
    private JCheckBox newClientCheck;
    private JTextField oldName;
    private JTextField oldTelephone;
    private JTextField oldAddress;
    private JTextField newName;
    private JTextField newSurname;
    private JTextField newTelephone;
    private JTextField newAddress;
    private JTextField oldSurname;
    private JTextField oldEmail;
    private JTextField newEmail;
    private JLabel labelTitle;
    private JLabel labelSubtitleOld;
    private JLabel labelSubtitleNew;
    private JPanel rootPanel;
    private JLabel emptyName;
    private JLabel emptySurname;
    private JLabel emptyType;
    private JLabel emptyTelephone;
    private JButton nameButton;
    private JButton surnameButton;
    private JButton typeButton;
    private JButton telephoneButton;
    private JButton addressButton;
    private JButton emailButton;
    private JButton button1;

    private boolean oldProvider;
    private boolean oldClient;

    ModifyContactView (String oldName, String oldSurname, boolean oldProvider, boolean oldClient, String oldTelephone, String oldAddress, String oldEmail) {
        setContentPane(rootPanel);

        labelTitle.setFont(new Font("Calibri", Font.PLAIN, 20));
        labelSubtitleNew.setFont(new Font("Calibri", Font.PLAIN, 16));
        labelSubtitleOld.setFont(new Font("Calibri", Font.PLAIN, 16));

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); //TODO: override window closing method

        emptyName.setVisible(true);
        emptySurname.setVisible(true);
        emptyTelephone.setVisible(true);

        this.oldName.setText(oldName);
        this.oldSurname.setText(oldSurname);
        this.oldTelephone.setText(oldTelephone);
        this.oldAddress.setText(oldAddress);
        this.oldEmail.setText(oldEmail);
        this.oldClient = oldClient;
        this.oldProvider = oldProvider;

        loadListeners();

        pack();
        setSize(900, 500);
        setVisible(true);
    }

    private void loadListeners() {
        saveButton.addActionListener(e -> {
            if (!emptyName.isVisible() && !emptySurname.isVisible() && !emptyTelephone.isVisible()) { //si no hi ha cap error de rellenar formulari...
                if (oldProvider && !oldClient) { //modifiquem un proveedor
                    int rowTable = controller.getManagePeopleView().getProvidersTable().convertRowIndexToModel(controller.getManagePeopleView().getProvidersTable().getSelectedRow());
                    if (!controller.providerExists(newName.getText(), newSurname.getText())) {
                        controller.deleteOneProvider(oldName.getText(), oldSurname.getText());
                        controller.saveOneProvider(newName.getText(), newSurname.getText(), newTelephone.getText(), newAddress.getText(), newEmail.getText());
                        controller.repaintProvidersOneRowDeleted(rowTable);
                        controller.repaintProvidersOneRowAdded(newName.getText(), newSurname.getText(), newTelephone.getText(), newAddress.getText(), newEmail.getText());
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "El nou proveedor que intenteu desar ja existeix", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else if (oldClient && !oldProvider) { //modifiquem un client
                    int rowTable = controller.getManagePeopleView().getClientsTable().convertRowIndexToModel(controller.getManagePeopleView().getClientsTable().getSelectedRow());
                    if (!controller.clientExists(newName.getText(), newSurname.getText())) {
                        controller.deleteOneClient(oldName.getText(), oldSurname.getText());
                        controller.saveOneClient(newName.getText(), newSurname.getText(), newTelephone.getText(), newAddress.getText(), newEmail.getText());
                        controller.repaintClientsOneRowDeleted(rowTable);
                        controller.repaintClientsOneRowAdded(newName.getText(), newSurname.getText(), newTelephone.getText(), newAddress.getText(), newEmail.getText());
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "El nou client que intenteu desar ja existeix", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else { //modifiquem client + proveedor
                    if (!controller.clientExists(newName.getText(), newSurname.getText()) && !controller.providerExists(newName.getText(), newSurname.getText())) {
                        controller.deleteOneProvider(oldName.getText(), oldSurname.getText());
                        controller.saveOneProvider(newName.getText(), newSurname.getText(), newTelephone.getText(), newAddress.getText(), newEmail.getText());
                        controller.repaintProvidersOneRowDeleted(controller.getManagePeopleView().getProvidersTableModel().getRow(oldName.getText(), oldSurname.getText()));
                        controller.repaintProvidersOneRowAdded(newName.getText(), newSurname.getText(), newTelephone.getText(), newAddress.getText(), newEmail.getText());

                        controller.deleteOneClient(oldName.getText(), oldSurname.getText());
                        controller.saveOneClient(newName.getText(), newSurname.getText(), newTelephone.getText(), newAddress.getText(), newEmail.getText());
                        controller.repaintClientsOneRowDeleted(controller.getManagePeopleView().geClientsTableModel().getRow(oldName.getText(), oldSurname.getText()));
                        controller.repaintClientsOneRowAdded(newName.getText(), newSurname.getText(), newTelephone.getText(), newAddress.getText(), newEmail.getText());
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "El nou contacte que intenteu desar ja existeix com a client i/o proveedor", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Heu d'omplir els camps obligatoris", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        newName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(newName.getText().length()<=0 || newName.getText().equals("")) {
                    emptyName.setVisible(true);
                }
                else {
                    emptyName.setVisible(false);
                }
            }
        });

        newSurname.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(newSurname.getText().length()<=0 || newSurname.getText().equals("")) {
                    emptySurname.setVisible(true);
                }
                else {
                    emptySurname.setVisible(false);
                }
            }
        });

        newTelephone.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(newTelephone.getText().length()<=0 || newTelephone.getText().equals("")) {
                    emptyTelephone.setVisible(true);
                }
                else {
                    emptyTelephone.setVisible(false);
                }
            }
        });

        nameButton.addActionListener(e-> {
            newName.setText(oldName.getText());
            emptyName.setVisible(false);
        });

        surnameButton.addActionListener(e-> {
            newSurname.setText(oldSurname.getText());
            emptySurname.setVisible(false);
        });

        telephoneButton.addActionListener(e-> {
            newTelephone.setText(oldTelephone.getText());
            emptyTelephone.setVisible(false);
        });

        addressButton.addActionListener(e-> {
            newAddress.setText(oldAddress.getText());
        });

        emailButton.addActionListener(e-> {
            newEmail.setText(oldEmail.getText());
        });
    }
}
