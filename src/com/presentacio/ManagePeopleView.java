package com.presentacio;

import com.model.Client;
import com.model.Provider;
import org.json.JSONException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.IOException;
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
                    String objectId = providersTableModel.getValueAt(rowTable, 0).toString();
                    if (dialogButton == 0) {
                        try {
                            controller.deleteOneProvider(objectId);
                            controller.repaintProvidersOneRowDeleted(rowTable);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                else {
                    int dialogButton = JOptionPane.showConfirmDialog (null, "Segur que voleu eliminar "+rows.length+" proveedors?","WARNING", JOptionPane.YES_NO_OPTION);
                    for (int rowView : rows) {
                        int rowTable = providersTable.convertRowIndexToModel(rowView);
                        String objectId = providersTableModel.getValueAt(rowTable, 0).toString();
                        if (dialogButton == 0) {
                            try {
                                controller.deleteOneProvider(objectId);
                                controller.repaintProvidersOneRowDeleted(rowTable);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }
            if (clientsTable.getSelectedRowCount()>=1) {
                int[] rows = clientsTable.getSelectedRows();
                if (rows.length == 1) {
                    int rowView = rows[0];
                    int rowTable = clientsTable.convertRowIndexToModel(rowView);
                    int dialogButton = JOptionPane.showConfirmDialog (null, "Segur que voleu eliminar aquest client?","WARNING", JOptionPane.YES_NO_OPTION);
                    String objectId = clientsTableModel.getValueAt(rowTable, 0).toString();
                    if (dialogButton == 0) {
                        try {
                            controller.deleteOneClient(objectId);
                            controller.repaintClientsOneRowDeleted(rowTable);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                else {
                    int dialogButton = JOptionPane.showConfirmDialog (null, "Segur que voleu eliminar "+rows.length+" clients?","WARNING", JOptionPane.YES_NO_OPTION);
                    for (int rowView : rows) {
                        int rowTable = clientsTable.convertRowIndexToModel(rowView);
                        String objectId = clientsTableModel.getValueAt(rowTable, 0).toString();
                        if (dialogButton == 0) {
                            try {
                                controller.deleteOneClient(objectId);
                                controller.repaintClientsOneRowDeleted(rowTable);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        EditButton.addActionListener(e -> {
            if (providersTable.getSelectedRowCount() == 1 || clientsTable.getSelectedRowCount() == 1) {
                try {
                    controller.ModifyContactView();
                } catch (IOException | JSONException e1) {
                    e1.printStackTrace();
                }
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

    private void newFilter() {
        RowFilter<ContactsTableModel, Object> rf;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter("(?i)" + filterTextField.getText(), 1, 2);
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

    private void loadClients() throws IOException, JSONException {

        clientsTableModel = new ContactsTableModel();
        Object columnNames[] = {"Codi", "Nom", "Cognoms", "DNI", "Núm. Compte", "Telèfon", "E-mail", "CP", "Població", "Domicili"};
        clientsTableModel.setColumnIdentifiers(columnNames);
        clientsTable = new JTable(clientsTableModel);
        sorter = new TableRowSorter<>(clientsTableModel);
        clientsTable.setRowSorter(sorter);
        sorter.setRowFilter(null);

        //load clients data
        ArrayList<Client> clients = controller.getClients();
        Object clientsData[][] = new Object[clients.size()][10];
        for(int i=0; i<clients.size(); ++i) {
            clientsData[i][0] = clients.get(i).getObjectId();
            clientsData[i][1] = clients.get(i).getName();
            clientsData[i][2] = clients.get(i).getSurname();
            clientsData[i][3] = clients.get(i).getDni_nif();
            clientsData[i][4] = clients.get(i).getAccountNumber();
            clientsData[i][5] = clients.get(i).getTelephone();
            clientsData[i][6] = clients.get(i).getEmail();
            clientsData[i][7] = clients.get(i).getCp();
            clientsData[i][8] = clients.get(i).getTown();
            clientsData[i][9] = clients.get(i).getAddress();
        }

        for (Object[] aClientsData : clientsData) {
            clientsTableModel.addRow(aClientsData);
        }

        clientsTable.setCellSelectionEnabled(false);
        clientsTable.setRowSelectionAllowed(true);
        clientsTable.setFocusable(false);
        clientsTable.getSelectionModel().addListSelectionListener(e -> SwingUtilities.invokeLater(
            () -> {
                if (clientsTable.getSelectedRowCount() == 1 || providersTable.getSelectedRowCount() == 1) {
                    if (clientsTable.getSelectedRowCount() != 0) {
                        providersTable.clearSelection();
                    }
                    else if (providersTable.getSelectedRowCount() != 0) {
                        clientsTable.clearSelection();
                    }
                    DeleteButton.setVisible(true);
                    EditButton.setVisible(true);
                }
                if (clientsTable.getSelectedRowCount() > 1 || providersTable.getSelectedRowCount() > 1) {
                    if (clientsTable.getSelectedRowCount() != 0) {
                        providersTable.clearSelection();
                    }
                    else if (providersTable.getSelectedRowCount() != 0) {
                        clientsTable.clearSelection();
                    }
                    EditButton.setVisible(false);
                }
                if (clientsTable.getSelectionModel().isSelectionEmpty() && providersTable.getSelectionModel().isSelectionEmpty()) {
                    EditButton.setVisible(false);
                    DeleteButton.setVisible(false);
                }
            }
        ));
    }

    private void loadProviders() throws IOException, JSONException {
        ArrayList<Provider> providers = controller.getProviders();
        Object providersData[][] = new Object[providers.size()][10];
        Object columnNames[] = {"Codi", "Nom", "Cognoms", "DNI", "Núm. Compte", "Telèfon", "E-mail", "CP", "Població", "Domicili"};
        for(int i=0; i<providers.size(); ++i) {
            providersData[i][0] = providers.get(i).getObjectId();
            providersData[i][1] = providers.get(i).getName();
            providersData[i][2] = providers.get(i).getSurname();
            providersData[i][3] = providers.get(i).getDni_nif();
            providersData[i][4] = providers.get(i).getAccountNumber();
            providersData[i][5] = providers.get(i).getTelephone();
            providersData[i][6] = providers.get(i).getEmail();
            providersData[i][7] = providers.get(i).getCp();
            providersData[i][8] = providers.get(i).getTown();
            providersData[i][9] = providers.get(i).getAddress();
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

    private void createUIComponents() throws IOException, JSONException {
        loadClients();
        loadProviders();
    }

    public JTable getProvidersTable() {
        return providersTable;
    }

    public JTable getClientsTable() {
        return clientsTable;
    }
}
