package com.presentacio;

import javax.swing.*;
import java.awt.*;

public class JPanelBackground extends JPanel {

    private Image background = null;

    // Metodo que es llamado automaticamente por la maquina virtual Java cada vez que repinta

    @Override
    public void paintComponent(Graphics g) {
        int width = this.getSize().width;
        int height = this.getSize().height;

        if (this.background != null) {
            g.drawImage(this.background, 0, 0, width, height, null);
        }
        super.paintComponent(g);
    }

    // Metodo donde le pasaremos la dirección de la imagen a cargar.
    public void setBackground(String imagePath) {
            Image image = new ImageIcon(getClass().getResource("/images/loginBack.png")).getImage();
            this.setOpaque(false);
            this.background = image;
            repaint();
    }

}