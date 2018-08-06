package com.presentacio;

import com.model.Product;
import com.model.ServerResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ProductsView extends JFrame{

    private ProductsViewController controller;

    private JPanel rootPanel;
    private JLabel labelWindowTitle;
    private JTable table;
    private JButton nouProducteButton;
    private JButton EditButton;
    private JButton DeleteButton;
    private JTextField filterTextField;
    private ProductsTableModel productsTableModel;
    private TableRowSorter<ProductsTableModel> sorter;

    ProductsView() {
        super();
        setContentPane(rootPanel);
        labelWindowTitle.setFont(new Font("Calibri", Font.PLAIN, 20));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        EditButton.setVisible(false);
        DeleteButton.setVisible(false);

        loadListeners();

        pack();
        setVisible(true);
    }

    private void loadListeners() {

        filterTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                newFilter();
            }
            public void insertUpdate(DocumentEvent e) {
                newFilter();
            }
            public void removeUpdate(DocumentEvent e) {
                newFilter();
            }
        });

        nouProducteButton.addActionListener(e -> {
            controller.showNewProductView();
        });

        DeleteButton.addActionListener(e -> {
            if (table.getSelectedRowCount()>=1) {
                int[] rows = table.getSelectedRows();
                if (rows.length == 1) {
                    int rowView = rows[0];
                    int rowTable = table.convertRowIndexToModel(rowView);
                    int dialogButton = JOptionPane.showConfirmDialog (null, "Segur que voleu eliminar aquest producte?","WARNING", JOptionPane.YES_NO_OPTION);
                    Product prod = new Product();
                    prod.setDescription(productsTableModel.getValueAt(rowTable, 0).toString());
                    prod.setType(productsTableModel.getValueAt(rowTable, 1).toString());
                    prod.setPrice(productsTableModel.getValueAt(rowTable, 2).toString());
                    if (dialogButton == 0) {
                        controller.deleteOneProduct(prod);
                        controller.repaintProductsTableWhenDeletion(rowTable);
                    }
                }
                else {
                    int dialogButton = JOptionPane.showConfirmDialog (null, "Segur que voleu eliminar "+rows.length+" productes?","WARNING", JOptionPane.YES_NO_OPTION);
                    for (int rowView : rows) {
                        int rowTable = table.convertRowIndexToModel(rowView);
                        String code = productsTableModel.getValueAt(rowTable, 0).toString();
                        if (dialogButton == 0) {
                            System.out.println("code "+code +" deleted");
                            //controller.deleteOneProduct(code);
                        }
                    }
                    for ( int i = rows.length -1 ;  i >= 0; i-- ) {
                        if ( productsTableModel.getValueAt(i,0) != null ) {
                            controller.repaintProductsTableWhenDeletion(table.convertRowIndexToModel(rows[i]));
                        }
                    }
                }
            }

        });
    }

    private void loadProducts() throws IOException, JSONException {

        productsTableModel = new ProductsTableModel();
        Object columnNames[] = {"Codi", "Producte", "Tipus", "Preu per unitat"};
        productsTableModel.setColumnIdentifiers(columnNames);
        table = new JTable(productsTableModel);
        sorter = new TableRowSorter<>(productsTableModel);
        table.setRowSorter(sorter);
        sorter.setRowFilter(null);

        //load products data
        ServerResponse serverResponse = controller.getProducts();
        if (serverResponse.getStatus() == 200) {
            ArrayList<Product> products = parseJSON(serverResponse.getMessage());
            Object productsData[][] = new Object[products.size()][4];
            for (int i = 0; i < products.size(); ++i) {
                productsData[i][0] = products.get(i).getObjectId();
                productsData[i][1] = products.get(i).getDescription();
                productsData[i][2] = products.get(i).getType();
                productsData[i][3] = products.get(i).getPrice();
            }

            for (Object[] aProductsData : productsData) {
                productsTableModel.addRow(aProductsData);
            }

            table.setCellSelectionEnabled(false);
            table.setRowSelectionAllowed(true);
            table.setFocusable(false);
            table.getSelectionModel().addListSelectionListener(e -> SwingUtilities.invokeLater(
                    () -> {
                        if (!EditButton.isVisible()) {
                            EditButton.setVisible(true);
                            DeleteButton.setVisible(true);
                        }
                        if (table.getSelectionModel().isSelectionEmpty()) {
                            EditButton.setVisible(false);
                            DeleteButton.setVisible(false);
                        }
                    }
            ));
        }
        else if (serverResponse.getStatus() != 500) {
            new Login();
            this.dispose();
        }
    }

    private ArrayList<Product> parseJSON(String products) throws JSONException {
        ArrayList<Product> list = new ArrayList<>();
        JSONArray arrayProds = new JSONArray(products);
        for (int i = 0; i < arrayProds.length(); ++i) {
            Product p = new Product();
            JSONObject jsonClient = arrayProds.getJSONObject(i);
            p.setObjectId(jsonClient.getString("_id"));
            p.setDescription(jsonClient.getString("description"));
            p.setType(jsonClient.getString("type"));
            p.setPrice(jsonClient.getString("price"));
            list.add(p);
        }
        return list;

    }

    private void createUIComponents() throws IOException, JSONException {
        controller = ProductsViewController.getInstance();
        loadProducts();
    }

    public ProductsTableModel getProductsTableModel() {
        return productsTableModel;
    }

    private void newFilter() {
        RowFilter<ProductsTableModel, Object> rf;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter("(?i)" + filterTextField.getText(), 0, 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
