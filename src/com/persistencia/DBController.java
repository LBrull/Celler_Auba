package com.persistencia;

import com.model.Client;
import com.model.Product;
import com.model.Provider;
import com.model.ServerResponse;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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

    public ServerResponse saveNewClient(Client client) throws IOException, JSONException {
        return dbContactsController.saveNewClient(client);
    }

    public ServerResponse saveNewProvider(Provider provider) throws IOException, JSONException {
        return dbContactsController.saveNewProvider(provider);
    }

    public boolean clientExists(String name, String surname) throws IOException, JSONException {
        return dbContactsController.clientExists(name, surname);
    }

    public boolean providerExists(String name, String surname) throws IOException, JSONException {
        return dbContactsController.providerExists(name, surname);
    }

    public ServerResponse deleteOneProvider(String objectId) throws IOException {
        return dbContactsController.deleteOneProvider(objectId);
    }

    public ServerResponse deleteOneClient(String objectId) throws IOException {
        return dbContactsController.deleteOneClient(objectId);
    }

    public int deleteProviders(Vector<String> names, Vector<String> surnames) {
        return dbContactsController.deleteProviders(names, surnames);
    }

    public ServerResponse saveNewProduct(Product product) throws IOException, JSONException {
        return dbProductsController.saveNewProduct(product);
    }

    public DBProductsController getDBProductsController() {
        return dbProductsController;
    }

    public ServerResponse deleteOneProduct(String ObjectId) throws IOException {
        return dbProductsController.deleteOneProduct(ObjectId);
    }

    public ServerResponse login(String username, String password) throws Exception{

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

        ////////////////////////////////////////
        HttpResponse response = client.execute( post );
        InputStream body = response.getEntity().getContent();
        /////////////////////////////////

        ServerResponse res = new ServerResponse();
        if (response.getStatusLine().getStatusCode() == 404) {

            res.setStatus(404);
            res.setMessage(readStream(body));
            client.close();
            return res;
        }

        if (response.getStatusLine().getStatusCode() == 400) {
            res.setStatus(400);
            res.setMessage(readStream(body));
            client.close();
            return res;
        }

        if (response.getStatusLine().getStatusCode() == 200) {
            String responseString = new BasicResponseHandler().handleResponse(response);
            JSONObject jsonResponse = new JSONObject(responseString);
            System.out.println(jsonResponse.toString(1));
            res.setStatus(200);
            res.setMessage(responseString);
            res.setToken(jsonResponse.getString("token"));
            client.close();
            return res;
        }
        else {
            res.setStatus(500);
            res.setMessage(readStream(body));
            client.close();
            return res;
        }
    }

    private String readStream(InputStream body) throws IOException {
        String result = IOUtils.toString(body, StandardCharsets.UTF_8);
        System.out.println(result);
        return result;
    }
}
