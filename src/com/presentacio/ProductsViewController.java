package com.presentacio;

import com.model.Product;
import com.model.ProductsController;

import java.util.ArrayList;
import java.util.Vector;

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

    public boolean usedCode(String text) {
       return productsController.usedCode(text);
    }

    public void saveNewProduct(String code, String desc, String price) {
        double priceD = Double.parseDouble(price);
        Product product = new Product(code, desc, priceD);
        productsController.saveNewProduct(product);

    }

    public void repaintProductsTable() {
        Vector<String> data = new Vector<>();
        data.add(newProductView.getCodiTextField().getText());
        data.add(newProductView.getDescripcioTextField().getText());
        data.add(newProductView.getPreuTextField().getText());

        productsView.getProductsTableModel().addRow(data);
    }

    public void deleteOneProduct(String code) {
        productsController.deleteOneProduct(code);
    }

    public void repaintProductsTableWhenDeletion(int rowTable) {
        productsView.getProductsTableModel().removeRow(rowTable);
    }
}
