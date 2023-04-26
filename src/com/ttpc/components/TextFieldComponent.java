package com.ttpc.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TextFieldComponent extends JTextField {
    public TextFieldComponent(int fontSize, boolean isEditable){
        setFont(new Font("Roboto", Font.BOLD, fontSize));
        setHorizontalAlignment(SwingConstants.RIGHT);
        setForeground(Color.BLACK);
        setEditable(isEditable);
    }

    public TextFieldComponent(String label, int fontSize, boolean isEditable, int columns){
        super(label, columns);
        setFont(new Font("Roboto", Font.BOLD, fontSize));
        setHorizontalAlignment(SwingConstants.RIGHT);
        setForeground(Color.BLACK);
        setEditable(isEditable);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))){
                    e.consume();
                }
            }
        });
    }
}
