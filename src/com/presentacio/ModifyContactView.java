package com.presentacio;

import javax.swing.*;
import java.awt.*;

public class ModifyContactView extends JFrame{
    private JButton saveButton;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox newProviderCheck;
    private JCheckBox newClientCheck;
    private JTextField oldName;
    private JTextField oldTelephone;
    private JTextField oldAddress;
    private JTextField newName;
    private JTextField newSurname;
    private JTextField newTelephone;
    private JTextField newAddress;
    private JTextField oldSurname;
    private JTextField oldEmail;
    private JTextField newEmail;
    private JLabel labelTitle;
    private JLabel labelSubtitleOld;
    private JLabel labelSubtitleNew;
    private JPanel rootPanel;
    private JLabel emptyName;
    private JLabel emptySurname;
    private JLabel emptyType;
    private JLabel emptyTelephone;

    ModifyContactView () {
        setContentPane(rootPanel);

        labelTitle.setFont(new Font("Calibri", Font.PLAIN, 20));
        labelSubtitleNew.setFont(new Font("Calibri", Font.PLAIN, 16));
        labelSubtitleOld.setFont(new Font("Calibri", Font.PLAIN, 16));

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); //TODO: override window closing method

        emptyName.setVisible(true);
        emptySurname.setVisible(true);
        emptyTelephone.setVisible(true);
        emptyType.setVisible(true);

        pack();
        setSize(900, 500);
        setVisible(true);
    }

}
