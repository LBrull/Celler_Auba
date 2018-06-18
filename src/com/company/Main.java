package com.company;

import com.mongodb.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	System.out.println("Prueba conexión MongoDB");
        MongoClient mongo = crearConexion();

        if (mongo != null) {
            System.out.println("Lista de bases de datos: ");

            //printDatabases(mongo);

        } else {
            System.out.println("Error: Conexión no establecida");
        }
    }

    private static MongoClient crearConexion(){
        MongoClient mongo = null;
        mongo = new MongoClient("localhost", 27017);
        return mongo;
    }

    private static void printDatabases(MongoClient mongo) {
        List dbs = mongo.getDatabaseNames();

        for (Object db : dbs) {
            System.out.println(" - " + db.toString());
        }
    }

}
