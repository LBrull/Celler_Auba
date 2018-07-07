package com.presentacio;

public class MenuController {

    private static MenuController instance = null;

    private ContactsViewController contactsInstance;
    private TransactionsViewController transactionsInstance;
    private ProductsViewController productsInstance;

    private MenuController () {}

    public static MenuController getInstance() {
        if (instance == null) {
            instance = new MenuController();
        }
        return instance;
    }

    public void showMenuView() {
        new Menu();

    }

    public void showContactsView() {
        contactsInstance = ContactsViewController.getInstance();
        contactsInstance.showContactsView();
    }

    public void showTransactionsView() {
        transactionsInstance = TransactionsViewController.getInstance();
        transactionsInstance.showTransactionsView();
    }

    public void showProductesView() {
        productsInstance = ProductsViewController.getInstance();
        productsInstance.showProductsView();
    }
}