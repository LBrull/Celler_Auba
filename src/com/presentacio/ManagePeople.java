package com.presentacio;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagePeople extends JFrame{

    private static final int SETUP_WIDTH = 200;
    private static final int SETUP_HEIGHT = 200;

    private static PresentationController controller = PresentationController.getInstance();

    private JPanel panel1;
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
        createUIComponents();
    }

    private void createUIComponents() {
        setSize(SETUP_WIDTH, SETUP_HEIGHT);
        panel1= new JPanel();
        setContentPane(panel1);
        setLocationRelativeTo(null);
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
}
