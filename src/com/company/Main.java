package com.company;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Main {

    private static MongoClient mongo;
    private static Document client1;

    public static void main(String[] args) {
	System.out.println("Prueba conexión MongoDB");
        mongo = crearConexion();

        if (mongo != null) {
            System.out.println("Lista de bases de datos: ");
            MongoDatabase mongoDB = mongo.getDatabase("celler_aubarcaDB");
            MongoCollection<Document> collection = mongoDB.getCollection("clients");

            client1 = new Document();
            client1.put("name", "Sivaraman");
            client1.put("surname", "Tikitaka");
            client1.put("telephone", 618526311);
            client1.put("address", "C/Els Rosers, 23");
            collection.insertOne(client1);

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
