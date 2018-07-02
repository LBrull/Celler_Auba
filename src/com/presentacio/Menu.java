package com.presentacio;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame{

    private MenuController menuController = MenuController.getInstance();

    private JButton clientsProvidersButton;
    private JButton compresVendesButton;
    private JPanel rootPanel;
    private JLabel labelTitle;

    public Menu () {
        super();
        setContentPane(rootPanel);
        labelTitle.setFont(new Font("Calibri", Font.PLAIN, 20));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        clientsProvidersButton.addActionListener(e -> {
            menuController.showContactsView();
        });

        compresVendesButton.addActionListener(e -> {
            menuController.showTransactionsView();
        });

        pack();
        setSize(700, 300);
        setVisible(true);
    }

}
