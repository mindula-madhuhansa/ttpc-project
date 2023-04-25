package com.ttpc.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FrameComponent extends JFrame {
    public FrameComponent(){
        setTitle("Train Ticket Price Calculator");
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.GRAY);

        //add icon to frame
        String iconPath = "src/res/ttpc.png";
        ImageIcon icon = new ImageIcon(iconPath);
        setIconImage(icon.getImage());
    }

    //creating a custom border
    public Border addBorder(){
        Border marginBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        return BorderFactory.createCompoundBorder(lineBorder, marginBorder);
    }
}
