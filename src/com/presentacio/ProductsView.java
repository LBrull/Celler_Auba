package com.presentacio;

import com.model.Product;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
                    String code = productsTableModel.getValueAt(rowTable, 0).toString();
                    if (dialogButton == 0) {
                        controller.deleteOneProduct(code);
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
                            controller.deleteOneProduct(code);
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
