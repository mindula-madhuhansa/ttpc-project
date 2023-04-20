package com.ttpc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonComponent extends JButton {
    public ButtonComponent(String label) {
        super(label);
        setFont(new Font("Roboto", Font.BOLD, 15));
        setForeground(Color.WHITE);
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setFocusable(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(Color.BLACK);
                setBackground(Color.WHITE);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(Color.WHITE);
                setBackground(Color.BLACK);
                setCursor(Cursor.getDefaultCursor());
            }
        });
    }


}
