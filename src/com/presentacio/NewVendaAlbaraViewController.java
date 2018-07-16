package com.presentacio;

import com.model.Client;
import com.model.ContactsController;
import com.model.Provider;
import com.persistencia.UtilsController;

import java.util.ArrayList;
import java.util.List;

public class NewVendaAlbaraViewController {
    private static NewVendaAlbaraViewController instance = null;
    private ContactsController contactsController = ContactsController.getInstance();
    private UtilsController utilsController = UtilsController.getInstance();

    private NewVendaAlbaraViewController() {
    }

    public static NewVendaAlbaraViewController getInstance() {
        if (instance == null) {
            instance = new NewVendaAlbaraViewController();
        }
        return instance;
    }

    public List<String> getProviders() {
        ArrayList<Provider> data = contactsController.getProviders();
        List<String> result = new ArrayList<>();
        for (Provider aData : data) {
            String name = aData.getName();
            String surname = aData.getSurname();
            String completeName = name.concat(" ");
            completeName = completeName.concat(surname);
            result.add(completeName);
        }
        return result;

    }

    public List<String> getClients() {
        ArrayList<Client> data = contactsController.getClients();
        List<String> result = new ArrayList<>();
        for (Client aData : data) {
            String name = aData.getName();
            String surname = aData.getSurname();
            String completeName = name.concat(" ");
            completeName = completeName.concat(surname);
            result.add(completeName);
        }
        return result;
    }

    public String getNextAmetllaAlbaraNumber() {
        return utilsController.getNextAmetllaAlbaraNumber();
    }

    public String getNextRaimAlbaraNumber() {
        return utilsController.getNextRaimAlbaraNumber();
    }

    public String getNextOlivaAlbaraNumber() {
        return utilsController.getNextOlivaAlbaraNumber();
    }
}
