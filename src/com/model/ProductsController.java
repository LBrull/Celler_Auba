package com.model;

import com.persistencia.DBController;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class ProductsController {
    private static ProductsController instance = null;
    private static DBController dbController;

    private ArrayList<Provider> products = null;

    private ProductsController() {
        dbController = DBController.getInstance();
    }

    public static ProductsController getInstance() {
        if (instance == null) instance = new ProductsController();
        return instance;
    }

    public ServerResponse saveNewProduct(Product product) throws IOException, JSONException {
        return dbController.saveNewProduct(product);
    }

    public ServerResponse getProducts() throws IOException, JSONException {
        return dbController.getDBProductsController().getProducts();
    }

    public boolean usedCode(String text) {
        return dbController.usedCode(text);
    }

    public ServerResponse deleteOneProduct(String ObjectId) throws IOException {
        return dbController.deleteOneProduct(ObjectId);
    }
}
