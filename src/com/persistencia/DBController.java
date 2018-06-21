package com.persistencia;

import com.model.Client;
import com.model.Provider;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

// MAIN DB controller
public class DBController {

    private static DBController dbController = null;
    private static MongoDatabase mongoDB = null;
    //TODO: add all the DB controllers
    private DBContactsController dbContactsController;

    private DBController() {
        dbContactsController = new DBContactsController();
    }

    public static MongoDatabase getMongoDB() {
        return mongoDB;
    }

    public static DBController getInstance() {
        if (dbController == null) {
            dbController = new DBController();
        }
        return dbController;
    }

    public void DBconnect() {
        MongoClient mongo = getMongoClient();
        mongoDB = mongo.getDatabase("celler_aubarcaDB");
        System.out.println("Success: Conexión estabecida");
    }

    private MongoClient getMongoClient(){
         return new MongoClient("localhost", 27017);
    }

    // Getters for all the DB controllers TODO: add all the getters for all the controllers
    public DBContactsController getDBContactsController() {
        return dbContactsController;
    }

    public void saveNewClient(Client client) {
        dbContactsController.saveNewClient(client);
    }

    public void saveNewProvider(Provider provider) {
        dbContactsController.saveNewProvider(provider);
    }
}
