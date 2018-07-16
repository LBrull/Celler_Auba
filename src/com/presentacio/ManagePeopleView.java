package com.presentacio;

import com.model.Client;
import com.model.Provider;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
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
    private JTextField filterTextField;
    private ContactsTableModel clientsTableModel;
    private ContactsTableModel providersTableModel;

    private TableRowSorter<ContactsTableModel> sorter;
    private TableRowSorter<ContactsTableModel> sorter2;


    ManagePeopleView() {
        super();
        setContentPane(rootPanel);

        labelClients.setFont(new Font("Calibri", Font.PLAIN, 18));
        labelProveedors.setFont(new Font("Calibri", Font.PLAIN, 18));
        labelWindowTitle.setFont(new Font("Calibri", Font.PLAIN, 20));

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        EditButton.setVisible(false);
        DeleteButton.setVisible(false);

        AddPersonButton.addActionListener(e -> controller.addContactView());
        DeleteButton.addActionListener(e -> {
            if (providersTable.getSelectedRowCount()>=1) {
                int[] rows = providersTable.getSelectedRows();
                if (rows.length == 1) {
                    int rowView = rows[0];
                    int rowTable = providersTable.convertRowIndexToModel(rowView);
                    int dialogButton = JOptionPane.showConfirmDialog (null, "Segur que voleu eliminar aquest proveedor?","WARNING", JOptionPane.YES_NO_OPTION);
                    String name = providersTableModel.getValueAt(rowTable, 0).toString();
                    String surname = providersTableModel.getValueAt(rowTable, 1).toString();
                    if (dialogButton == 0) {
                        controller.deleteOneProvider(name, surname);
                        controller.repaintProvidersOneRowDeleted(rowTable);
                    }
                }
                else {
                    int dialogButton = JOptionPane.showConfirmDialog (null, "Segur que voleu eliminar "+rows.length+" proveedors?","WARNING", JOptionPane.YES_NO_OPTION);
                    for (int rowView : rows) {
                        int rowTable = providersTable.convertRowIndexToModel(rowView);
                        String name = providersTableModel.getValueAt(rowTable, 0).toString();
                        String surname = providersTableModel.getValueAt(rowTable, 1).toString();
                        if (dialogButton == 0) {
                            System.out.println("name "+name + " surname "+surname+" deleted");
                            controller.deleteOneProvider(name, surname);
                        }
                    }
                    actualizeProvidersTable(rows);
                }
            }
            if (clientsTable.getSelectedRowCount()>=1) {
                int[] rows = clientsTable.getSelectedRows();
                if (rows.length == 1) {
                    int rowView = rows[0];
                    int rowTable = clientsTable.convertRowIndexToModel(rowView);
                    int dialogButton = JOptionPane.showConfirmDialog (null, "Segur que voleu eliminar aquest client?","WARNING", JOptionPane.YES_NO_OPTION);
                    String name = clientsTableModel.getValueAt(rowTable, 0).toString();
                    String surname = clientsTableModel.getValueAt(rowTable, 1).toString();
                    if (dialogButton == 0) {
                        controller.deleteOneClient(name, surname);
                        controller.repaintClientsOneRowDeleted(rowTable);
                    }
                }
                else {
                    int dialogButton = JOptionPane.showConfirmDialog (null, "Segur que voleu eliminar "+rows.length+" clients?","WARNING", JOptionPane.YES_NO_OPTION);
                    for (int rowView : rows) {
                        int rowTable = clientsTable.convertRowIndexToModel(rowView);
                        String name = clientsTableModel.getValueAt(rowTable, 0).toString();
                        String surname = clientsTableModel.getValueAt(rowTable, 1).toString();
                        if (dialogButton == 0) {
                            System.out.println("name "+name + " surname "+surname+" deleted");
                            controller.deleteOneClient(name, surname);

                        }
                    }
                    actualizeClientsTable(rows);
                }
            }
        });

        EditButton.addActionListener(e -> {
            if (providersTable.getSelectedRowCount() == 1 || clientsTable.getSelectedRowCount() == 1) {
                controller.ModifyContactView();
            }
        });

        filterTextField.getDocument().addDocumentListener(new DocumentListener() {
               public void changedUpdate(DocumentEvent e) {
                   newFilter();
               }
               public void insertUpdate(DocumentEvent e) {
                   newFilter();
               }
               public void removeUpdate(DocumentEvent e) {
                   newFilter();
               }
           });

        pack();
        setSize(SETUP_WIDTH, SETUP_HEIGHT);
        setVisible(true);
    }

    private void actualizeClientsTable(int[] rows) {
        for ( int i = (rows.length) -1 ;  i >= 0; i-- ) {
            controller.repaintClientsOneRowDeleted(clientsTable.convertRowIndexToModel(rows[i]));
        }
    }

    private void actualizeProvidersTable(int[] rows) {
        for ( int i = rows.length -1 ;  i >= 0; i-- ) {
            controller.repaintProvidersOneRowDeleted(providersTable.convertRowIndexToModel(rows[i]));
        }
    }

    private void newFilter() {
        RowFilter<ContactsTableModel, Object> rf;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter("(?i)" + filterTextField.getText(), 0, 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
        sorter2.setRowFilter(rf);
    }

    public ContactsTableModel getProvidersTableModel() {
        return providersTableModel;
    }

    public ContactsTableModel getClientsTableModel() {
        return clientsTableModel;
    }

    private void loadClients() {

        clientsTableModel = new ContactsTableModel();
        Object columnNames[] = {"Nom", "Cognoms", "DNI", "Núm. Compte", "Telèfon", "E-mail", "CP", "Població", "Domicili"};
        clientsTableModel.setColumnIdentifiers(columnNames);
        clientsTable = new JTable(clientsTableModel);
        sorter = new TableRowSorter<>(clientsTableModel);
        clientsTable.setRowSorter(sorter);
        sorter.setRowFilter(null);

        //load clients data
        ArrayList<Client> clients = controller.getClients();
        int numberOfClients = controller.getClientsCount();
        Object clientsData[][] = new Object[numberOfClients][9];
        for(int i=0; i<numberOfClients; ++i) {
            clientsData[i][0] = clients.get(i).getName();
            clientsData[i][1] = clients.get(i).getSurname();
            clientsData[i][2] = clients.get(i).getDni_nif();
            clientsData[i][3] = clients.get(i).getAccountNumber();
            clientsData[i][4] = clients.get(i).getTelephone();
            clientsData[i][5] = clients.get(i).getEmail();

            clientsData[i][6] = clients.get(i).getCp();
            clientsData[i][7] = clients.get(i).getTown();
            clientsData[i][8] = clients.get(i).getAddress();
        }

        for (Object[] aClientsData : clientsData) {
            clientsTableModel.addRow(aClientsData);
        }

        clientsTable.setCellSelectionEnabled(false);
        clientsTable.setRowSelectionAllowed(true);
        clientsTable.setFocusable(false);
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
        Object providersData[][] = new Object[numberOfProviders][9];
        Object columnNames[] = {"Nom", "Cognoms", "NIF", "Núm. Compte",  "Telèfon", "E-mail", "CP", "Població", "Domicili"};
        ArrayList<Provider> providers = controller.getProviders();
        for(int i=0; i<numberOfProviders; ++i) {
            providersData[i][0] = providers.get(i).getName();
            providersData[i][1] = providers.get(i).getSurname();
            providersData[i][2] = providers.get(i).getDni_nif();
            providersData[i][3] = providers.get(i).getAccountNumber();
            providersData[i][4] = providers.get(i).getTelephone();
            providersData[i][5] = providers.get(i).getEmail();

            providersData[i][6] = providers.get(i).getCp();
            providersData[i][7] = providers.get(i).getTown();
            providersData[i][8] = providers.get(i).getAddress();
        }

        providersTableModel = new ContactsTableModel(providersData, columnNames);
        providersTable = new JTable(providersTableModel);
        sorter2 = new TableRowSorter<>(providersTableModel);
        providersTable.setRowSorter(sorter2);
        sorter2.setRowFilter(null);

        providersTable.setCellSelectionEnabled(false);
        providersTable.setRowSelectionAllowed(true);
        providersTable.setFocusable(false);
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

    public JTable getProvidersTable() {
        return providersTable;
    }

    public JTable getClientsTable() {
        return clientsTable;
    }

    public ContactsTableModel geClientsTableModel() {
        return clientsTableModel;
    }
}
