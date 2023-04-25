package com.ttpc;

import com.ttpc.components.ButtonComponent;
import com.ttpc.components.FrameComponent;
import com.ttpc.components.LabelComponent;
import com.ttpc.components.TextFieldComponent;
import com.ttpc.interfaces.WindowGUICreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketDetailsWindow extends FrameComponent implements ActionListener, WindowGUICreator {

    JPanel ticketDetailsPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    LabelComponent startLabel = new LabelComponent("From:", 14);
    TextFieldComponent startTextField = new TextFieldComponent(14, false);
    LabelComponent destinationLabel = new LabelComponent("To:", 14);
    TextFieldComponent destinationTextField = new TextFieldComponent(14, false);
    LabelComponent distanceLabel = new LabelComponent("Distance:", 14);
    TextFieldComponent distanceTextField = new TextFieldComponent(14, false);
    LabelComponent classLabel = new LabelComponent("Class:", 14);
    TextFieldComponent classTextField = new TextFieldComponent(14, false);
    LabelComponent ticketQtyLabel = new LabelComponent("Qty:", 14);
    TextFieldComponent ticketQtyTextField = new TextFieldComponent(14, false);
    LabelComponent totalPriceLabel = new LabelComponent("Total:", 14);
    TextFieldComponent totalPriceTextField = new TextFieldComponent(14, false);
    ButtonComponent okButton = new ButtonComponent("OK");
    ButtonComponent printButton = new ButtonComponent("Print");
    PriceCalculator priceCalculator;
    FrameComponent parentFrame;

    public TicketDetailsWindow(PriceCalculator priceCalculator, FrameComponent parentFrame) {
        this.priceCalculator = priceCalculator;
        this.parentFrame = parentFrame;
        prepareGUI();
    }

    public void prepareGUI() {

        this.setSize(360, 360);
        this.add(ticketDetailsPanel);
        this.add(buttonPanel);

        ticketDetailsPanel.setBounds(24, 20, 300, 240);
        ticketDetailsPanel.setBackground(Color.GRAY);
        ticketDetailsPanel.setBorder(addBorder());
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

        double ticketQty = priceCalculator.getFullTicketsAmount() + priceCalculator.getHalfTicketsAmount() * 0.5;
        double ticketPrice = ticketQty * priceCalculator.getTicketPrice();

        //add textFields
        startTextField.setText("Colombo Fort");
        destinationTextField.setText(priceCalculator.getStationName());
        distanceTextField.setText(String.valueOf(priceCalculator.getDistance()));
        classTextField.setText(String.valueOf(priceCalculator.getTicketClass()));
        ticketQtyTextField.setText(String.valueOf(ticketQty));
        totalPriceTextField.setText(String.valueOf(ticketPrice));

        buttonPanel.setBounds(24, 270, 300, 40);
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
        buttonPanel.add(okButton);
        buttonPanel.add(printButton);

        okButton.addActionListener(this);
        printButton.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            this.dispose();
            parentFrame.setEnabled(true);
        }
    }
}
