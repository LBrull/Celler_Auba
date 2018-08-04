package com.model;

import com.persistencia.DBContactsController;
import com.persistencia.DBController;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class ContactsController {


    private static DBController dbController;
    private static ContactsController contactsController = null;
    private ArrayList<Client> clients = null;
    private ArrayList<Provider> providers = null;

    private ContactsController() {
        dbController = DBController.getInstance();
    }

    public static ContactsController getInstance() {
        if (contactsController == null) {
            contactsController = new ContactsController();
        }
        return contactsController;
    }

    public ArrayList<Client> getClients() throws IOException, JSONException {
        DBContactsController dbContactsController = DBController.getInstance().getDBContactsController();
        clients = dbContactsController.getClients();
        return clients;
    }

    public ArrayList<Provider> getProviders() throws IOException, JSONException {
        DBContactsController dbContactsController = DBController.getInstance().getDBContactsController();
        providers = dbContactsController.getProviders();
        return providers;
    }

    public void saveNewClient(String name, String surname, String dni, String telephone, String cp, String town, String address, String email, String accountNumber) {
        Client client = new Client(name, surname, dni, telephone, cp, town, address, email, accountNumber);
        dbController.saveNewClient(client);
    }

    public void saveNewProvider(String name, String surname, String dni, String telephone, String cp, String town, String address, String email, String accountNumber) {
        Provider provider = new Provider(name, surname, dni, telephone, cp, town, address, email, accountNumber);
        dbController.saveNewProvider(provider);
    }

    public boolean clientExists(String name, String surname) throws IOException, JSONException {
        return dbController.clientExists(name, surname);
    }

    public boolean providerExists(String name, String surname) throws IOException, JSONException {
        return dbController.providerExists(name, surname);
    }

    public void deleteOneProvider(String name, String surname) {
        dbController.deleteOneProvider(name, surname);
    }

    public void deleteOneClient(String name, String surname) {
        dbController.deleteOneClient(name, surname);
    }

}
