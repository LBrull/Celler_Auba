package com.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NewProductView extends JFrame{

    private static ProductsViewController controller = ProductsViewController.getInstance();

    private JLabel labelTitle;
    private JTextField codiTextField;
    private JTextField descripcioTextField;
    private JTextField preuTextField;
    private JLabel emptyCode;
    private JLabel emptyDescription;
    private JLabel emptyPrice;
    private JButton saveButton;
    private JPanel rootPanel;

    NewProductView() {
        super();
        setContentPane(rootPanel);
        labelTitle.setFont(new Font("Calibri", Font.PLAIN, 20));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        emptyPrice.setFont(new Font("Calibri", Font.PLAIN, 20));
        emptyDescription.setFont(new Font("Calibri", Font.PLAIN, 20));
        emptyCode.setFont(new Font("Calibri", Font.PLAIN, 20));

        codiTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(codiTextField.getText().length()<=0 || codiTextField.getText().equals("")) {
                    emptyCode.setVisible(true);
                }
                else {
                    emptyCode.setVisible(false);
                }
            }
        });

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
                    emptyDescription.setVisible(true);
                }
                else {
                    emptyDescription.setVisible(false);
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
                    emptyPrice.setVisible(true);
                }
                else {
                    emptyPrice.setVisible(false);
                }
            }
        });

        saveButton.addActionListener(e -> {
            if (!emptyCode.isVisible() && !emptyDescription.isVisible() && !emptyPrice.isVisible()) {
                if(controller.usedCode(codiTextField.getText())) {
                    JOptionPane.showMessageDialog(null, "Aquest codi ja està en ús", "", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    controller.saveNewProduct(codiTextField.getText(), descripcioTextField.getText(), preuTextField.getText());
                    controller.repaintProductsTable();
                    dispose();
                }

            }
            else {
                JOptionPane.showMessageDialog(null, "Completeu correctament tots els camps", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        pack();
        setVisible(true);
    }

    public JTextField getPreuTextField() {
        return preuTextField;
    }

    public JTextField getDescripcioTextField() {
        return descripcioTextField;
    }

    public JTextField getCodiTextField() {
        return codiTextField;
    }

}
