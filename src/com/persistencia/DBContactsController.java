package com.persistencia;

import com.model.Client;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class DBContactsController {

    private static DBContactsController dbContactsController = null;
    private MongoDatabase mongoDB;

    private DBContactsController() {
    }

    public static DBContactsController getInstance() {
        if (dbContactsController == null) {
            dbContactsController = new DBContactsController();
            System.out.println("Success: DBContactsController created");
        }
        return dbContactsController;
    }

    public void setMongoDatabase (MongoDatabase mongoDB) {
        this.mongoDB = mongoDB;
    }

    public List<Client> getClients () {
        List<Client> list = new ArrayList<>();
        List<Document> documents = mongoDB.getCollection("clients").find().into(new ArrayList<>());

        for(Document document : documents){
            Client client = new Client();

            client.setName(document.getString("name"));
            client.setSurname(document.getString("surname"));
            client.setTelephone(document.getString("telephone"));
            client.setAddress(document.getString("address"));
            list.add(client);

            System.out.println(document);
        }
        return list;
    }

}
