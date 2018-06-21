package com.persistencia;

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

//            MongoCollection<Document> collection = mongoDB.getCollection("clients");
//            Document client1 = new Document();
//            client1.append("name", "Domingo");
//            client1.append("surname", "Brull");
//            client1.append("telephone", 650575112);
//            client1.append("address", "Av PIUS XII, 33");
//            collection.insertOne(client1);

    private MongoClient getMongoClient(){
         return new MongoClient("localhost", 27017);
    }

    // Getters for all the DB controllers TODO: add all the getters for all the controllers
    public DBContactsController getDBContactsController() {
        return dbContactsController;
    }

}
