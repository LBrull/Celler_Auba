package com.presentacio;

import javax.swing.*;

public class ManagePeople extends JFrame{

    private static final int SETUP_WIDTH = 200;
    private static final int SETUP_HEIGHT = 200;

    private static PresentationController controller = PresentationController.getInstance();

    private JPanel rootPanel;
    private JList providersList;
    private JList clientsList;
    private JPanel bottomSpace;
    private JPanel topSpace;
    private JButton AddPersonButton;
    private JButton DeleteButton;
    private JButton EditButton;
    private JPanel providersPanel;
    private JPanel clientsPanel;

    public ManagePeople() {
        super();
        setContentPane(rootPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        //createUIComponents();
    }

//    private void createUIComponents() {
//        setSize(SETUP_WIDTH, SETUP_HEIGHT);
//        setContentPane(rootPanel);
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }

    public JButton getAddPersonButton() {
        return AddPersonButton;
    }

    public JButton getEditButton() {
        return EditButton;
    }

    public JButton getDeleteButton() {
        return DeleteButton;
    }
}
