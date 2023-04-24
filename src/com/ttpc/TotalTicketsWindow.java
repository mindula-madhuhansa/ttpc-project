package com.ttpc;

import com.ttpc.components.ButtonComponent;
import com.ttpc.components.FrameComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TotalTicketsWindow extends FrameComponent implements ActionListener, WindowGUICreator {
    JPanel buttonPanel = new JPanel();
    JTextArea ticketDetailsTextArea = new JTextArea(20, 1);
    JScrollPane textAreaScrollPane = new JScrollPane(ticketDetailsTextArea);
    ButtonComponent backButton = new ButtonComponent("Back");
    ButtonComponent clearButton = new ButtonComponent("Clear");

    TotalTicketsWindow() {
        prepareGUI();
    }

    public void prepareGUI(){
        this.setSize(400, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(textAreaScrollPane);
        this.add(buttonPanel);

        textAreaScrollPane.setBounds(30, 20, 330, 470);
        textAreaScrollPane.setBackground(Color.GRAY);
        textAreaScrollPane.setBorder(addBorder());

        //show text file
        try {
            String filepath = "src/res/tickets.txt";
            File ticketsFile = new File(filepath);
            BufferedReader br = new BufferedReader(new FileReader(ticketsFile));

            String line;
            while ((line = br.readLine()) != null) {
                ticketDetailsTextArea.append(line + "\n");
            }

            br.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new TotalTicketsWindow(), "An error occurred: " + e.getMessage(), "TTPC", JOptionPane.ERROR_MESSAGE);
        }

        Insets marginTextArea = new Insets(10, 10, 0, 0);
        ticketDetailsTextArea.setMargin(marginTextArea);
        ticketDetailsTextArea.setFont(new Font("Roboto", Font.PLAIN, 14));
        ticketDetailsTextArea.setEditable(false);
        ticketDetailsTextArea.setCaretPosition(0);

        buttonPanel.setBounds(30, 510, 330, 40);
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
        buttonPanel.add(backButton);
        buttonPanel.add(clearButton);

        backButton.addActionListener(this);
        clearButton.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            this.dispose();
            new TicketIssuingWindow();
        } else if (e.getSource() == clearButton) {
            ticketDetailsTextArea.setText("");
            String filepath = "src/res/tickets.txt";
            File file = new File(filepath);
            boolean isFileDeleted = file.delete();
            if (isFileDeleted) {
                JOptionPane.showMessageDialog(this, "File deleted successfully.", "TTPC", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "File does not exist.", "TTPC", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}
