package com.ttpc.components;

import com.ttpc.interfaces.FrameCreatable;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import java.awt.Color;

public abstract class FrameComponent extends JFrame implements FrameCreatable {
    public FrameComponent(String label) {
        super(label);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.GRAY);

        //add icon to frame
        String iconPath = "src/res/ttpc.png";
        ImageIcon icon = new ImageIcon(iconPath);
        setIconImage(icon.getImage());
    }

    public abstract void prepareGUI(int width, int height);
}
