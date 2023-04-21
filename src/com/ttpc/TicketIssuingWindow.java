package com.ttpc;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TicketIssuingWindow extends JFrame implements ActionListener {

    JPanel stationPanel = new JPanel();
    JPanel classPanel = new JPanel();
    JPanel amountPanel = new JPanel();
    JPanel halfFullAmountLabelPanel = new JPanel();
    JPanel halfFullAmountTextFieldPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel destinationLabel = new JLabel();
    JComboBox<String> stationComboBox = new JComboBox<>();
    JLabel classLabel = new JLabel();
    JRadioButton firstClassRadioButton = new JRadioButton();
    JRadioButton secondClassRadioButton = new JRadioButton();
    JRadioButton thirdClassRadioButton = new JRadioButton();
    ButtonGroup classRadioButtons = new ButtonGroup();
    JLabel amountLabel = new JLabel();
    JLabel halfAmountLabel = new JLabel();
    JLabel fullAmountLabel = new JLabel();
    JTextField halfAmountTextField = new JTextField();
    JTextField fullAmountTextField = new JTextField();
    ButtonComponent startButton = new ButtonComponent("Start");
    ButtonComponent totalButton = new ButtonComponent("Total");
    ButtonComponent helpButton = new ButtonComponent("Help");
    ButtonComponent aboutButton = new ButtonComponent("About");
    ArrayList<String> stationNames = new ArrayList<>();
    ArrayList<Double> distances = new ArrayList<>();
    ArrayList<Double> firstClassPrices = new ArrayList<>();
    ArrayList<Double> secondClassPrices = new ArrayList<>();
    ArrayList<Double> thirdClassPrices = new ArrayList<>();

//    JSeparator hSeparator = new JSeparator(JSeparator.HORIZONTAL);

    TicketIssuingWindow() {
        this.setResizable(false);
        this.setSize(430, 580);
        this.setTitle("Train Ticket Price Calculator");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.GRAY);
        this.add(stationPanel);
        this.add(classPanel);
        this.add(amountPanel);
        this.add(buttonPanel);

        Border marginBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, marginBorder);

        //destination label and station list combobox
        stationPanel.setBounds(30, 20, 360, 100);
        stationPanel.setBackground(Color.GRAY);
        stationPanel.setBorder(compoundBorder);
        stationPanel.setLayout(new GridLayout(2, 1, 10, 10));
        stationPanel.add(destinationLabel);
        stationPanel.add(stationComboBox);
//        stationPanel.add(hSeparator);

        destinationLabel.setText("Destination");
        destinationLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        destinationLabel.setForeground(Color.BLACK);

        String filepath = "src/res/stationDetails.csv";
        StationDatabaseReader csvReader = new StationDatabaseReader();
        for (StationDetails stationDetails : csvReader.readCSV(filepath)) {
            stationComboBox.addItem(stationDetails.getStationName());
            stationNames.add(stationDetails.getStationName());
            distances.add(stationDetails.getDistance());
            firstClassPrices.add(stationDetails.getFirstClassPrice());
            secondClassPrices.add(stationDetails.getSecondClassPrice());
            thirdClassPrices.add(stationDetails.getThirdClassPrice());
        }


        stationComboBox.setFont(new Font("Roboto", Font.PLAIN, 15));
        stationComboBox.setBackground(Color.WHITE);
        stationComboBox.setFocusable(false);
        stationComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //class label and three radio buttons
        classPanel.setBounds(30, 140, 360, 140);
        classPanel.setBackground(Color.GRAY);
        classPanel.setBorder(compoundBorder);
        classPanel.setLayout(new GridLayout(4, 1, 10, 10));
        classPanel.add(classLabel);
        classPanel.add(firstClassRadioButton);
        classPanel.add(secondClassRadioButton);
        classPanel.add(thirdClassRadioButton);
//        classPanel.add(hSeparator);

        classLabel.setText("Class");
        classLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        classLabel.setForeground(Color.BLACK);

        firstClassRadioButton.setText("First Class");
        firstClassRadioButton.setFont(new Font("Roboto", Font.PLAIN, 14));
        firstClassRadioButton.setForeground(Color.BLACK);
        firstClassRadioButton.setFocusable(false);
        firstClassRadioButton.setOpaque(false);
        firstClassRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        secondClassRadioButton.setText("Second Class");
        secondClassRadioButton.setFont(new Font("Roboto", Font.PLAIN, 14));
        secondClassRadioButton.setForeground(Color.BLACK);
        secondClassRadioButton.setFocusable(false);
        secondClassRadioButton.setOpaque(false);
        secondClassRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        thirdClassRadioButton.setText("Third Class");
        thirdClassRadioButton.setFont(new Font("Roboto", Font.PLAIN, 14));
        thirdClassRadioButton.setForeground(Color.BLACK);
        thirdClassRadioButton.setFocusable(false);
        thirdClassRadioButton.setOpaque(false);
        thirdClassRadioButton.setSelected(true);
        thirdClassRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        classRadioButtons.add(firstClassRadioButton);
        classRadioButtons.add(secondClassRadioButton);
        classRadioButtons.add(thirdClassRadioButton);

        //amount label and half and full labels and textFields
        amountPanel.setBounds(30, 300, 360, 120);
        amountPanel.setBackground(Color.GRAY);
        amountPanel.setBorder(compoundBorder);
        amountPanel.setLayout(new GridLayout(3, 1));
        amountPanel.add(amountLabel);
        amountPanel.add(halfFullAmountLabelPanel);
        amountPanel.add(halfFullAmountTextFieldPanel);
