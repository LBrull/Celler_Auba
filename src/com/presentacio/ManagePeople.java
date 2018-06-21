package com.presentacio;

import com.model.Client;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ManagePeople extends JFrame{

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

    ManagePeople() {
        super();
        setContentPane(rootPanel);

        labelClients.setForeground(textColor);
        labelProveedors.setForeground(textColor);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(SETUP_WIDTH, SETUP_HEIGHT);

       // loadProviders();
        EditButton.setVisible(false);
        DeleteButton.setVisible(false);
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
    }
}
