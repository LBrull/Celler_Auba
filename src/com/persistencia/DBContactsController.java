package com.persistencia;

import com.model.Client;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

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

    public int getClientsCount() {
        return (int) DBController.getMongoDB().getCollection("clients").count();
    }
}
