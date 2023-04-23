package com.ttpc.components;

import javax.swing.*;
import java.awt.*;

public class RadioButtonComponent extends JRadioButton {
    public RadioButtonComponent(String label, boolean isEnabled){
        super(label);
        setFont(new Font("Roboto", Font.BOLD, 12));
        setFocusable(false);
        setOpaque(false);
        setEnabled(isEnabled);
        setForeground(Color.BLACK);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
