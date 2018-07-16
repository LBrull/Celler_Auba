package com.presentacio;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.time.LocalDateTime;


public class NewVendaAlbaraView extends JFrame{

    private NewVendaAlbaraViewController controller;

    private JLabel labelTitle;
    private JPanel rootPanel;
    private JPanel standardPanel;
    private JPanel ametllaPanel;
    private JPanel header;
    private JComboBox fromSelector;
    private JComboBox toSelector;
    private JTextField numField;
    private JTextField dataField;
    private JComboBox tipusSelector;
    private JRadioButton efectiuRadioButton;
    private JRadioButton tarjetaRadioButton;
    private JRadioButton altresRadioButton;
    private TableModel model;
    private JPanel bottomPanel;
    private JButton acceptButton;
    private JButton newItemButton;
    private JButton deleteItemButton;
    private JPanel tableButtonsPanel;
    private JTable table1;

    private Object columnNamesAmetlla[] = {"Article", "Descripció", "Quantitat", "Preu unitari", "Diposit"};
    private Object columnNamesGeneral[] = {"Article", "Descripció", "Quantitat", "Preu unitari"};
    private AlbaraAmetllaTableModel albaraAmetllaTableModel;
    private AlbaraAmetllaTableModel albaraGeneralTableModel;



    NewVendaAlbaraView() {
        setContentPane(rootPanel);
        labelTitle.setFont(new Font("Calibri", Font.PLAIN, 20));
        tipusSelector.setSelectedIndex(0);
        String date =  String.valueOf(LocalDateTime.now().getDayOfMonth());
        date = date.concat("/");
        date = date.concat(String.valueOf(LocalDateTime.now().getMonthValue()));
        date = date.concat("/");
        date = date.concat(String.valueOf(LocalDateTime.now().getYear()));
        dataField.setText(date);

        String numAlbara = "AM-";
        numAlbara = numAlbara.concat(controller.getNextAmetllaAlbaraNumber());
        numField.setText(numAlbara);

        tipusSelector.addActionListener(e -> {
            if (!(tipusSelector.getSelectedItem() == null) && tipusSelector.getSelectedItem().equals("Ametlla")) {
                System.out.println("selected ametlla");
                table1.setModel(albaraAmetllaTableModel);
                String identifier = "AM-";
                identifier = identifier.concat(controller.getNextAmetllaAlbaraNumber());
                numField.setText(identifier);
            }
            else if (!(tipusSelector.getSelectedItem() == null) && tipusSelector.getSelectedItem().equals("Raïm")){
                System.out.println("selected raïm");
                table1.setModel(albaraGeneralTableModel);
                String identifier = "RA-";
                identifier = identifier.concat(controller.getNextRaimAlbaraNumber());
                numField.setText(identifier);
            }
            else if (!(tipusSelector.getSelectedItem() == null) && tipusSelector.getSelectedItem().equals("Oliva")) {
                System.out.println("selected oliva");
                table1.setModel(albaraGeneralTableModel);
                String identifier = "OL-";
                identifier = identifier.concat(controller.getNextOlivaAlbaraNumber());
                numField.setText(identifier);
            }
        });

        pack();
        setVisible(true);
    }

    private void createUIComponents() {
        controller = NewVendaAlbaraViewController.getInstance();
        fromSelector = new FilterComboBox(controller.getProviders());
        toSelector = new FilterComboBox(controller.getClients());
        String[] petStrings = { "Ametlla", "Raïm", "Oliva"};
        tipusSelector = new JComboBox(petStrings);

        albaraAmetllaTableModel = new AlbaraAmetllaTableModel();
        albaraAmetllaTableModel.setColumnIdentifiers(columnNamesAmetlla);
        table1 = new JTable(albaraAmetllaTableModel);
        albaraGeneralTableModel = new AlbaraAmetllaTableModel();
        albaraGeneralTableModel.setColumnIdentifiers(columnNamesGeneral);
        System.out.println("end creting UI");

    }
}
