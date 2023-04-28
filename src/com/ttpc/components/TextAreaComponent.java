package com.ttpc.components;

import com.ttpc.TotalTicketsWindow;

import javax.swing.JTextArea;
import java.awt.Insets;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextAreaComponent extends JTextArea {

    public TextAreaComponent(int columns, int rows){
        setColumns(columns);
        setRows(rows);

        Insets marginTextArea = new Insets(10, 10, 0, 0);
        setMargin(marginTextArea);
        setFont(new Font("Roboto", Font.PLAIN, 14));
        setEditable(false);
    }

    public void addText(String filepath){
        //show text file
        try {
            File ticketsFile = new File(filepath);
            BufferedReader br = new BufferedReader(new FileReader(ticketsFile));

            String line;
            while ((line = br.readLine()) != null) {
                append(line + "\n");
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
//            JOptionPane.showMessageDialog(new TotalTicketsWindow(), "An error occurred: " + e.getMessage(), "TTPC", JOptionPane.ERROR_MESSAGE);
        }
    }



}
