package com.ttpc;

import com.opencsv.CSVReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
    JButton backButton = new JButton();
    JButton proceedButton = new JButton();
    JSeparator hSeparator = new JSeparator(JSeparator.HORIZONTAL);

    TicketIssuingWindow(){
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(430, 500);
        this.setTitle("Train Ticket Price Calculator");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(255, 200, 184));
        this.add(stationPanel);
        this.add(classPanel);
        this.add(amountPanel);
        this.add(buttonPanel);

        //destination label and station list combobox
        stationPanel.setBounds(30,30,360,70);
        stationPanel.setBackground(new Color(255, 200, 184));
        stationPanel.setLayout(new GridLayout(2,1,10,10));
        stationPanel.add(destinationLabel);
        stationPanel.add(stationComboBox);
//        stationPanel.add(hSeparator);

        destinationLabel.setText("Destination");
        destinationLabel.setFont(new Font("Roboto", Font.BOLD, 16));

        String filepath = "src/res/stationDetails.csv";
        StationDatabaseReader csvReader = new StationDatabaseReader();
        for (StationDetails stationDetails : csvReader.readCSV(filepath)){
            stationComboBox.addItem(stationDetails.getStationName());
        }

        //class label and three radio buttons
        classPanel.setBounds(30,120,360,120);
        classPanel.setBackground(new Color(255, 200, 184));
        classPanel.setLayout(new GridLayout(4,1,10,10));
        classPanel.add(classLabel);
        classPanel.add(firstClassRadioButton);
        classPanel.add(secondClassRadioButton);
        classPanel.add(thirdClassRadioButton);
//        classPanel.add(hSeparator);

        classLabel.setText("Class");
        classLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        firstClassRadioButton.setText("First Class");
        firstClassRadioButton.setOpaque(false);
        secondClassRadioButton.setText("Second Class");
        secondClassRadioButton.setOpaque(false);
        thirdClassRadioButton.setText("Third Class");
        thirdClassRadioButton.setOpaque(false);
        thirdClassRadioButton.setSelected(true);

        classRadioButtons.add(firstClassRadioButton);
        classRadioButtons.add(secondClassRadioButton);
        classRadioButtons.add(thirdClassRadioButton);

        //amount label and half and full labels and textFields
        amountPanel.setBounds(30, 260, 360,100);
        amountPanel.setBackground(new Color(255, 200, 184));
        amountPanel.setLayout(new GridLayout(3,1,10,10));
        amountPanel.add(amountLabel);
        amountPanel.add(halfFullAmountLabelPanel);
        amountPanel.add(halfFullAmountTextFieldPanel);
//        amountPanel.add(hSeparator);

        amountLabel.setText("Amount");
        amountLabel.setFont(new Font("Roboto", Font.BOLD, 16));

        halfFullAmountLabelPanel.setBounds(30, 300, 360,100);
        halfFullAmountLabelPanel.setBackground(new Color(255, 200, 184));
        halfFullAmountLabelPanel.setLayout(new GridLayout(1,2,10,10));

        halfFullAmountLabelPanel.add(halfAmountLabel);
        halfFullAmountLabelPanel.add(fullAmountLabel);

        halfAmountLabel.setText("Half");
        halfAmountLabel.setFont(new Font("Roboto", Font.BOLD, 14));
        fullAmountLabel.setText("Full");
        fullAmountLabel.setFont(new Font("Roboto", Font.BOLD, 14));

        halfFullAmountTextFieldPanel.setBounds(30, 340, 360,100);
        halfFullAmountTextFieldPanel.setBackground(new Color(255, 200, 184));
        halfFullAmountTextFieldPanel.setLayout(new GridLayout(1,2,10,10));

        halfFullAmountTextFieldPanel.add(halfAmountTextField);
        halfFullAmountTextFieldPanel.add(fullAmountTextField);

        halfAmountTextField.setText("0");
        halfAmountTextField.setFont(new Font("Roboto", Font.PLAIN, 15));
        halfAmountTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        fullAmountTextField.setText("1");
        fullAmountTextField.setFont(new Font("Roboto", Font.PLAIN, 15));
        fullAmountTextField.setHorizontalAlignment(SwingConstants.RIGHT);

        //back button and proceed button
        buttonPanel.setBounds(30, 380, 360,40);
        buttonPanel.setBackground(new Color(255, 200, 184));
        buttonPanel.setLayout(new GridLayout(1,2,10,10));
        buttonPanel.add(backButton);
        buttonPanel.add(proceedButton);

        backButton.setText("Back");
        backButton.setFont(new Font("Roboto", Font.BOLD, 15));
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        proceedButton.setText("Proceed");
        proceedButton.setFont(new Font("Roboto", Font.BOLD, 15));
        proceedButton.setFocusable(false);
        backButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == backButton){
            dispose();
            new WelcomeWindow();
        }
        if (e.getSource() == proceedButton){

        }

    }
}
