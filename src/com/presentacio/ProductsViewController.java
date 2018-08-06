package com.presentacio;

import com.model.Product;
import com.model.ProductsController;
import com.model.ServerResponse;
import org.json.JSONException;

import java.io.IOException;
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

    public ServerResponse getProducts() throws IOException, JSONException {
        return productsController.getProducts();
    }

    public void showNewProductView() {
        newProductView = new NewProductView();
    }

    public boolean usedCode(String text) {
       return productsController.usedCode(text);
    }

    public ServerResponse saveNewProduct(String desc, String type, String price) throws IOException, JSONException {
        Product product = new Product(desc, type, price);
        return productsController.saveNewProduct(product);

    }

    public void repaintProductsTable(Product newProduct) {
        Vector<String> data = new Vector<>();
        data.add(newProduct.getObjectId());
        data.add(newProduct.getDescription());
        data.add(newProduct.getType());
        data.add(newProduct.getPrice());

        productsView.getProductsTableModel().addRow(data);
    }

    public ServerResponse deleteOneProduct(String ObjectId) throws IOException {
        return productsController.deleteOneProduct(ObjectId);
    }

    public void repaintProductsTableWhenDeletion(int rowTable) {
        productsView.getProductsTableModel().removeRow(rowTable);
    }
}
