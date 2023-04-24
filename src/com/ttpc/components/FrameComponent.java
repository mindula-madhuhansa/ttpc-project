package com.ttpc.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FrameComponent extends JFrame {
    public FrameComponent(){
        this.setTitle("Train Ticket Price Calculator");
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.GRAY);

        //add icon to frame
        String iconPath = "src/res/ttpc.png";
        ImageIcon icon = new ImageIcon(iconPath);
        this.setIconImage(icon.getImage());
    }

    public Border addBorder(){
        //creating a custom border
        Border marginBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        return BorderFactory.createCompoundBorder(lineBorder, marginBorder);
    }
}
