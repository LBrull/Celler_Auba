package com.presentacio;

import com.model.Client;
import com.model.ContactsController;
import com.model.Provider;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ContactsViewController {

    private static ContactsViewController instance = null;
    // TODO: controladors de model associats
    private static ContactsController contactsController;
    // TODO: vistes associades al controlador
    private ManagePeopleView managePeopleView;
    private AddContactView addContactView;

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

    public void contactsView(){
        managePeopleView = new ManagePeopleView();
        initComponents();
        initListeners();
    }


    public void addContactView() {
        addContactView = new AddContactView();
    }

    public ArrayList<Client> getClients() {
        return contactsController.getClients();
    }

    public ArrayList<Provider> getProviders() {
        return contactsController.getProviders();
    }

    private void initComponents() {
        AddButton = managePeopleView.getAddPersonButton();
        EditButton = managePeopleView.getEditButton();
        DeleteButton = managePeopleView.getDeleteButton();
    }

    private void initListeners() {
        AddButton.setContentAreaFilled(false);
        EditButton.setContentAreaFilled(false);
        DeleteButton.setContentAreaFilled(false);
    }

    public int getClientsCount() {
        return contactsController.getClientsCount();
    }
    public int getProvidersCount() {
        return contactsController.getProvidersCount();
    }

    public void saveNewClient() {
        String name = addContactView.getNameTextField().getText();
        String surname = addContactView.getSurnameTextField().getText();
        String telephone = addContactView.getTelephoneTextField().getText();
        String address = addContactView.getAddressTextField().getText();
        String email = addContactView.getEmailTextField().getText();
        contactsController.saveNewClient(name, surname, telephone, address, email);
    }

    public void saveNewProvider() {
        String name = addContactView.getNameTextField().getText();
        String surname = addContactView.getSurnameTextField().getText();
        String telephone = addContactView.getTelephoneTextField().getText();
        String address = addContactView.getAddressTextField().getText();
        String email = addContactView.getEmailTextField().getText();
        contactsController.saveNewProvider(name, surname, telephone, address, email);
    }

    public boolean clientExists(String name, String surname) {
        return contactsController.clientExists(name, surname);
    }

    public boolean providerExists(String name, String surname) {
        return contactsController.providerExists(name, surname);
    }

}


