package com.presentacio;

import javax.swing.*;
import java.awt.*;

public class TransactionsView extends JFrame{

    private JPanel rootPanel;
    private JLabel labelTitle;
    private JButton novaVendaButton;
    private JButton novaCompraButton;
    private JButton compresButton;
    private JLabel labelSubtitle1;
    private JLabel labelSubtitle2;

    public TransactionsView() {
        setContentPane(rootPanel);
        setTitle("Celler Aubà");
        setIconImage(new ImageIcon(getClass().getResource("/icons/icono-olivo.png")).getImage());
        labelTitle.setFont(new Font("Calibri", Font.PLAIN, 20));
        labelSubtitle1.setFont(new Font("Calibri", Font.PLAIN, 16));
        labelSubtitle2.setFont(new Font("Calibri", Font.PLAIN, 16));

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        initListeners();

        pack();
        setSize(400, 400);
        setVisible(true);
    }

    private void initListeners() {
        novaVendaButton.addActionListener(e -> {
            new NewVendaAlbaraView();
        });
    }

}
