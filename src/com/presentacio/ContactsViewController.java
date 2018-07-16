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
    private NewContactView newContactView;
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

    public void showContactsView(){
        managePeopleView = new ManagePeopleView();
        initComponents();
    }

    public ManagePeopleView getManagePeopleView() {
        return managePeopleView;
    }

    public void addContactView() {
        newContactView = new NewContactView();
    }

    public JTable getProvidersTable() {
        return providersTable;
    }

    public JTable getClientsTable() {
        return clientsTable;
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
    }

    public int getClientsCount() {
        return contactsController.getClientsCount();
    }
    public int getProvidersCount() {
        return contactsController.getProvidersCount();
    }

    public void saveNewClient() {
        String name = newContactView.getNameTextField().getText();
        String surname = newContactView.getSurnameTextField().getText();
        String dniNif = newContactView.getDniNifTextField().getText();
        String accountNumber = newContactView.getAccountNumberTextField().getText();
        String telephone = newContactView.getTelephoneTextField().getText();
        String cp = newContactView.getCpTextField().getText();
        String town = newContactView.getTownTextField().getText();
        String address = newContactView.getAddressTextField().getText();
        String email = newContactView.getEmailTextField().getText();
        contactsController.saveNewClient(name, surname, dniNif, telephone, cp, town, address, email, accountNumber);
    }

    public void saveNewProvider() {
        String name = newContactView.getNameTextField().getText();
        String surname = newContactView.getSurnameTextField().getText();
        String dniNif = newContactView.getDniNifTextField().getText();
        String accountNumber = newContactView.getAccountNumberTextField().getText();
        String telephone = newContactView.getTelephoneTextField().getText();
        String cp = newContactView.getCpTextField().getText();
        String town = newContactView.getTownTextField().getText();
        String address = newContactView.getAddressTextField().getText();
        String email = newContactView.getEmailTextField().getText();
        contactsController.saveNewProvider(name, surname, dniNif, telephone, cp, town, address, email, accountNumber);
    }

    public boolean clientExists(String name, String surname) {
        return contactsController.clientExists(name, surname);
    }

    public boolean providerExists(String name, String surname) {
        return contactsController.providerExists(name, surname);
    }

    public void repaintClientsTable() {
        Vector<String> data = new Vector<>();
        data.add(newContactView.getNameTextField().getText());
        data.add(newContactView.getSurnameTextField().getText());
        data.add(newContactView.getDniNifTextField().getText());
        data.add(newContactView.getAccountNumberTextField().getText());
        data.add(newContactView.getTelephoneTextField().getText());
        data.add(newContactView.getEmailTextField().getText());
        data.add(newContactView.getCpTextField().getText());
        data.add(newContactView.getTownTextField().getText());
        data.add(newContactView.getAddressTextField().getText());
        managePeopleView.getClientsTableModel().addRow(data);
    }

    public void repaintProvidersTable() {
        Vector<String> data = new Vector<>();
        data.add(newContactView.getNameTextField().getText());
        data.add(newContactView.getSurnameTextField().getText());
        data.add(newContactView.getDniNifTextField().getText());
        data.add(newContactView.getAccountNumberTextField().getText());
        data.add(newContactView.getTelephoneTextField().getText());
        data.add(newContactView.getEmailTextField().getText());
        data.add(newContactView.getCpTextField().getText());
        data.add(newContactView.getTownTextField().getText());
        data.add(newContactView.getAddressTextField().getText());
        managePeopleView.getProvidersTableModel().addRow(data);
    }

    public void deleteOneProvider(String name, String surname) {
        contactsController.deleteOneProvider(name, surname);
    }

    public void deleteOneClient(String name, String surname) {
        contactsController.deleteOneClient(name, surname);
    }

    public void saveOneClient(String name, String surname, String dni, String telephone, String cp, String town, String address, String email, String accountNumber) {
        contactsController.saveNewClient(name, surname, dni, telephone, cp, town, address, email, accountNumber);
    }

    public void saveOneProvider(String name, String surname, String dni, String telephone, String cp, String town, String address, String email, String accountNumber) {
        contactsController.saveNewProvider(name, surname, dni, telephone, cp, town, address, email, accountNumber);
    }

    public void repaintProvidersOneRowDeleted(int row) {
        managePeopleView.getProvidersTableModel().removeRow(row);

    }

    public void repaintClientsOneRowDeleted(int row) {
        managePeopleView.getClientsTableModel().removeRow(row);
    }

    public void repaintProvidersOneRowAdded(String name, String surname, String dni, String telephone, String cp, String town, String address, String email, String accountNumber) {
        Vector<String> data = new Vector<>();
        data.add(name);
        data.add(surname);
        data.add(dni);
        data.add(accountNumber);
        data.add(telephone);
        data.add(email);
        data.add(cp);
        data.add(town);
        data.add(address);

        managePeopleView.getProvidersTableModel().addRow(data);
    }

    public void repaintClientsOneRowAdded(String name, String surname, String dni, String telephone, String cp, String town, String address, String email, String accountNumber) {
        Vector<String> data = new Vector<>();
        data.add(name);
        data.add(surname);
        data.add(dni);
        data.add(accountNumber);
        data.add(telephone);
        data.add(email);
        data.add(cp);
        data.add(town);
        data.add(address);

        managePeopleView.getClientsTableModel().addRow(data);
    }

    public void ModifyContactView() {
        if (1 == providersTable.getSelectedRowCount()) {
            int rowTable = providersTable.convertRowIndexToModel(providersTable.getSelectedRow());
            String oldName = providersTable.getModel().getValueAt(rowTable, 0).toString();
            String oldSurname = providersTable.getModel().getValueAt(rowTable, 1).toString();
            String oldDNI = providersTable.getModel().getValueAt(rowTable, 2).toString();
            String oldAccountNumber = providersTable.getModel().getValueAt(rowTable, 3).toString();
            String oldTelephone = providersTable.getModel().getValueAt(rowTable, 4).toString();
            String oldEmail = providersTable.getModel().getValueAt(rowTable, 5).toString();
            String oldCp = providersTable.getModel().getValueAt(rowTable, 6).toString();
            String oldTown = providersTable.getModel().getValueAt(rowTable, 7).toString();
            String oldAddress = providersTable.getModel().getValueAt(rowTable, 8).toString();


            boolean oldProvider = providerExists(oldName, oldSurname);
            boolean oldClient = clientExists(oldName, oldSurname);
            modifyContactView = new ModifyContactView(oldName, oldSurname, oldDNI, oldProvider, oldClient, oldTelephone, oldCp, oldTown, oldAddress, oldEmail, oldAccountNumber);
        }

        else if (1 == clientsTable.getSelectedRowCount()) {
            int rowTable = clientsTable.convertRowIndexToModel(clientsTable.getSelectedRow());
            String oldName = clientsTable.getModel().getValueAt(rowTable, 0).toString();
            String oldSurname = clientsTable.getModel().getValueAt(rowTable, 1).toString();
            String oldDNI = clientsTable.getModel().getValueAt(rowTable, 2).toString();
            String oldAccountNumber = clientsTable.getModel().getValueAt(rowTable, 3).toString();
            String oldTelephone = clientsTable.getModel().getValueAt(rowTable, 4).toString();
            String oldEmail = clientsTable.getModel().getValueAt(rowTable, 5).toString();
            String oldCp = clientsTable.getModel().getValueAt(rowTable, 6).toString();
            String oldTown = clientsTable.getModel().getValueAt(rowTable, 7).toString();
            String oldAddress = clientsTable.getModel().getValueAt(rowTable, 8).toString();

            boolean oldProvider = providerExists(oldName, oldSurname);
            boolean oldClient = clientExists(oldName, oldSurname);
            modifyContactView = new ModifyContactView(oldName, oldSurname, oldDNI, oldProvider, oldClient, oldTelephone, oldCp, oldTown, oldAddress, oldEmail, oldAccountNumber);
        }
    }

    public ModifyContactView getModifyContactView() {
        return modifyContactView;
    }
}


