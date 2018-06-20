package com.presentacio;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PresentationController {

    private static PresentationController instance = null;
    private ManagePeople managePeople;
    private JButton AddButton;
    private JButton EditButton;
    private JButton DeleteButton;


    private PresentationController() {
    }

    public static PresentationController getInstance() {
        if (instance == null) {
            instance = new PresentationController();
        }
        return instance;
    }

    public void run(){
        managePeople = new ManagePeople();
        initComponents();
        initListeners();
    }

    private void initComponents() {

        AddButton = managePeople.getAddPersonButton();
        EditButton = managePeople.getEditButton();
        DeleteButton = managePeople.getDeleteButton();


    }

    private void initListeners() {

        AddButton.setContentAreaFilled(false);
        EditButton.setContentAreaFilled(false);
        AddButton.addActionListener(new AddButtonListener());
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}


