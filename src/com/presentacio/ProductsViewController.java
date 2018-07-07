package com.presentacio;

import com.model.Product;
import com.model.ProductsController;

import java.util.ArrayList;

public class ProductsViewController {
    private static ProductsViewController instance = null;
    private static ProductsController productsController = ProductsController.getInstance();

    // TODO: vistes associades al controlador
    private ProductsView productsView;
    private NewProductView newProductView;

    private ProductsViewController() {

    }

    public static ProductsViewController getInstance() {
        if (instance == null) {
            instance = new ProductsViewController();
        }
        return instance;
    }

    public void showProductsView() {
        productsView = new ProductsView();
    }

    public int getProductsCount() {
        return productsController.getProductsCount();
    }

    public ArrayList<Product> getProducts() {
        return productsController.getProducts();
    }

    public void showNewProductView() {
        newProductView = new NewProductView();
    }
}
