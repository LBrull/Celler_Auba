package com.presentacio;

import com.model.Client;
import com.model.ContactsController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ContactsViewController {

    private static ContactsViewController instance = null;
    //TODO: controladors de model associats
    private static ContactsController contactsController;

    private ManagePeople managePeople;
    private JButton AddButton;
    private JButton EditButton;
    private JButton DeleteButton;

    private ContactsViewController() {
        //TODO: get instance de tots els controladors de model associats
        contactsController = ContactsController.getInstance();
    }

    public static ContactsViewController getInstance() {
        if (instance == null) {
            instance = new ContactsViewController();
        }
        return instance;
    }

    public void createView(){
        managePeople = new ManagePeople();
        initComponents();
        initListeners();
    }

    public ArrayList<Client> getClients() {
        return contactsController.getClients();
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

    public int getClientsCount() {
        return contactsController.getClientsCount();
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}


