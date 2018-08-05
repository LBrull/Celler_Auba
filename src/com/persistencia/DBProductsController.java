package com.persistencia;

import com.model.Product;
import com.model.ServerResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class DBProductsController {

    public ServerResponse saveNewProduct(Product product) throws IOException, JSONException {
        String url = DBController.getDatabaseUrl() +"/api/product";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);

        post.setHeader("Content-Type", "application/json");
        String token = "Bearer "+ Preferences.userRoot().get("token", null);
        post.setHeader("authorization", token);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", product.getCode());
        jsonObject.put("description", product.getDescription());
        jsonObject.put("type", product.getType());
        jsonObject.put("price", product.getPrice());
        String json = jsonObject.toString(1);

        StringEntity entity = new StringEntity(json);
        post.setEntity(entity);

        CloseableHttpResponse response = client.execute(post);
        String responseString = new BasicResponseHandler().handleResponse(response);
        if (response.getStatusLine().getStatusCode() == 200) {
            System.out.println(responseString);
            client.close();
            ServerResponse res = new ServerResponse();
            res.setStatus(200);
            res.setMessage(responseString);
            return res;
        }
        else if (response.getStatusLine().getStatusCode() == 500) {
            client.close();
            ServerResponse res = new ServerResponse();
            res.setStatus(500);
            res.setMessage(responseString);
            return res;
        }
        else {
            client.close();
            ServerResponse res = new ServerResponse();
            res.setStatus(500);
            res.setMessage(responseString);
            return res;
        }
    }

    public ArrayList<Product> getProducts() throws IOException, JSONException {
        ArrayList<Product> list = new ArrayList<>();

        String url = DBController.getDatabaseUrl() +"/api/products";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);

        get.setHeader("Content-Type", "application/json");
        String token = "Bearer "+ Preferences.userRoot().get("token", null);
        get.setHeader("authorization", token);
        CloseableHttpResponse response = client.execute(get);
        System.out.println("response: " + response);

        if (response.getStatusLine().getStatusCode() == 200) {
            String responseString = new BasicResponseHandler().handleResponse(response);
            JSONArray products = new JSONArray(responseString);
            for (int i = 0; i < products.length(); ++i) {
                Product p = new Product();
                JSONObject jsonClient = products.getJSONObject(i);
                p.setCode(jsonClient.getString("code"));
                p.setDescription(jsonClient.getString("description"));
                p.setType(jsonClient.getString("type"));
                p.setPrice(jsonClient.getString("price"));

                list.add(p);
            }
            client.close();
            return list;
        }
        else {
            client.close();
            return list;
        }
    }

    public boolean usedCode(String text) {
        ArrayList<Product> list = new ArrayList<>();
        List<Document> products = DBController.getMongoDB().getCollection("products").find().into(new ArrayList<>());
        int i=0;
        boolean trobat = false;
        while(!trobat && i<products.size()) {
            if (products.get(i).getString("code").equals(text)) trobat = true;
            ++i;
        }
        return trobat;
    }

    public void deleteOneProduct(String code) {

    }
}
