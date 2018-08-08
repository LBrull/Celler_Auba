package com.presentacio;

import com.model.Product;
import com.model.ServerResponse;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EditProductView extends JFrame{
    private ProductsViewController controller;

    private JLabel labelTitle;
    private JTextField codiTextField;
    private JTextField descTextField;
    private JTextField priceTextField;
    private JComboBox comboBox1;
    private JButton saveButton;
    private JPanel rootPanel;
    private String[] tipus = {"AM", "RA", "OL"};

    public EditProductView(String objectId, String desc, String type, String price) {
        super();
        setContentPane(rootPanel);
        labelTitle.setFont(new Font("Calibri", Font.PLAIN, 20));
        codiTextField.setText(objectId);
        descTextField.setText(desc);
        comboBox1.setSelectedItem(type);
        priceTextField.setText(price);

        saveButton.addActionListener(e -> {
            try {
                ServerResponse serverResponse = ProductsViewController.editProduct(objectId, descTextField.getText(), comboBox1.getSelectedItem().toString(), priceTextField.getText());
                if (serverResponse.getStatus() != 200) {
                    JOptionPane.showMessageDialog(null, serverResponse.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    int rowTable = controller.getProductsTable().convertRowIndexToModel(controller.getProductsView().getProductsTable().getSelectedRow());
                    Product newProduct = parseJSON(serverResponse);
                    controller.repaintProductsTableWhenEdit(rowTable, newProduct.getObjectId(), newProduct.getDescription(), newProduct.getType(), newProduct.getPrice());
                    dispose();
                }
            } catch (IOException | JSONException e1) {
                e1.printStackTrace();
            }
        });

        pack();
        setVisible(true);

    }

    private Product parseJSON(ServerResponse res) throws JSONException {
        JSONObject jsonObject = new JSONObject(res.getMessage());
        JSONObject jsonProduct = new JSONObject(jsonObject.getString("product"));
        Product newProd = new Product();
        newProd.setObjectId(jsonProduct.getString("_id"));
        newProd.setDescription(jsonProduct.getString("description"));
        newProd.setType(jsonProduct.getString("type"));
        newProd.setPrice(jsonProduct.getString("price"));
        return newProd;
    }

    private void createUIComponents() {
        controller = ProductsViewController.getInstance();
        comboBox1 = new JComboBox(tipus);
    }
}
