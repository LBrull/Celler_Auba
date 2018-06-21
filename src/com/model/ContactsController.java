package com.model;

import com.persistencia.DBContactsController;
import com.persistencia.DBController;
import java.util.ArrayList;

public class ContactsController {


    private static DBController dbController;
    private static ContactsController contactsController = null;

    private ContactsController() {
        dbController = DBController.getInstance();
    }

    public static ContactsController getInstance() {
        if (contactsController == null) {
            contactsController = new ContactsController();
        }
        return contactsController;
    }

    public ArrayList<Client> getClients() {
        DBContactsController dbContactsController = DBController.getInstance().getDBContactsController();
        return dbContactsController.getClients();
    }

    public int getClientsCount() {
        return dbController.getDBContactsController().getClientsCount();
    }
}
