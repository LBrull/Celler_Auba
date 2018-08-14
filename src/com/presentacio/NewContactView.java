package com.presentacio;

import com.model.ServerResponse;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class NewContactView extends JFrame {

    private static ContactsViewController controller = ContactsViewController.getInstance();

    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JCheckBox checkBoxProvider;
    private JCheckBox checkBoxClient;
    private JTextField telephoneTextField;
    private JTextField addressTextField;
    private JTextField emailTextField;
    private JButton saveButton;
    private JPanel rootPanel;
    private JLabel labelTitle;
    private JTextField cpTextField;
    private JTextField townTextField;
    private JTextField dniNifTextField;
    private JTextField accountNumberTextField;
    private JLabel JLabelTipus;


    NewContactView() {
        super();
        setTitle("Celler Aubà");
        setIconImage(new ImageIcon(getClass().getResource("/icons/icono-olivo.png")).getImage());
        setContentPane(rootPanel);
        labelTitle.setFont(new Font("Calibri", Font.PLAIN, 20));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JLabelTipus.setForeground(new Color(200,30,60));

        checkBoxClient.addActionListener(e -> {
            if (!checkBoxClient.isSelected() && !checkBoxProvider.isSelected()) {
                JLabelTipus.setForeground(new Color(200,30,60));
            }
            else {
                JLabelTipus.setForeground(new Color(0,0,0));
            }
        });

        checkBoxProvider.addActionListener(e -> {
            if (!checkBoxClient.isSelected() && !checkBoxProvider.isSelected()) {
                JLabelTipus.setForeground(new Color(200,30,60));
            }
            else {
                JLabelTipus.setForeground(new Color(0,0,0));
            }
        });

/////////////////////////////////////////

        saveButton.addActionListener(e -> {
            if (checkBoxClient.isSelected() && !checkBoxProvider.isSelected()) {
                ServerResponse res;
                try {
                    res = controller.saveNewClient();
                    if (res.getStatus() == 200 && !res.getMessage().contains("not save")) {
                        controller.repaintClientsTableWhenAdd(res);
                        dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, res.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | JSONException e1) {
                    e1.printStackTrace();
                }

            } else if (!checkBoxClient.isSelected() && checkBoxProvider.isSelected()) {
                ServerResponse res;
                try {
                    res = controller.saveNewProvider();
                    if (res.getStatus() == 200) {
                        controller.repaintProvidersTableWhenAdd(res);
                        dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, res.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | JSONException e1) {
                    e1.printStackTrace();
                }

            } else if (checkBoxClient.isSelected() && checkBoxProvider.isSelected()){
                ServerResponse res1;
                ServerResponse res2;
                try {
                    res1 = controller.saveNewClient();
                    res2 = controller.saveNewProvider();
                    if (res1.getStatus()==200 && res2.getStatus() == 200) {
                        controller.repaintClientsTableWhenAdd(res1);
                        controller.repaintProvidersTableWhenAdd(res2);
                        dispose();
                    }
                    else if (res1.getStatus()==200) {
                        controller.repaintClientsTableWhenAdd(res1);
                        JOptionPane.showMessageDialog(this, "Només s'ha pogut guardar el client", "", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                    else if (res2.getStatus()==200) {
                        controller.repaintProvidersTableWhenAdd(res2);
                        JOptionPane.showMessageDialog(this, "Només s'ha pogut guardar el proveidor", "", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                } catch (IOException | JSONException e1) {
                    e1.printStackTrace();
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "Heu de triar el tipus de contacte", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        pack();
        setSize(700, 550);
        setVisible(true);
    }

//    public JCheckBox getCheckBoxClient() {
//        return checkBoxClient;
//    }
//
//    public JCheckBox getCheckBoxProvider() {
//        return checkBoxProvider;
//    }

    public JTextField getCpTextField() {
        return cpTextField;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getSurnameTextField() {
        return surnameTextField;
    }

    public JTextField getTelephoneTextField() {
        return telephoneTextField;
    }

    public JTextField getAddressTextField() {
        return addressTextField;
    }

    public JTextField getEmailTextField() {
        return emailTextField;
    }

    public JTextField getTownTextField() {
        return townTextField;
    }

    public JTextField getDniNifTextField() {
        return dniNifTextField;
    }

    public JTextField getAccountNumberTextField() {
        return accountNumberTextField;
    }
}
