package com.presentacio;

import javax.swing.*;
import java.awt.*;

public class NewVendaAlbaraView extends JFrame{

    private NewVendaAlbaraViewController controller;

    private JLabel labelTitle;
    private JPanel body;
    private JPanel rootPanel;
    private JPanel header;
    private JComboBox fromSelector;
    private JComboBox toSelector;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox tipusSelector;
    private JRadioButton efectiuRadioButton;
    private JRadioButton tarjetaRadioButton;
    private JRadioButton altresRadioButton;
    private JTable table1;
    private JPanel bottomPanel;
    private JButton acceptButton;

    NewVendaAlbaraView() {
        setContentPane(rootPanel);
        labelTitle.setFont(new Font("Calibri", Font.PLAIN, 20));

        pack();
        setVisible(true);

    }

    private void createUIComponents() {
        controller = NewVendaAlbaraViewController.getInstance();
        fromSelector = new FilterComboBox(controller.getProviders());
        toSelector = new FilterComboBox(controller.getClients());
    }
}
