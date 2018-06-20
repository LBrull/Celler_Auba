package com.model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.presentacio.PresentationController;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;

public class Runner {

    private static Color backgroundColor = new Color(60, 63, 65);
    private static Color textColor = new Color(187,187,187);
    private static Color colorAccent = new Color(231, 158, 109);

    public static void main(String[] args) {

        //Change L&F of application
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //Windows Look and feel
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        UIManager.put("Button.background", backgroundColor);

        UIManager.put("Panel.background", backgroundColor);
        UIManager.put("Label.foreground", textColor);
        UIManager.put("List.background", backgroundColor);

        //Change font of application
        setUIFont (new javax.swing.plaf.FontUIResource("Calibri", Font.PLAIN,14));
        PresentationController presentationController = PresentationController.getInstance();
        presentationController.run();

//        MongoClient mongo = crearConexion();
//        if (mongo != null) {
//            System.out.println("Lista de bases de datos: ");
//            MongoDatabase mongoDB = mongo.getDatabase("celler_aubarcaDB");
//
//            MongoCollection<Document> collection = mongoDB.getCollection("clients");
//
//            Document client1 = new Document();
//            client1.append("name", "Domingo");
//            client1.append("surname", "Brull");
//            client1.append("telephone", 650575112);
//            client1.append("address", "Av PIUS XII, 33");
//            collection.insertOne(client1);
//            System.out.println("Inserted one client successfully");
//
//        } else {
//            System.out.println("Error: Conexión no establecida");
//        }
    }

    private static void setUIFont (javax.swing.plaf.FontUIResource f){
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put (key, f);
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
