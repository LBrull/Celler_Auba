package com.presentacio;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame{

    private MenuController menuController = MenuController.getInstance();

    private JButton clientsProvidersButton;
    private JButton compresVendesButton;
    private JButton productsButton;
    private JButton temporadesButton;
    private JPanel rootPanel;
    private JLabel labelTitle;
    private JLabel activeTempAmetllaLabel;
    private JLabel activeTempRaimLabel;
    private JLabel activeTempOlivaLabel;


    public Menu () {
        super();
        setContentPane(rootPanel);
        labelTitle.setFont(new Font("Calibri", Font.PLAIN, 20));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        compresVendesButton.setEnabled(false);

        //TODO
        temporadesButton.addActionListener(e -> {
            //temporadesController.showTemporadesView();
        });

        clientsProvidersButton.addActionListener(e -> {
            menuController.showContactsView();
        });

        compresVendesButton.addActionListener(e -> {
            menuController.showTransactionsView();
        });

        productsButton.addActionListener(e -> {
            menuController.showProductesView();
        });

        if(!activeTempAmetllaLabel.getText().equals(("No hi ha cap temporada activa")) || !activeTempRaimLabel.getText().equals("No hi ha cap temporada activa") || !activeTempOlivaLabel.getText().equals("No hi ha cap temporada activa")) {
            compresVendesButton.setEnabled(true);
        }

        pack();
        setSize(700, 600);
        setVisible(true);
    }

}
