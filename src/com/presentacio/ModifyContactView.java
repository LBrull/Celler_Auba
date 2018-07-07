package com.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ModifyContactView extends JFrame{

    private static ContactsViewController controller = ContactsViewController.getInstance();

    private JButton saveButton;

    private JTextField oldName;
    private JTextField oldTelephone;
    private JTextField oldDomicili;
    private JTextField oldSurname;
    private JTextField oldEmail;
    private JTextField oldCp;
    private JTextField oldTown;

    private boolean oldProvider;
    private boolean oldClient;

    private JTextField newName;
    private JTextField newSurname;
    private JTextField newTelephone;
    private JTextField newDomicili;
    private JTextField newCp;
    private JTextField newEmail;
    private JTextField newTown;

    private JLabel labelTitle;
    private JLabel labelSubtitleOld;
    private JLabel labelSubtitleNew;
    private JPanel rootPanel;
    private JLabel emptyName;
    private JLabel emptySurname;
    private JLabel emptyTelephone;

    private JButton nameButton;
    private JButton surnameButton;
    private JButton telephoneButton;
    private JButton domiciliButton;
    private JButton emailButton;
    private JButton cpButton;
    private JButton poblacioButton;
    private JTextField oldDni;
    private JTextField oldAccountNumber;
    private JTextField newDni;
    private JTextField newAccountNumber;
    private JButton dniButton;
    private JButton accountNumberButton;


    ModifyContactView (String oldName, String oldSurname, String oldDni, boolean oldProvider, boolean oldClient, String oldTelephone, String oldCp, String oldTown, String oldDomicili, String oldEmail, String oldAccountNumber) {
        setContentPane(rootPanel);

        labelTitle.setFont(new Font("Calibri", Font.PLAIN, 20));
        labelSubtitleNew.setFont(new Font("Calibri", Font.PLAIN, 16));
        labelSubtitleOld.setFont(new Font("Calibri", Font.PLAIN, 16));

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        emptyName.setVisible(true);
        emptySurname.setVisible(true);
        emptyTelephone.setVisible(true);

        emptyName.setFont(new Font("Calibri", Font.PLAIN, 20));
        emptySurname.setFont(new Font("Calibri", Font.PLAIN, 20));
        emptyTelephone.setFont(new Font("Calibri", Font.PLAIN, 20));

        this.oldName.setText(oldName);
        this.oldSurname.setText(oldSurname);
        this.oldDni.setText(oldDni);
        this.oldAccountNumber.setText(oldAccountNumber);
        this.oldTelephone.setText(oldTelephone);
        this.oldCp.setText(oldCp);
        this.oldTown.setText(oldTown);
        this.oldDomicili.setText(oldDomicili);
        this.oldEmail.setText(oldEmail);
        this.oldClient = oldClient;
        this.oldProvider = oldProvider;

        loadListeners();

        pack();
        setSize(900, 550);
        setVisible(true);
    }

    private void loadListeners() {
        saveButton.addActionListener(e -> {
            if (!emptyName.isVisible() && !emptySurname.isVisible() && !emptyTelephone.isVisible()) { //si no hi ha cap error de rellenar formulari...
                if (oldProvider && !oldClient) { //modifiquem un proveedor
                    int rowTable = controller.getManagePeopleView().getProvidersTable().convertRowIndexToModel(controller.getManagePeopleView().getProvidersTable().getSelectedRow());
                    controller.deleteOneProvider(oldName.getText(), oldSurname.getText());
                    controller.saveOneProvider(newName.getText(), newSurname.getText(), newDni.getText(), newTelephone.getText(), newCp.getText(), newTown.getText(), newDomicili.getText(), newEmail.getText(), newAccountNumber.getText());
                    controller.repaintProvidersOneRowDeleted(rowTable);
                    controller.repaintProvidersOneRowAdded(newName.getText(), newSurname.getText(), newDni.getText(), newTelephone.getText(), newCp.getText(), newTown.getText(), newDomicili.getText(), newEmail.getText(), newAccountNumber.getText());
                    dispose();

                }
                else if (oldClient && !oldProvider) { //modifiquem un client
                    int rowTable = controller.getManagePeopleView().getClientsTable().convertRowIndexToModel(controller.getManagePeopleView().getClientsTable().getSelectedRow());
                    controller.deleteOneClient(oldName.getText(), oldSurname.getText());
                    controller.saveOneClient(newName.getText(), newSurname.getText(), newDni.getText(), newTelephone.getText(), newCp.getText(), newTown.getText(), newDomicili.getText(), newEmail.getText(), newAccountNumber.getText());
                    controller.repaintClientsOneRowDeleted(rowTable);
                    controller.repaintClientsOneRowAdded(newName.getText(), newSurname.getText(), newDni.getText(), newTelephone.getText(), newCp.getText(), newTown.getText(), newDomicili.getText(), newEmail.getText(), newAccountNumber.getText());
                    dispose();

                }
                else { //modifiquem client + proveedor
                    controller.deleteOneProvider(oldName.getText(), oldSurname.getText());
                    controller.saveOneProvider(newName.getText(), newSurname.getText(), newDni.getText(), newTelephone.getText(), newCp.getText(), newTown.getText(), newDomicili.getText(), newEmail.getText(), newAccountNumber.getText());
                    controller.repaintProvidersOneRowDeleted(controller.getManagePeopleView().getProvidersTableModel().getRow(oldName.getText(), oldSurname.getText()));
                    controller.repaintProvidersOneRowAdded(newName.getText(), newSurname.getText(), newDni.getText(), newTelephone.getText(), newCp.getText(), newTown.getText(), newDomicili.getText(), newEmail.getText(), newAccountNumber.getText());

                    controller.deleteOneClient(oldName.getText(), oldSurname.getText());
                    controller.saveOneClient(newName.getText(), newSurname.getText(), newDni.getText(), newTelephone.getText(), newCp.getText(), newTown.getText(), newDomicili.getText(), newEmail.getText(), newAccountNumber.getText());
                    controller.repaintClientsOneRowDeleted(controller.getManagePeopleView().geClientsTableModel().getRow(oldName.getText(), oldSurname.getText()));
                    controller.repaintClientsOneRowAdded(newName.getText(), newSurname.getText(), newDni.getText(), newTelephone.getText(), newCp.getText(), newTown.getText(), newDomicili.getText(), newEmail.getText(), newAccountNumber.getText());
                    dispose();
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

        cpButton.addActionListener(e-> {
            newCp.setText(oldCp.getText());
        });

        domiciliButton.addActionListener(e-> {
            newDomicili.setText(oldDomicili.getText());
        });

        emailButton.addActionListener(e-> {
            newEmail.setText(oldEmail.getText());
        });

        domiciliButton.addActionListener(e-> {
            newDomicili.setText(oldDomicili.getText());
        });

        poblacioButton.addActionListener(e-> {
            newTown.setText(oldTown.getText());
        });

        dniButton.addActionListener(e-> {
            newDni.setText(oldDni.getText());
        });

        accountNumberButton.addActionListener(e-> {
            newAccountNumber.setText(oldAccountNumber.getText());
        });

    }
}
