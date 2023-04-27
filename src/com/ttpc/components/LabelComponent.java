package com.ttpc.components;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class LabelComponent extends JLabel {
    public LabelComponent(String label, int fontSize){
        super(label);
        setFont(new Font("Roboto", Font.BOLD, fontSize));
        setForeground(Color.BLACK);

    }
}
