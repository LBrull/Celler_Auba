package com.presentacio;

import javax.swing.*;
import java.awt.*;
import java.util.prefs.Preferences;

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
    private JButton tancarSessioButton;
    private JLabel sessioName;


    public Menu () {
        super();
        setTitle("Celler Aubrca - Menú");
        setIconImage(new ImageIcon(getClass().getResource("/icons/icono-olivo.png")).getImage());
        setContentPane(rootPanel);
        labelTitle.setFont(new Font("Calibri", Font.PLAIN, 28));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        sessioName.setText(menuController.getSessioName());
        compresVendesButton.setEnabled(false);

        tancarSessioButton.addActionListener(e -> {
            Preferences root = Preferences.userRoot();
            root.remove("token");
            temporadesButton.setEnabled(false);
            compresVendesButton.setEnabled(false);
            clientsProvidersButton.setEnabled(false);
            productsButton.setEnabled(false);
            JOptionPane.showMessageDialog(null, "S'ha tancat la sessió", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
        });

        temporadesButton.addActionListener(e -> {
            menuController.showTemporadesView();
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
        setSize(750, 650);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
