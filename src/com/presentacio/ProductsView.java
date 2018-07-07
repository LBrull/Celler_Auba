package com.presentacio;

import com.model.Product;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class ProductsView extends JFrame{

    private ProductsViewController controller;

    private JPanel rootPanel;
    private JLabel labelWindowTitle;
    private JTable table;
    private JButton nouProducteButton;
    private JButton EditButton;
    private JButton DeleteButton;
    private ProductsTableModel productsTableModel;
    private TableRowSorter<ProductsTableModel> sorter;

    ProductsView() {
        super();
        setContentPane(rootPanel);
        labelWindowTitle.setFont(new Font("Calibri", Font.PLAIN, 20));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        EditButton.setVisible(false);
        DeleteButton.setVisible(false);

        pack();
        setVisible(true);
    }

    private void loadProducts() {

        productsTableModel = new ProductsTableModel();
        Object columnNames[] = {"Codi", "Descripció", "Preu per unitat"};
        productsTableModel.setColumnIdentifiers(columnNames);
        table = new JTable(productsTableModel);
        sorter = new TableRowSorter<>(productsTableModel);
        table.setRowSorter(sorter);
        sorter.setRowFilter(null);

        //load products data
        int numberOfProducts = controller.getProductsCount();
        ArrayList<Product> products = controller.getProducts();
        Object productsData[][] = new Object[numberOfProducts][3];
        for(int i=0; i<numberOfProducts; ++i) {
            productsData[i][0] = products.get(i).getCode();
            productsData[i][1] = products.get(i).getDescription();
            productsData[i][2] = products.get(i).getPrice();
        }

        for (int j=0; j<productsData.length; ++j) {
            productsTableModel.addRow(productsData[j]);
        }

        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(true);
        table.setFocusable(false);
        table.getSelectionModel().addListSelectionListener(e -> SwingUtilities.invokeLater(
                () -> {
                    if(!EditButton.isVisible()) {
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

    private void createUIComponents() {
        controller = ProductsViewController.getInstance();
        loadProducts();
    }
}
