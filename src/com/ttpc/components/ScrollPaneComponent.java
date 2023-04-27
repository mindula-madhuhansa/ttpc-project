package com.ttpc.components;

import com.ttpc.interfaces.BorderCreatable;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ScrollPaneComponent extends JScrollPane implements BorderCreatable {
    public ScrollPaneComponent(boolean isBorder, TextAreaComponent textAreaComponent){
        setBackground(Color.GRAY);
        setViewportView(textAreaComponent);
        if (isBorder){
            setBorder(addBorder());
        }
    }

    @Override
    //creating a custom border
    public Border addBorder(){
        Border marginBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        return BorderFactory.createCompoundBorder(lineBorder, marginBorder);
    }
}
