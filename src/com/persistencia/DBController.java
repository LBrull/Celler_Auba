package com.persistencia;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

// MAIN DB controller
public class DBController {

    private static DBController dbController = null;
    private static MongoDatabase mongoDB = null;

    // All the controllers of the DB associated to this are here
    private static DBContactsController dbContactsController = null;

    private DBController() {
    }

    public static DBController getInstance() {
        if (dbController == null) {
            dbController = new DBController();
            System.out.println("Success: conexión establecida");
        }
        return dbController;
    }

    public void connect() {
        MongoClient mongo = crearConexion();
        if (mongo != null) {
            mongoDB = mongo.getDatabase("celler_aubarcaDB");
        }
        else {
            System.out.println("Error: Conexión no establecida");
        }
    }

//            MongoCollection<Document> collection = mongoDB.getCollection("clients");
//            Document client1 = new Document();
//            client1.append("name", "Domingo");
//            client1.append("surname", "Brull");
//            client1.append("telephone", 650575112);
//            client1.append("address", "Av PIUS XII, 33");
//            collection.insertOne(client1);

    private MongoClient crearConexion(){
        MongoClient mongo = null;
        mongo = new MongoClient("localhost", 27017);
        return mongo;
    }

    // Getters for all the DB controllers
    public static DBContactsController getDBContactsController() {
        dbContactsController = DBContactsController.getInstance();
        dbContactsController.setMongoDatabase(mongoDB);
        return dbContactsController;
    }
}
