package com.presentacio;

import com.model.Client;
import com.model.ContactsController;
import com.model.Provider;
import com.model.ServerResponse;
import com.persistencia.DBContactsController;
import org.json.JSONException;

import javax.swing.*;
import java.io.IOException;
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

    public ArrayList<Client> getClients() throws IOException, JSONException {
        return contactsController.getClients();
    }

    public ArrayList<Provider> getProviders() throws IOException, JSONException {
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

    public ServerResponse saveNewClient() throws IOException, JSONException {
        String name = newContactView.getNameTextField().getText();
        String surname = newContactView.getSurnameTextField().getText();
        String dniNif = newContactView.getDniNifTextField().getText();
        String accountNumber = newContactView.getAccountNumberTextField().getText();
        String telephone = newContactView.getTelephoneTextField().getText();
        String cp = newContactView.getCpTextField().getText();
        String town = newContactView.getTownTextField().getText();
        String address = newContactView.getAddressTextField().getText();
        String email = newContactView.getEmailTextField().getText();
        Client client = new Client(null, name, surname, dniNif, telephone, cp, town, address, email, accountNumber);
        return contactsController.saveNewClient(client);
    }

    public ServerResponse saveNewProvider() throws IOException, JSONException {
        String name = newContactView.getNameTextField().getText();
        String surname = newContactView.getSurnameTextField().getText();
        String dniNif = newContactView.getDniNifTextField().getText();
        String accountNumber = newContactView.getAccountNumberTextField().getText();
        String telephone = newContactView.getTelephoneTextField().getText();
        String cp = newContactView.getCpTextField().getText();
        String town = newContactView.getTownTextField().getText();
        String address = newContactView.getAddressTextField().getText();
        String email = newContactView.getEmailTextField().getText();
        Provider provider = new Provider(null, name, surname, dniNif, telephone, cp, town, address, email, accountNumber);
        return contactsController.saveNewProvider(provider);
    }

    public boolean clientExists(String name, String surname) throws IOException, JSONException {
        return contactsController.clientExists(name, surname);
    }

    public boolean providerExists(String name, String surname) throws IOException, JSONException {
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

    public ServerResponse deleteOneProvider(String objectId) throws IOException {
        return contactsController.deleteOneProvider(objectId);
    }

    public ServerResponse deleteOneClient(String objectId) throws IOException {
        return contactsController.deleteOneClient(objectId);
    }

    public ServerResponse saveOneClient(String name, String surname, String dni, String telephone, String cp, String town, String address, String email, String accountNumber) throws IOException, JSONException {
        Client client = new Client();
        client.setName(name);
        client.setSurname(surname);
        client.setDni_nif(dni);
        client.setTelephone(telephone);
        client.setCP(cp);
        client.setTown(town);
        client.setAddress(address);
        client.setEmail(email);
        client.setAccountNumber(accountNumber);
        return contactsController.saveNewClient(client);
    }

    public ServerResponse saveOneProvider(String name, String surname, String dni, String telephone, String cp, String town, String address, String email, String accountNumber) throws IOException, JSONException {
        Provider provider = new Provider();
        provider.setName(name);
        provider.setSurname(surname);
        provider.setDni_nif(dni);
        provider.setTelephone(telephone);
        provider.setCP(cp);
        provider.setTown(town);
        provider.setAddress(address);
        provider.setEmail(email);
        provider.setAccountNumber(accountNumber);
        return contactsController.saveNewProvider(provider);
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

    public void ModifyContactView() throws IOException, JSONException {
        if (1 == providersTable.getSelectedRowCount()) {
            int rowTable = providersTable.convertRowIndexToModel(providersTable.getSelectedRow());
            String objectId = providersTable.getModel().getValueAt(rowTable, 0).toString();
            String oldName = providersTable.getModel().getValueAt(rowTable, 1).toString();
            String oldSurname = providersTable.getModel().getValueAt(rowTable, 2).toString();
            String oldDNI = providersTable.getModel().getValueAt(rowTable, 3).toString();
            String oldAccountNumber = providersTable.getModel().getValueAt(rowTable, 4).toString();
            String oldTelephone = providersTable.getModel().getValueAt(rowTable, 5).toString();
            String oldEmail = providersTable.getModel().getValueAt(rowTable, 6).toString();
            String oldCp = providersTable.getModel().getValueAt(rowTable, 7).toString();
            String oldTown = providersTable.getModel().getValueAt(rowTable, 8).toString();
            String oldAddress = providersTable.getModel().getValueAt(rowTable, 9).toString();


            boolean oldProvider = providerExists(oldName, oldSurname);
            boolean oldClient = clientExists(oldName, oldSurname);
            modifyContactView = new ModifyContactView(objectId, oldName, oldSurname, oldDNI, oldProvider, oldClient, oldTelephone, oldCp, oldTown, oldAddress, oldEmail, oldAccountNumber);
        }

        else if (1 == clientsTable.getSelectedRowCount()) {
            int rowTable = clientsTable.convertRowIndexToModel(clientsTable.getSelectedRow());
            String objectId = providersTable.getModel().getValueAt(rowTable, 0).toString();
            String oldName = clientsTable.getModel().getValueAt(rowTable, 1).toString();
            String oldSurname = clientsTable.getModel().getValueAt(rowTable, 2).toString();
            String oldDNI = clientsTable.getModel().getValueAt(rowTable, 3).toString();
            String oldAccountNumber = clientsTable.getModel().getValueAt(rowTable, 4).toString();
            String oldTelephone = clientsTable.getModel().getValueAt(rowTable, 5).toString();
            String oldEmail = clientsTable.getModel().getValueAt(rowTable, 6).toString();
            String oldCp = clientsTable.getModel().getValueAt(rowTable, 7).toString();
            String oldTown = clientsTable.getModel().getValueAt(rowTable, 8).toString();
            String oldAddress = clientsTable.getModel().getValueAt(rowTable, 9).toString();

            boolean oldProvider = providerExists(oldName, oldSurname);
            boolean oldClient = clientExists(oldName, oldSurname);
            modifyContactView = new ModifyContactView(objectId, oldName, oldSurname, oldDNI, oldProvider, oldClient, oldTelephone, oldCp, oldTown, oldAddress, oldEmail, oldAccountNumber);
        }
    }

    public ModifyContactView getModifyContactView() {
        return modifyContactView;
    }

    public static ServerResponse editProvider(String objectId, String name, String surname, String dni_nif, String telephone, String cp, String town, String address, String email, String accountNumber) throws IOException, JSONException {
        Provider provider = new Provider( objectId,  name,  surname,  dni_nif,  telephone,  cp,  town,  email,  address,  accountNumber);
        return DBContactsController.editProvider(provider);
    }

    public void repaintProvidersTableWhenEdit(int rowTable, Provider newProvider) {
        ContactsTableModel providersTableModel = managePeopleView.getProvidersTableModel();
        providersTableModel.setValueAt(newProvider.getName(), rowTable, 1);
        providersTableModel.setValueAt(newProvider.getSurname(), rowTable, 2);
        providersTableModel.setValueAt(newProvider.getDni_nif(), rowTable, 3);
        providersTableModel.setValueAt(newProvider.getAccountNumber(), rowTable, 4);
        providersTableModel.setValueAt(newProvider.getTelephone(), rowTable, 5);
        providersTableModel.setValueAt(newProvider.getEmail(), rowTable, 6);
        providersTableModel.setValueAt(newProvider.getCp(), rowTable, 7);
        providersTableModel.setValueAt(newProvider.getTown(), rowTable, 8);
        providersTableModel.setValueAt(newProvider.getAddress(), rowTable, 9);
    }
}


