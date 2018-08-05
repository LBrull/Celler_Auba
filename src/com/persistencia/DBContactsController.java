package com.persistencia;

import com.model.Client;
import com.model.Provider;
import com.mongodb.client.MongoCollection;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.prefs.Preferences;

public class DBContactsController {

    private String DBUrl = "https://cellerauba.herokuapp.com";

    DBContactsController() {
    }

    public ArrayList<Client> getClients () throws IOException, JSONException {
        ArrayList<Client> list = new ArrayList<>();

        String url =DBUrl + "/api/clients";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);

        get.setHeader("Content-Type", "application/json");
        String token = "Bearer "+ Preferences.userRoot().get("token", null);
        get.setHeader("authorization", token);
        CloseableHttpResponse response = client.execute(get);
        String responseString = new BasicResponseHandler().handleResponse(response);
        System.out.println(responseString);
        client.close();

        if (response.getStatusLine().getStatusCode() == 200) {
            JSONArray clients = new JSONArray(responseString);
            for (int i = 0; i < clients.length(); ++i) {
                Client c = new Client();
                JSONObject jsonClient = clients.getJSONObject(i);
                c.setName(jsonClient.getString("name"));
                c.setSurname(jsonClient.getString("surname"));
                c.setDni_nif(jsonClient.getString("dni_nif"));
                c.setAccountNumber(jsonClient.getString("accountNumber"));
                c.setTelephone(jsonClient.getString("telephone"));
                c.setEmail(jsonClient.getString("email"));
                c.setCP(jsonClient.getString("cp"));
                c.setTown(jsonClient.getString("town"));
                c.setAddress(jsonClient.getString("address"));
                list.add(c);
            }
        }
        return list;
    }

    public ArrayList<Provider> getProviders() throws IOException, JSONException {
        ArrayList<Provider> list = new ArrayList<>();

        String url = "https://cellerauba.herokuapp.com/api/providers";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);

        get.setHeader("Content-Type", "application/json");
        String token = "Bearer "+ Preferences.userRoot().get("token", null);
        get.setHeader("authorization", token);
        CloseableHttpResponse response = client.execute(get);
        String responseString = new BasicResponseHandler().handleResponse(response);
        client.close();

        if (response.getStatusLine().getStatusCode() == 200) {
            JSONArray clients = new JSONArray(responseString);
            for (int i = 0; i < clients.length(); ++i) {
                Provider c = new Provider();
                JSONObject jsonClient = clients.getJSONObject(i);
                c.setName(jsonClient.getString("name"));
                c.setSurname(jsonClient.getString("surname"));
                c.setDni_nif(jsonClient.getString("dni_nif"));
                c.setAccountNumber(jsonClient.getString("accountNumber"));
                c.setTelephone(jsonClient.getString("telephone"));
                c.setEmail(jsonClient.getString("email"));
                c.setCP(jsonClient.getString("cp"));
                c.setTown(jsonClient.getString("town"));
                c.setAddress(jsonClient.getString("address"));
                list.add(c);
            }
        }
        return list;
    }

    public void saveNewClient(Client client) {
        MongoCollection<Document> collection = DBController.getMongoDB().getCollection("clients");
        Document newClient = new Document();
        newClient.append("name", client.getName());
        newClient.append("surname", client.getSurname());

        newClient.append("telephone", client.getTelephone());
        newClient.append("email", client.getEmail());
        newClient.append("cp", client.getCp());
        newClient.append("town", client.getTown());
        newClient.append("address", client.getAddress());
        newClient.append("dni_nif", client.getDni_nif());
        newClient.append("accountNumber", client.getAccountNumber());
        collection.insertOne(newClient);
    }

    public void saveNewProvider(Provider provider) {
        MongoCollection<Document> collection = DBController.getMongoDB().getCollection("providers");
        Document newProvider = new Document();
        newProvider.append("name", provider.getName());
        newProvider.append("surname", provider.getSurname());

        newProvider.append("telephone", provider.getTelephone());
        newProvider.append("email", provider.getEmail());
        newProvider.append("cp", provider.getCp());
        newProvider.append("town", provider.getTown());
        newProvider.append("address", provider.getAddress());
        newProvider.append("dni_nif", provider.getDni_nif());
        newProvider.append("accountNumber", provider.getAccountNumber());
        collection.insertOne(newProvider);
    }

    public boolean clientExists(String name, String surname) throws IOException, JSONException {
        List<Client> clients = getClients();
        boolean found = false;
        int i=0;
        while (!found && i<clients.size()) {
            if (clients.get(i).getName().equals(name) && clients.get(i).getSurname().equals(surname)) found = true;
            ++i;
        }
        return found;
    }

    public boolean providerExists(String name, String surname) throws IOException, JSONException {
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
