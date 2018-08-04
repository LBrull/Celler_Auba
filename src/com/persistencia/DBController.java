package com.persistencia;

import com.model.Client;
import com.model.Product;
import com.model.Provider;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Vector;

// MAIN DB controller
public class DBController {

    private static String DatabaseUrl = "https://cellerauba.herokuapp.com";

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
//        MongoClient mongo = getMongoClient();
//        mongoDB = mongo.getDatabase("celler_aubarcaDB");
//        System.out.println("Success: Conexión estabecida");
    }

    private MongoClient getMongoClient(){
         return new MongoClient("localhost", 27017);
    }

    // Getters for all the DB controllers TODO: add all the getters for all the controllers
    public DBContactsController getDBContactsController() {
        return dbContactsController;
    }

    public static String getDatabaseUrl() {
        return DatabaseUrl;
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

    public boolean clientExists(String name, String surname) throws IOException, JSONException {
        return dbContactsController.clientExists(name, surname);
    }

    public boolean providerExists(String name, String surname) throws IOException, JSONException {
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

    public void saveNewProduct(Product product) throws IOException, JSONException {
        dbProductsController.saveNewProduct(product);
    }

    public DBProductsController getDBProductsController() {
        return dbProductsController;
    }

    public boolean usedCode(String text) {
        return dbProductsController.usedCode(text);
    }

    public void deleteOneProduct(String code) {
        dbProductsController.deleteOneProduct(code);
    }

    public String login(String username, String password) throws Exception{

        String url = DatabaseUrl + "/api/signin";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        String json = jsonObject.toString(1);

        StringEntity entity = new StringEntity(json);
        post.setEntity(entity);
        post.setHeader("Content-Type", "application/json");
        CloseableHttpResponse response = client.execute(post);
        String responseString = new BasicResponseHandler().handleResponse(response);
        client.close();

        JSONObject jsonResponse = new JSONObject(responseString);

        if (response.getStatusLine().getStatusCode() == 200) {
            return jsonResponse.getString("token");
        }
        else return null;
    }
}
