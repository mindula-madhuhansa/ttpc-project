package com.ttpc.components;

import javax.swing.*;
import java.awt.*;

public class TextFieldComponent extends JTextField {
    public TextFieldComponent(int fontSize, boolean isEditable){
        setFont(new Font("Roboto", Font.BOLD, fontSize));
        setHorizontalAlignment(SwingConstants.RIGHT);
        setForeground(Color.BLACK);
        setEditable(isEditable);
    }

    public TextFieldComponent(String label, int fontSize, boolean isEditable){
        super(label);
        setFont(new Font("Roboto", Font.BOLD, fontSize));
        setHorizontalAlignment(SwingConstants.RIGHT);
        setForeground(Color.BLACK);
        setEditable(isEditable);
    }
}
