package com.model;

import com.persistencia.DBController;

import java.util.ArrayList;

public class ProductsController {
    private static ProductsController instance = null;
    private static DBController dbController;

    private ProductsController() {
        dbController = DBController.getInstance();
    }

    public static ProductsController getInstance() {
        if (instance == null) instance = new ProductsController();
        return instance;
    }

    public void saveNewProduct(Product product) {
        dbController.saveNewProduct(product);
    }

    public int getProductsCount() {
        return dbController.getDBProductsController().getProductsCount();
    }

    public ArrayList<Product> getProducts() {
        return dbController.getDBProductsController().getProducts();
    }

    public boolean usedCode(String text) {
        return dbController.usedCode(text);
    }
}
