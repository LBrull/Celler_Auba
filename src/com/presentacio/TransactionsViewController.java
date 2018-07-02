package com.presentacio;

public class TransactionsViewController {

    private static TransactionsViewController instance = null;
    private TransactionsView transactionsView;

    private TransactionsViewController() {}

    public static TransactionsViewController getInstance() {
        if (instance == null) {
            instance = new TransactionsViewController();
        }
        return instance;
    }

    public void showTransactionsView() {
        transactionsView = new TransactionsView();
    }
}
