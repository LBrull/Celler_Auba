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

    public void saveNewProduct(Product product) throws IOException, JSONException {
        dbController.saveNewProduct(product);
    }

    public ArrayList<Product> getProducts() throws IOException, JSONException {
        return dbController.getDBProductsController().getProducts();
    }

    public boolean usedCode(String text) {
        return dbController.usedCode(text);
    }

    public void deleteOneProduct(String code) {
        dbController.deleteOneProduct(code);
    }
}
