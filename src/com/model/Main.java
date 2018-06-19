package com.model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.presentation.ManagePeople;
import org.bson.Document;

public class Main {

    public static void main(String[] args) {

        MongoClient mongo = crearConexion();
        if (mongo != null) {
            System.out.println("Lista de bases de datos: ");
            MongoDatabase mongoDB = mongo.getDatabase("celler_aubarcaDB");

            MongoCollection<Document> collection = mongoDB.getCollection("clients");

            Document client1 = new Document();
            client1.append("name", "Domingo");
            client1.append("surname", "Brull");
            client1.append("telephone", 650575112);
            client1.append("address", "Av PIUS XII, 33");
            collection.insertOne(client1);
            System.out.println("Inserted one client successfully");

        } else {
            System.out.println("Error: Conexión no establecida");
        }
    }

    private static MongoClient crearConexion(){
        MongoClient mongo = null;
        mongo = new MongoClient("localhost", 27017);
        return mongo;
    }

    private static void printDatabase(MongoClient mongo) {
        MongoDatabase db = mongo.getDatabase("celler_aubarcaDB");
        MongoCollection<Document> collection = db.getCollection("celler_aubarcaDB");
        for(int i=0; i<collection.count(); ++i) {
        }
    }

}
