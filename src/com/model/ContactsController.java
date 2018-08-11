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

    public ServerResponse saveNewClient(Client client) throws IOException, JSONException {
        return  dbController.saveNewClient(client);
    }

    public ServerResponse saveNewProvider(Provider provider) throws IOException, JSONException {
        return dbController.saveNewProvider(provider);
    }

    public boolean clientExists(String name, String surname) throws IOException, JSONException {
        return dbController.clientExists(name, surname);
    }

    public boolean providerExists(String name, String surname) throws IOException, JSONException {
        return dbController.providerExists(name, surname);
    }

    public ServerResponse deleteOneProvider(String objectId) throws IOException {
        return dbController.deleteOneProvider(objectId);
    }

    public ServerResponse deleteOneClient(String objectId) throws IOException {
        return dbController.deleteOneClient(objectId);
    }

}
