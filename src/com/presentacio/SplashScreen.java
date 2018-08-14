package com.presentacio;

import org.jb2011.lnf.beautyeye.widget.border.BEShadowBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SplashScreen extends JWindow {
    private static JProgressBar progressBar = new JProgressBar();
    private static int count = 1;
    private static int PROGBAR_MAX = 100;
    private static Timer progressBarTimer;
    private ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            progressBar.setValue(count);
            if (PROGBAR_MAX == count) {
                progressBarTimer.stop();
                SplashScreen.this.setVisible(false);
                createAndShowFrame();
            }
            count++;
        }
    };
    private JPanelBackground panel;
    private JPanel panel1;
    private JLabel label;

    public SplashScreen() {
        Container container = getContentPane();

        panel.setBackground("/images/splash.jpg");
        panel.setBorder(new BEShadowBorder());
        container.add(panel, BorderLayout.CENTER);

        label.setText("Celler Aubarca");
        label.setFont(new Font("Papyrus", Font.BOLD, 38));

        progressBar.setMaximum(PROGBAR_MAX);
        //container.add(progressBar, BorderLayout.SOUTH);
        pack();
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        startProgressBar();
    }
    private void startProgressBar() {
        int TIMER_PAUSE = 25;
        progressBarTimer = new Timer(TIMER_PAUSE, al);
        progressBarTimer.start();
    }
    private void createAndShowFrame() {
        LoginController loginController = LoginController.getInstance();
        loginController.showLoginView();
    }
}
