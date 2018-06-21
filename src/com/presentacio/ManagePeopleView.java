package com.presentacio;

import com.model.Client;
import com.model.Provider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManagePeopleView extends JFrame{

    private static final int SETUP_WIDTH = 800;
    private static final int SETUP_HEIGHT = 500;

    private static ContactsViewController controller = ContactsViewController.getInstance();

    private JPanel rootPanel;
    private JButton AddPersonButton;
    private JButton DeleteButton;
    private JButton EditButton;
    private JPanel topPanel;
    private JPanel providersTitlePanel;
    private JPanel clientsTitlePanel;
    private JPanel bottomPanel;
    private JLabel labelProveedors;
    private JLabel labelClients;
    private JTable clientsTable;
    private JTable providersTable;

//    private Color backgroundColor = new Color(60, 63, 65);
    private Color textColor = new Color(187,187,187);
//    private Color gridColor = new Color(60, 63, 65);

    ManagePeopleView() {
        super();
        setContentPane(rootPanel);

        labelClients.setForeground(textColor);
        labelClients.setFont(new Font("Calibri", Font.PLAIN, 20));
        labelProveedors.setForeground(textColor);
        labelProveedors.setFont(new Font("Calibri", Font.PLAIN, 20));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(SETUP_WIDTH, SETUP_HEIGHT);

        EditButton.setVisible(false);
        DeleteButton.setVisible(false);

        AddPersonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addContactView();
            }
        });

        pack();
        setVisible(true);
    }

    private void loadClients() {
        int numberOfClients = controller.getClientsCount();
        Object clientsData[][] = new Object[numberOfClients][5];
        Object columnNames[] = {"Nom", "Cognoms", "Telèfon", "Adreça", "e-mail"};
        ArrayList<Client> clients = controller.getClients();

        for(int i=0; i<numberOfClients; ++i) {
            clientsData[i][0] = clients.get(i).getName();
            clientsData[i][1] = clients.get(i).getSurname();
            clientsData[i][2] = clients.get(i).getTelephone();
            clientsData[i][3] = clients.get(i).getAddress();
            clientsData[i][4] = clients.get(i).getEmail();
        }

        ContactsTableModel contactsTableModel = new ContactsTableModel(clientsData, columnNames);
        clientsTable = new JTable(contactsTableModel);
        clientsTable.setAutoCreateRowSorter(true);
        clientsTable.getSelectionModel().addListSelectionListener(e -> SwingUtilities.invokeLater(
            () -> {
                if(!EditButton.isVisible()) {
                    EditButton.setVisible(true);
                    DeleteButton.setVisible(true);
                }
            }
        ));
    }

    private void loadProviders() {
        int numberOfProviders = controller.getProvidersCount();
        Object providersData[][] = new Object[numberOfProviders][5];
        Object columnNames[] = {"Nom", "Cognoms", "Telèfon", "Adreça", "e-mail"};
        ArrayList<Provider> providers = controller.getProviders();

        for(int i=0; i<numberOfProviders; ++i) {
            providersData[i][0] = providers.get(i).getName();
            providersData[i][1] = providers.get(i).getSurname();
            providersData[i][2] = providers.get(i).getTelephone();
            providersData[i][3] = providers.get(i).getAddress();
            providersData[i][4] = providers.get(i).getEmail();
        }

        ContactsTableModel contactsTableModel = new ContactsTableModel(providersData, columnNames);
        providersTable = new JTable(contactsTableModel);
        providersTable.setAutoCreateRowSorter(true);
        providersTable.getSelectionModel().addListSelectionListener(e -> SwingUtilities.invokeLater(
                () -> {
                    if(!EditButton.isVisible()) {
                        EditButton.setVisible(true);
                        DeleteButton.setVisible(true);
                    }
                }
        ));
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

    private void createUIComponents() {
        loadClients();
        loadProviders();
    }


}
