package com.presentacio;

import com.model.Client;
import com.persistencia.DBController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ContactsViewController {

    private static ContactsViewController instance = null;
    private DBController dbController = null;

    private ManagePeople managePeople;
    private JButton AddButton;
    private JButton EditButton;
    private JButton DeleteButton;

    private List<Client> clientList = new ArrayList<>();


    private ContactsViewController() {
        dbController = DBController.getInstance();
        clientList = dbController.getDBContactsController().getClients();
        System.out.println(clientList.size());
    }

    public static ContactsViewController getInstance() {
        if (instance == null) {
            instance = new ContactsViewController();
        }
        return instance;
    }

    public void run(){
        managePeople = new ManagePeople();
        initComponents();
        initListeners();
    }

    private void initComponents() {

        AddButton = managePeople.getAddPersonButton();
        EditButton = managePeople.getEditButton();
        DeleteButton = managePeople.getDeleteButton();

    }

    private void initListeners() {

        AddButton.setContentAreaFilled(false);
        EditButton.setContentAreaFilled(false);
        DeleteButton.setContentAreaFilled(false);
        AddButton.addActionListener(new AddButtonListener());
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}


