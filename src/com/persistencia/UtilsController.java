package com.persistencia;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;

public class UtilsController {

    private static UtilsController instance = null;

    private UtilsController() {}


    public static UtilsController getInstance() {
        if (instance == null) {
            instance = new UtilsController();
        }
        return instance;
    }


    public String getNextAmetllaAlbaraNumber() {
        MongoCollection<Document> collection = DBController.getMongoDB().getCollection("ametllaNumbers");
        if (collection.count() == 0) {
            return "1";
        }
        else {
            ArrayList<Document> array = collection.find().into(new ArrayList<>());
            String lastNumber = array.get(0).getString("number");
            int aux = Integer.getInteger(lastNumber);
            return String.valueOf(aux + 1);
        }
    }

    public String getNextRaimAlbaraNumber() {
        MongoCollection<Document> collection = DBController.getMongoDB().getCollection("raimNumbers");
        if (collection.count() == 0) {
            return "1";
        }
        else {
            ArrayList<Document> array = collection.find().into(new ArrayList<>());
            String lastNumber = array.get(0).getString("number");
            int aux = Integer.getInteger(lastNumber);
            return String.valueOf(aux + 1);
        }
    }

    public String getNextOlivaAlbaraNumber() {
        MongoCollection<Document> collection = DBController.getMongoDB().getCollection("olivaNumbers");
        if (collection.count() == 0) {
            return "1";
        }
        else {
            ArrayList<Document> array = collection.find().into(new ArrayList<>());
            String lastNumber = array.get(0).getString("number");
            int aux = Integer.getInteger(lastNumber);
            return String.valueOf(aux + 1);
        }
    }
}
