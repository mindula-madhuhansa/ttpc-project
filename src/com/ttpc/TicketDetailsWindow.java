package com.ttpc;

import com.ttpc.components.ButtonComponent;
import com.ttpc.components.LabelComponent;
import com.ttpc.components.TextFieldComponent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketDetailsWindow extends JFrame implements ActionListener {

    JPanel ticketDetailsPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    LabelComponent startLabel = new LabelComponent("From:", 14);
    TextFieldComponent startTextField = new TextFieldComponent(14,false);
    LabelComponent destinationLabel = new LabelComponent("To:", 14);
    TextFieldComponent destinationTextField = new TextFieldComponent(14,false);
    LabelComponent distanceLabel = new LabelComponent("Distance:", 14);
    TextFieldComponent distanceTextField = new TextFieldComponent(14,false);
    LabelComponent classLabel = new LabelComponent("Class:", 14);
    TextFieldComponent classTextField = new TextFieldComponent(14,false);
    LabelComponent ticketQtyLabel = new LabelComponent("Qty:", 14);
    TextFieldComponent ticketQtyTextField = new TextFieldComponent(14,false);
    LabelComponent totalPriceLabel = new LabelComponent("Total:", 14);
    TextFieldComponent totalPriceTextField = new TextFieldComponent(14,false);
    ButtonComponent backButton = new ButtonComponent("Back");
    ButtonComponent printButton = new ButtonComponent("Print");
    PriceCalculator priceCalculator;

    TicketDetailsWindow(PriceCalculator priceCalculator){
        this.priceCalculator = priceCalculator;

        this.setResizable(false);
        this.setSize(360, 360);
        this.setTitle("Train Ticket Price Calculator");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.GRAY);
        this.add(ticketDetailsPanel);
        this.add(buttonPanel);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //add icon to frame
        String iconPath = "src/res/ttpc.png";
        ImageIcon icon = new ImageIcon(iconPath);
        this.setIconImage(icon.getImage());

        //add custom border
        Border marginBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, marginBorder);

        ticketDetailsPanel.setBounds(24,20,300,240);
        ticketDetailsPanel.setBackground(Color.GRAY);
        ticketDetailsPanel.setBorder(compoundBorder);
        ticketDetailsPanel.setLayout(new GridLayout(6, 2, 10, 10));
        ticketDetailsPanel.add(startLabel);
        ticketDetailsPanel.add(startTextField);
        ticketDetailsPanel.add(destinationLabel);
        ticketDetailsPanel.add(destinationTextField);
        ticketDetailsPanel.add(distanceLabel);
        ticketDetailsPanel.add(distanceTextField);
        ticketDetailsPanel.add(classLabel);
        ticketDetailsPanel.add(classTextField);
        ticketDetailsPanel.add(ticketQtyLabel);
        ticketDetailsPanel.add(ticketQtyTextField);
        ticketDetailsPanel.add(totalPriceLabel);
        ticketDetailsPanel.add(totalPriceTextField);

        double ticketQty = priceCalculator.fullTicketsAmount + priceCalculator.halfTicketsAmount*0.5;
        double ticketPrice = ticketQty * priceCalculator.ticketPrice;
        //add textFields
        startTextField.setText("Colombo Fort");
        destinationTextField.setText(priceCalculator.stationName);
        distanceTextField.setText(String.valueOf(priceCalculator.distance));
        classTextField.setText(String.valueOf(priceCalculator.ticketClass));
        ticketQtyTextField.setText(String.valueOf(ticketQty));
        totalPriceTextField.setText(String.valueOf(ticketPrice));

        buttonPanel.setBounds(24, 270, 300, 40);
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
        buttonPanel.add(backButton);
        buttonPanel.add(printButton);

        backButton.addActionListener(this);
        printButton.addActionListener(this);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton){
            this.dispose();
        }
    }
}
