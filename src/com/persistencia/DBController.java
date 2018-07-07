package com.persistencia;

import com.model.Client;
import com.model.Product;
import com.model.Provider;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.util.Vector;

// MAIN DB controller
public class DBController {

    private static DBController dbController = null;
    private static MongoDatabase mongoDB = null;
    //TODO: add all the DB controllers
    private DBContactsController dbContactsController;
    private DBProductsController dbProductsController;

    private DBController() {
        dbProductsController = new DBProductsController();
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

    public void disconnect() {
        getMongoClient().close();
    }

    public boolean clientExists(String name, String surname) {
        return dbContactsController.clientExists(name, surname);
    }

    public boolean providerExists(String name, String surname) {
        return dbContactsController.providerExists(name, surname);
    }

    public void deleteOneProvider(String name, String surname) {
        dbContactsController.deleteOneProvider(name, surname);
    }

    public void deleteOneClient(String name, String surname) {
        dbContactsController.deleteOneClient(name, surname);
    }

    public int deleteProviders(Vector<String> names, Vector<String> surnames) {
        return dbContactsController.deleteProviders(names, surnames);
    }

    public void saveNewProduct(Product product) {
        dbProductsController.saveNewProduct(product);
    }

    public DBProductsController getDBProductsController() {
        return dbProductsController;
    }
}
