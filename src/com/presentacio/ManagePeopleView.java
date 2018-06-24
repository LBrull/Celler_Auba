package com.presentacio;

import com.model.Client;
import com.model.Provider;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManagePeopleView extends JFrame{

    private static final int SETUP_WIDTH = 1200;
    private static final int SETUP_HEIGHT = 700;

    private static ContactsViewController controller = ContactsViewController.getInstance();

    private JPanel rootPanel;
    private JButton AddPersonButton;
    private JButton DeleteButton;
    private JButton EditButton;
    private JLabel labelProveedors;
    private JLabel labelClients;
    private JTable clientsTable;
    private JTable providersTable;
    private JLabel labelWindowTitle;
    private ContactsTableModel clientsTableModel;
    private ContactsTableModel providersTableModel;
//    private Color backgroundColor = new Color(60, 63, 65);
    private Color textColor = new Color(187,187,187);
//    private Color gridColor = new Color(60, 63, 65);

    ManagePeopleView() {
        super();
        setContentPane(rootPanel);

        labelClients.setForeground(textColor);
        labelClients.setFont(new Font("Calibri", Font.PLAIN, 18));
        labelProveedors.setForeground(textColor);
        labelProveedors.setFont(new Font("Calibri", Font.PLAIN, 18));
        labelWindowTitle.setFont(new Font("Calibri", Font.PLAIN, 20));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //TODO: override window closing method

        EditButton.setVisible(false);
        DeleteButton.setVisible(false);
        AddPersonButton.addActionListener(e -> controller.addContactView());
        pack();
        setSize(SETUP_WIDTH, SETUP_HEIGHT);
        setVisible(true);
    }

    public ContactsTableModel getProvidersTableModel() {
        return providersTableModel;
    }

    public ContactsTableModel getClientsTableModel() {
        return clientsTableModel;
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

        clientsTableModel = new ContactsTableModel(clientsData, columnNames);
        clientsTable = new JTable(clientsTableModel);
        clientsTable.setCellSelectionEnabled(false);
        clientsTable.setRowSelectionAllowed(true);
        clientsTable.setFocusable(false);
        clientsTable.setAutoCreateRowSorter(true);
        clientsTable.getSelectionModel().addListSelectionListener(e -> SwingUtilities.invokeLater(
            () -> {
                if(!EditButton.isVisible()) {
                    EditButton.setVisible(true);
                    DeleteButton.setVisible(true);
                }
                if (!providersTable.getSelectionModel().isSelectionEmpty()) {
                    providersTable.clearSelection();
                }
                if (clientsTable.getSelectionModel().isSelectionEmpty() && providersTable.getSelectionModel().isSelectionEmpty()) {
                    EditButton.setVisible(false);
                    DeleteButton.setVisible(false);
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

        providersTableModel = new ContactsTableModel(providersData, columnNames);
        providersTable = new JTable(providersTableModel);
        providersTable.setCellSelectionEnabled(false);
        providersTable.setRowSelectionAllowed(true);
        providersTable.setFocusable(false);
        providersTable.setAutoCreateRowSorter(true);
        providersTable.getSelectionModel().addListSelectionListener(e -> SwingUtilities.invokeLater(
            () -> {
                if(!EditButton.isVisible()) {
                    EditButton.setVisible(true);
                    DeleteButton.setVisible(true);
                }
                if (!clientsTable.getSelectionModel().isSelectionEmpty()) {
                    clientsTable.clearSelection();
                }
                if (clientsTable.getSelectionModel().isSelectionEmpty() && providersTable.getSelectionModel().isSelectionEmpty()) {
                    EditButton.setVisible(false);
                    DeleteButton.setVisible(false);
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
