package com.presentacio;

import com.model.Product;
import com.model.ServerResponse;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class NewProductView extends JFrame{

    private static ProductsViewController controller = ProductsViewController.getInstance();

    private JLabel labelTitle;
    private JTextField descripcioTextField;
    private JTextField preuTextField;
    private JButton saveButton;
    private JPanel rootPanel;
    private JComboBox typeComboBox;
    private JLabel productLabel;
    private JLabel priceLabel;
    private Object[] tipus = {"AM", "RA", "OL"};
    private boolean correctProduct = false;
    private boolean correctPrice = false;

    NewProductView() {
        super();
        setTitle("Celler Aubà");
        setIconImage(new ImageIcon(getClass().getResource("/icons/icono-olivo.png")).getImage());
        setContentPane(rootPanel);
        labelTitle.setFont(new Font("Calibri", Font.PLAIN, 20));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        productLabel.setForeground(new Color(200,30,60));
        priceLabel.setForeground(new Color(200,30,60));

        descripcioTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(descripcioTextField.getText().length()<=0 || descripcioTextField.getText().equals("")) {
                    productLabel.setForeground(new Color(200,30,60));
                    correctProduct = false;
                }
                else {
                    productLabel.setForeground(new Color(0,0,0));
                    correctProduct = true;
                }
            }
        });

        preuTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(preuTextField.getText().length()<=0 || preuTextField.getText().equals("") || preuTextField.getText().contains(",")) {
                    priceLabel.setForeground(new Color(200,30,60));
                    correctPrice = false;
                }
                else {
                    priceLabel.setForeground(new Color(0,0,0));
                    correctPrice = true;
                }
            }
        });

        saveButton.addActionListener(e -> {
            if (correctPrice && correctProduct) {
                try {
                    ServerResponse res = controller.saveNewProduct(descripcioTextField.getText(), this.getProductType(), preuTextField.getText());
                    Product newProduct = parseJSON(res);
                    if (res.getStatus() == 200) {
                        controller.repaintProductsTableWhenAddition(newProduct);
                        dispose();
                    }

                    else if (res.getStatus() != 200) {
                        if (res.getStatus() == 403) {
                            //no token
                            new Login();
                        }
                        if (res.getStatus() == 401) {
                            //expired token
                            new Login();
                        }
                        if (res.getStatus() == 500 && res.getMessage().contains("Invalid")) {
                            //invalid token
                            new Login();
                        }
                        if (res.getStatus() == 500 && res.getMessage().contains("not save")) {
                            //could not save the product
                            JOptionPane.showMessageDialog(null, res.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (IOException | JSONException e1) {
                    e1.printStackTrace();
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Completeu correctament tots els camps", "", JOptionPane.INFORMATION_MESSAGE);
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

    public JTextField getPreuTextField() {
        return preuTextField;
    }

    public JTextField getDescripcioTextField() {
        return descripcioTextField;
    }

    public String getProductType() {
        return typeComboBox.getSelectedItem().toString();
    }

    private void createUIComponents() {
        typeComboBox = new JComboBox(tipus);
    }
}
