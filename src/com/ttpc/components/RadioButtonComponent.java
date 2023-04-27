package com.ttpc.components;

import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;

public class RadioButtonComponent extends JRadioButton {
    public RadioButtonComponent(String label, boolean isEnabled){
        super(label);
        setFont(new Font("Roboto", Font.BOLD, 14));
        setFocusable(false);
        setOpaque(false);
        setEnabled(isEnabled);
        setForeground(Color.BLACK);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
