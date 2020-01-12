package com.weatherchecker.ui;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Color bg = Color.decode("#3EC9BE");
        graphics.setColor(bg);
        graphics.fillRect(0, 0, getWidth(), getHeight());

    }
}