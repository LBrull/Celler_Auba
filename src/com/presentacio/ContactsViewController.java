package com.presentacio;

import com.model.Client;
import com.model.ContactsController;
import com.model.Provider;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class ContactsViewController {

    private static ContactsViewController instance = null;
    // TODO: controladors de model associats
    private static ContactsController contactsController;

    // TODO: vistes associades al controlador
    private ManagePeopleView managePeopleView;
    private AddContactView addContactView;
    private ModifyContactView modifyContactView;

    private JButton AddButton;
    private JButton EditButton;
    private JButton DeleteButton;
    private JTable providersTable;
    private JTable clientsTable;


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

    public ManagePeopleView getManagePeopleView() {
        return managePeopleView;
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
        providersTable = managePeopleView.getProvidersTable();
        clientsTable = managePeopleView.getClientsTable();
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

    public void repaintClientsTable() {
        Vector<String> data = new Vector<>();
        data.add(addContactView.getNameTextField().getText());
        data.add(addContactView.getSurnameTextField().getText());
        data.add(addContactView.getTelephoneTextField().getText());
        data.add(addContactView.getAddressTextField().getText());
        data.add(addContactView.getEmailTextField().getText());
        managePeopleView.getClientsTableModel().addRow(data);
    }

    public void repaintProvidersTable() {
        Vector<String> data = new Vector<>();
        data.add(addContactView.getNameTextField().getText());
        data.add(addContactView.getSurnameTextField().getText());
        data.add(addContactView.getTelephoneTextField().getText());
        data.add(addContactView.getAddressTextField().getText());
        data.add(addContactView.getEmailTextField().getText());
        managePeopleView.getProvidersTableModel().addRow(data);
    }

    public void deleteOneProvider(String name, String surname) {
        contactsController.deleteOneProvider(name, surname);
    }

    public void deleteOneClient(String name, String surname) {
        contactsController.deleteOneClient(name, surname);
    }

    public void saveOneClient(String name, String surname, String telephone, String address, String email) {
        contactsController.saveNewClient(name, surname, telephone, address, email);
    }

    public void saveOneProvider(String name, String surname, String telephone, String address, String email) {
        contactsController.saveNewProvider(name, surname, telephone, address, email);
    }

    public void repaintProvidersOneRowDeleted(int row) {
        managePeopleView.getProvidersTableModel().removeRow(row);

    }

    public void repaintClientsOneRowDeleted(int row) {
        managePeopleView.getClientsTableModel().removeRow(row);
    }

    public void repaintProvidersOneRowAdded(String name, String surname, String telephone, String address, String email) {
        Vector<String> data = new Vector<>();
        data.add(name);
        data.add(surname);
        data.add(telephone);
        data.add(address);
        data.add(email);
        managePeopleView.getProvidersTableModel().addRow(data);
    }

    public void repaintClientsOneRowAdded(String name, String surname, String telephone, String address, String email) {
        Vector<String> data = new Vector<>();
        data.add(name);
        data.add(surname);
        data.add(telephone);
        data.add(address);
        data.add(email);
        managePeopleView.getClientsTableModel().addRow(data);
    }

    public void ModifyContactView() {
        if (1 == providersTable.getSelectedRowCount()) {
//            providersTable.convertRowIndexToModel()
            String oldName = providersTable.getModel().getValueAt(providersTable.getSelectedRow(), 0).toString();
            String oldSurname = providersTable.getModel().getValueAt(providersTable.getSelectedRow(), 1).toString();
            String oldTelephone = providersTable.getModel().getValueAt(providersTable.getSelectedRow(), 2).toString();
            String oldAddress = providersTable.getModel().getValueAt(providersTable.getSelectedRow(), 3).toString();
            String oldEmail = providersTable.getModel().getValueAt(providersTable.getSelectedRow(), 4).toString();
            boolean oldProvider = providerExists(oldName, oldSurname);
            boolean oldClient = clientExists(oldName, oldSurname);
            modifyContactView = new ModifyContactView(oldName, oldSurname, oldProvider, oldClient, oldTelephone, oldAddress, oldEmail);
        }

        else if (1 == clientsTable.getSelectedRowCount()) {
            String oldName = clientsTable.getModel().getValueAt(clientsTable.getSelectedRow(), 0).toString();
            String oldSurname = clientsTable.getModel().getValueAt(clientsTable.getSelectedRow(), 1).toString();
            String oldTelephone = clientsTable.getModel().getValueAt(clientsTable.getSelectedRow(), 2).toString();
            String oldAddress = clientsTable.getModel().getValueAt(clientsTable.getSelectedRow(), 3).toString();
            String oldEmail = clientsTable.getModel().getValueAt(clientsTable.getSelectedRow(), 4).toString();
            boolean oldProvider = providerExists(oldName, oldSurname);
            boolean oldClient = clientExists(oldName, oldSurname);
            modifyContactView = new ModifyContactView(oldName, oldSurname, oldProvider, oldClient, oldTelephone, oldAddress, oldEmail);
        }
    }

    public void saveNewClient(String name, String surname, String telephone, String address, String email) {
        contactsController.saveNewClient(name, surname, telephone, address, email);
    }

    public void saveNewProvider(String name, String surname, String telephone, String address, String email) {
        contactsController.saveNewProvider(name, surname, telephone, address, email);
    }

    public ModifyContactView getModifyContactView() {
        return modifyContactView;
    }
}


