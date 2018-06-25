package com.persistencia;

import com.model.Client;
import com.model.Provider;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DBContactsController {

    DBContactsController() {
    }

    public ArrayList<Client> getClients () {
        ArrayList<Client> list = new ArrayList<>();
        List<Document> documents = DBController.getMongoDB().getCollection("clients").find().into(new ArrayList<>());

        for(Document document : documents){
            Client client = new Client();

            client.setName(document.getString("name"));
            client.setSurname(document.getString("surname"));
            client.setTelephone(document.getString("telephone"));
            client.setAddress(document.getString("address"));
            client.setEmail(document.getString("email"));
            list.add(client);
        }
        return list;
    }

    public ArrayList<Provider> getProviders() {
        ArrayList<Provider> list = new ArrayList<>();
        List<Document> documents = DBController.getMongoDB().getCollection("providers").find().into(new ArrayList<>());

        for(Document document : documents){
            Provider provider = new Provider();

            provider.setName(document.getString("name"));
            provider.setSurname(document.getString("surname"));
            provider.setTelephone(document.getString("telephone"));
            provider.setAddress(document.getString("address"));
            provider.setEmail(document.getString("email"));
            list.add(provider);
        }
        return list;
    }

    public int getClientsCount() {
        return (int) DBController.getMongoDB().getCollection("clients").count();
    }

    public int getProvidersCount() {
        return (int) DBController.getMongoDB().getCollection("providers").count();
    }

    public void saveNewClient(Client client) {
        MongoCollection<Document> collection = DBController.getMongoDB().getCollection("clients");
        Document newClient = new Document();
        newClient.append("name", client.getName());
        newClient.append("surname", client.getSurname());
        newClient.append("telephone", client.getTelephone());
        newClient.append("address", client.getAddress());
        newClient.append("email", client.getEmail());
        collection.insertOne(newClient);
    }

    public void saveNewProvider(Provider provider) {
        MongoCollection<Document> collection = DBController.getMongoDB().getCollection("providers");
        Document newProvider = new Document();
        newProvider.append("name", provider.getName());
        newProvider.append("surname", provider.getSurname());
        newProvider.append("telephone", provider.getTelephone());
        newProvider.append("address", provider.getAddress());
        newProvider.append("email", provider.getEmail());
        collection.insertOne(newProvider);
    }

    public boolean clientExists(String name, String surname) {
        List<Client> clients = getClients();
        boolean found = false;
        int i=0;
        while (!found && i<clients.size()) {
            if (clients.get(i).getName().equals(name) && clients.get(i).getSurname().equals(surname)) found = true;
            ++i;
        }
        return found;
    }

    public boolean providerExists(String name, String surname) {
        List<Provider> providers = getProviders();
        boolean found = false;
        int i=0;
        while (!found && i<providers.size()) {
            if (providers.get(i).getName().equals(name) && providers.get(i).getSurname().equals(surname)) found = true;
            ++i;
        }
        return found;
    }

    public void deleteOneProvider(String name, String surname) {
        Document providerToDelete = new Document();
        providerToDelete.append("name", name);
        providerToDelete.append("surname", surname);
        DBController.getMongoDB().getCollection("providers").deleteOne(providerToDelete);
    }

    public void deleteOneClient(String name, String surname) {
        Document clientToDelete = new Document();
        clientToDelete.append("name", name);
        clientToDelete.append("surname", surname);
        DBController.getMongoDB().getCollection("clients").deleteOne(clientToDelete);
    }

    public int deleteProviders(Vector<String> names, Vector<String> surnames) {
        for (int i=0; i<names.size(); ++i) {
            Document providerToDelete = new Document();
            providerToDelete.append("name", names.get(i));
            providerToDelete.append("surname", surnames.get(i));
            DBController.getMongoDB().getCollection("providers").deleteOne(providerToDelete);
        }
        return 0;
    }
}
