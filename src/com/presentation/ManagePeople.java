package com.presentation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagePeople {
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("ManagePeople");
        frame.setContentPane(new ManagePeople().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {

        panel1 = new JPanel();
        providersList = new JList();
        clientsList = new JList();
        bottomSpace = new JPanel();
        topSpace = new JPanel();
        AddPersonButton = new JButton();
        DeleteButton = new JButton();
        EditButton = new JButton();
        providersPanel = new JPanel();
        clientsPanel = new JPanel();

        DeleteButton.setVisible(false);
        EditButton.setVisible(false);

        AddPersonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button add clicked");
            }
        });

        providersList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                DeleteButton.setVisible(true);
                EditButton.setVisible(true);
            }
        });

        DefaultListModel<String> providersListModel = new DefaultListModel<>();
        JList<String> providersList = new JList<>( providersListModel );


        //simulateMain();

    }
}
