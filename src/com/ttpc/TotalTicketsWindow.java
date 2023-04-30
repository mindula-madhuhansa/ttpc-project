package com.ttpc;

import com.ttpc.components.FrameComponent;
import com.ttpc.components.PanelComponent;
import com.ttpc.components.TextAreaComponent;
import com.ttpc.components.ScrollPaneComponent;
import com.ttpc.components.ButtonComponent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class TotalTicketsWindow extends FrameComponent implements ActionListener {
    PanelComponent buttonPanel = new PanelComponent(false);
    TextAreaComponent ticketDetailsTextArea = new TextAreaComponent(20, 1);
    ScrollPaneComponent textAreaScrollPane = new ScrollPaneComponent(true, ticketDetailsTextArea);
    ButtonComponent backButton = new ButtonComponent("Back");
    ButtonComponent clearButton = new ButtonComponent("Clear");

    TotalTicketsWindow() {
        super("Train Ticket Price Calculator");
        prepareGUI(400, 600);
    }

    @Override
    public void prepareGUI(int width, int height) {
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(textAreaScrollPane);
        this.add(buttonPanel);

        //add scroll pane
        textAreaScrollPane.setBounds(30, 20, 330, 470);

        //set file location and display it
        ticketDetailsTextArea.addText("src/res/tickets.txt");
        ticketDetailsTextArea.setCaretPosition(0);

        //add buttons
        buttonPanel.setBounds(30, 510, 330, 40);
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
