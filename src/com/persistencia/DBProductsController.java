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
            double price = (document.getDouble("price"));
            product.setPrice(price);

            list.add(product);
        }
        return list;
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
        Document productToDelete = new Document();
        productToDelete.append("code", code);
        DBController.getMongoDB().getCollection("products").deleteOne(productToDelete);
    }
}
