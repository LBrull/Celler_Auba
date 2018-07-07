package com.persistencia;

import com.model.Product;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class DBProductsController {

    public void saveNewProduct(Product product) {
        MongoCollection<Document> collection = DBController.getMongoDB().getCollection("products");
        Document newProduct = new Document();
        newProduct.append("code", product.getCode());
        newProduct.append("description", product.getDescription());
        newProduct.append("price", product.getPrice());

        collection.insertOne(newProduct);
    }

    public int getProductsCount() {
        return (int) DBController.getMongoDB().getCollection("products").count();
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> list = new ArrayList<>();
        List<Document> documents = DBController.getMongoDB().getCollection("products").find().into(new ArrayList<>());

        for(Document document : documents){
            Product product = new Product();

            product.setCode(document.getString("code"));
            product.setDescription(document.getString("description"));
            double price = Double.parseDouble(document.getString("price"));
            product.setPrice(price);

            list.add(product);
        }
        return list;
    }
}
