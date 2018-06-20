package com.presentacio;

import javax.swing.*;
import java.awt.*;

public class ManagePeople extends JFrame{

    private static final int SETUP_WIDTH = 800;
    private static final int SETUP_HEIGHT = 500;

    private static PresentationController controller = PresentationController.getInstance();

    private JPanel rootPanel;
    private JList providersList;
    private JList clientsList;
    private JButton AddPersonButton;
    private JButton DeleteButton;
    private JButton EditButton;
    private JScrollPane clientsPanel;
    private JScrollPane providersPanel;
    private JPanel topPanel;
    private JPanel providersTitlePanel;
    private JPanel clientsTitlePanel;
    private JPanel bottomPanel;
    private JLabel labelProveedors;
    private JLabel labelClients;

    private Color backgroundColor = new Color(60, 63, 65);
    private Color textColor = new Color(187,187,187);

    ManagePeople() {
        super();

        setContentPane(rootPanel);

        labelClients.setForeground(textColor);
        labelProveedors.setForeground(textColor);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(SETUP_WIDTH, SETUP_HEIGHT);
        pack();
        setVisible(true);
    }

    public JButton getAddPersonButton() {
        return AddPersonButton;
    }

    public JButton getEditButton() {
        return EditButton;
    }

    public JButton getDeleteButton() {
        return DeleteButton;
    }

    public JList getProvidersList() {
        return providersList;
    }

    public JList getClientsList() {
        return clientsList;
    }
}
