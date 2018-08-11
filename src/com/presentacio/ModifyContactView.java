package com.presentacio;

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
            if (oldProvider && !oldClient) { //modifiquem un proveedor
                int rowTable = controller.getManagePeopleView().getProvidersTable().convertRowIndexToModel(controller.getManagePeopleView().getProvidersTable().getSelectedRow());
                try {
                    ServerResponse serverResponse = ContactsViewController.editProvider(codiTextField.getText(), newName.getText(), newSurname.getText(), newTelephone.getText(), newEmail.getText(),  newCp.getText(), newTown.getText(), newDomicili.getText(), newDni.getText(),  newAccountNumber.getText());
                    if (serverResponse.getStatus() != 200) {
                        JOptionPane.showMessageDialog(null, serverResponse.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Provider newProvider = parseJSON(serverResponse);
                        controller.repaintProvidersTableWhenEdit(rowTable, newProvider);
                        dispose();
                    }
                } catch (IOException | JSONException e1) {
                    e1.printStackTrace();
                }
            } else if (oldClient && !oldProvider) { //modifiquem un client
                int rowTable = controller.getManagePeopleView().getClientsTable().convertRowIndexToModel(controller.getManagePeopleView().getClientsTable().getSelectedRow());
                ServerResponse resDelete;
                ServerResponse resSave;
                try {
                    resDelete = controller.deleteOneClient(codiTextField.getText());
                    resSave = controller.saveOneClient(newName.getText(), newSurname.getText(), newDni.getText(), newTelephone.getText(), newCp.getText(), newTown.getText(), newDomicili.getText(), newEmail.getText(), newAccountNumber.getText());
                    if (resDelete.getStatus() == 200 && resSave.getStatus() == 200) {
                        controller.repaintClientsOneRowDeleted(rowTable);
                        controller.repaintClientsOneRowAdded(newName.getText(), newSurname.getText(), newDni.getText(), newTelephone.getText(), newCp.getText(), newTown.getText(), newDomicili.getText(), newEmail.getText(), newAccountNumber.getText());
                        dispose();
                    }

                } catch (IOException | JSONException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }

    private Provider parseJSON(ServerResponse res) throws JSONException {
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
}