//        amountPanel.add(hSeparator);

        amountLabel.setText("Amount");
        amountLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        amountLabel.setForeground(Color.BLACK);

        halfFullAmountLabelPanel.setBounds(30, 300, 360, 100);
        halfFullAmountLabelPanel.setBackground(Color.GRAY);
        halfFullAmountLabelPanel.setLayout(new GridLayout(1, 2, 10, 10));

        halfFullAmountLabelPanel.add(halfAmountLabel);
        halfFullAmountLabelPanel.add(fullAmountLabel);

        halfAmountLabel.setText("Half");
        halfAmountLabel.setFont(new Font("Roboto", Font.BOLD, 14));
        halfAmountLabel.setForeground(Color.BLACK);
        fullAmountLabel.setText("Full");
        fullAmountLabel.setForeground(Color.BLACK);
        fullAmountLabel.setFont(new Font("Roboto", Font.BOLD, 14));

        halfFullAmountTextFieldPanel.setBounds(30, 340, 360, 100);
        halfFullAmountTextFieldPanel.setBackground(Color.GRAY);
        halfFullAmountTextFieldPanel.setLayout(new GridLayout(1, 2, 10, 10));

        halfFullAmountTextFieldPanel.add(halfAmountTextField);
        halfFullAmountTextFieldPanel.add(fullAmountTextField);

        halfAmountTextField.setText("0");
        halfAmountTextField.setFont(new Font("Roboto", Font.PLAIN, 16));
        halfAmountTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        halfAmountTextField.setForeground(Color.BLACK);

        fullAmountTextField.setText("1");
        fullAmountTextField.setFont(new Font("Roboto", Font.PLAIN, 16));
        fullAmountTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        fullAmountTextField.setForeground(Color.BLACK);

        //back button and proceed button
        buttonPanel.setBounds(30, 450, 360, 80);
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));
        buttonPanel.add(startButton);
        buttonPanel.add(totalButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(aboutButton);

        startButton.addActionListener(this);
        totalButton.addActionListener(this);
        helpButton.addActionListener(this);
        aboutButton.addActionListener(this);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            int destinationIndex = stationComboBox.getSelectedIndex();
            double distance = distances.get(destinationIndex) ;
            String stationName = stationNames.get(destinationIndex);
            int halfTicketsAmount = Integer.parseInt(halfAmountTextField.getText());
            int fullTicketsAmount = Integer.parseInt(fullAmountTextField.getText());

            int trainClass = 0;
            double ticketPrice = 0;

            if (firstClassRadioButton.isSelected()){
                trainClass = 1;
                ticketPrice = firstClassPrices.get(destinationIndex);
            } else if (secondClassRadioButton.isSelected()) {
                trainClass = 2;
                ticketPrice = secondClassPrices.get(destinationIndex);
            } else if (thirdClassRadioButton.isSelected()) {
                trainClass = 3;
                ticketPrice = thirdClassPrices.get(destinationIndex);
            }

            PriceCalculator priceCalculator = new PriceCalculator(stationName,distance,ticketPrice, halfTicketsAmount, fullTicketsAmount, trainClass);
            priceCalculator.ticketDetailsSaver();

            stationComboBox.setSelectedIndex(0);
            halfAmountTextField.setText("0");
            fullAmountTextField.setText("1");
            thirdClassRadioButton.setSelected(true);

            JOptionPane.showMessageDialog(this, priceCalculator, "TTPC", JOptionPane.INFORMATION_MESSAGE);

        } else if (e.getSource() == totalButton) {
            String filepath = "src/res/tickets.txt";
            File file = new File(filepath);
            if(file.exists()){
                this.dispose();
                new TotalTicketsWindow();
            }else{
                JOptionPane.showMessageDialog(this, "Issued tickets file does not exist.", "TTPC", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == helpButton) {
            JOptionPane.showMessageDialog(this, "This is help", "TTPC", JOptionPane.INFORMATION_MESSAGE);

        } else if (e.getSource() == aboutButton) {
            JOptionPane.showMessageDialog(this, "This is about", "TTPC", JOptionPane.INFORMATION_MESSAGE);

        }


    }

}
