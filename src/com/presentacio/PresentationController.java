package com.presentacio;

import javax.swing.*;

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
            System.out.println("returned presentationController instance");
        }
        return instance;
    }

    public void run(){
        managePeople = new ManagePeople();
        initComponents();
    }

    private void initComponents() {

        AddButton = managePeople.getAddPersonButton();
        EditButton = managePeople.getEditButton();
        DeleteButton = managePeople.getDeleteButton();
    }
}


