package com.presentacio;

import com.model.Client;
import com.model.Provider;
import com.model.ServerResponse;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ModifyContactView extends JFrame{

    private static ContactsViewController controller = ContactsViewController.getInstance();

    private JButton saveButton;
    private boolean oldProvider;
    private boolean oldClient;
    private JTextField codiTextField;
    private JTextField newName;
    private JTextField newSurname;
    private JTextField newTelephone;
    private JTextField newDomicili;
    private JTextField newCp;
    private JTextField newEmail;
    private JTextField newTown;
    private JTextField newDni;
    private JTextField newAccountNumber;

    private JLabel labelTitle;
    private JPanel rootPanel;

    ModifyContactView (String objectId, String oldName, String oldSurname, String oldDni, boolean oldProvider, boolean oldClient, String oldTelephone, String oldCp, String oldTown, String oldDomicili, String oldEmail, String oldAccountNumber) {
        setContentPane(rootPanel);

        labelTitle.setFont(new Font("Calibri", Font.PLAIN, 20));

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.codiTextField.setText(objectId);
        this.newName.setText(oldName);
        this.newSurname.setText(oldSurname);
        this.newDni.setText(oldDni);
        this.newTelephone.setText(oldTelephone);
        this.newCp.setText(oldCp);
        this.newTown.setText(oldTown);
        this.newDomicili.setText(oldDomicili);
        this.newEmail.setText(oldEmail);
        this.newAccountNumber.setText(oldAccountNumber);

        this.oldClient = oldClient;
        this.oldProvider = oldProvider;

        loadListeners();

        pack();
        setSize(900, 550);
        setVisible(true);
    }

    private void loadListeners() {
        saveButton.addActionListener(e -> {
            if (oldProvider) { //modifiquem un proveedor
                int rowTable = controller.getManagePeopleView().getProvidersTable().convertRowIndexToModel(controller.getManagePeopleView().getProvidersTable().getSelectedRow());
                try {
                    ServerResponse serverResponse = ContactsViewController.editProvider(codiTextField.getText(), newName.getText(), newSurname.getText(), newTelephone.getText(), newEmail.getText(),  newCp.getText(), newTown.getText(), newDomicili.getText(), newDni.getText(),  newAccountNumber.getText());
                    if (serverResponse.getStatus() != 200) {
                        JOptionPane.showMessageDialog(null, serverResponse.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Provider newProvider = parseJSONProvider(serverResponse);
                        controller.repaintProvidersTableWhenEdit(rowTable, newProvider);
                        dispose();
                    }
                } catch (IOException | JSONException e1) {
                    e1.printStackTrace();
                }
            } else if (oldClient) { //modifiquem un client
                int rowTable = controller.getManagePeopleView().getClientsTable().convertRowIndexToModel(controller.getManagePeopleView().getClientsTable().getSelectedRow());
                try {
                    ServerResponse serverResponse = ContactsViewController.editClient(codiTextField.getText(), newName.getText(), newSurname.getText(), newTelephone.getText(), newEmail.getText(),  newCp.getText(), newTown.getText(), newDomicili.getText(), newDni.getText(),  newAccountNumber.getText());
                    if (serverResponse.getStatus() != 200) {
                        JOptionPane.showMessageDialog(null, serverResponse.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        Client newClient = parseJSONClient(serverResponse);
                        controller.repaintClientsTableWhenEdit(rowTable, newClient);
                        dispose();
                    }

                } catch (IOException | JSONException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }

    private Provider parseJSONProvider(ServerResponse res) throws JSONException {
        JSONObject jsonObject = new JSONObject(res.getMessage());
        JSONObject jsonProduct = new JSONObject(jsonObject.getString("provider"));
        Provider newProv = new Provider();
        newProv.setObjectId(jsonProduct.getString("_id"));
        newProv.setName(jsonProduct.getString("name"));
        newProv.setSurname(jsonProduct.getString("surname"));
        newProv.setAccountNumber(jsonProduct.getString("accountNumber"));
        newProv.setEmail(jsonProduct.getString("email"));
        newProv.setAddress(jsonProduct.getString("address"));
        newProv.setTown(jsonProduct.getString("town"));
        newProv.setCP(jsonProduct.getString("cp"));
        newProv.setTelephone(jsonProduct.getString("telephone"));
        newProv.setDni_nif(jsonProduct.getString("dni_nif"));
        return newProv;
    }

    private Client parseJSONClient(ServerResponse res) throws JSONException {
        JSONObject jsonObject = new JSONObject(res.getMessage());
        JSONObject jsonProduct = new JSONObject(jsonObject.getString("client"));
        Client newCli = new Client();
        newCli.setObjectId(jsonProduct.getString("_id"));
        newCli.setName(jsonProduct.getString("name"));
        newCli.setSurname(jsonProduct.getString("surname"));
        newCli.setAccountNumber(jsonProduct.getString("accountNumber"));
        newCli.setEmail(jsonProduct.getString("email"));
        newCli.setAddress(jsonProduct.getString("address"));
        newCli.setTown(jsonProduct.getString("town"));
        newCli.setCP(jsonProduct.getString("cp"));
        newCli.setTelephone(jsonProduct.getString("telephone"));
        newCli.setDni_nif(jsonProduct.getString("dni_nif"));
        return newCli;
    }
}
