package com.ttpc;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TicketIssuingWindow extends JFrame implements ActionListener, MouseListener {

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
//    JSeparator hSeparator = new JSeparator(JSeparator.HORIZONTAL);

    TicketIssuingWindow(){
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(430, 550);
        this.setTitle("Train Ticket Price Calculator");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);
        this.add(stationPanel);
        this.add(classPanel);
        this.add(amountPanel);
        this.add(buttonPanel);

        Border marginBorder = BorderFactory.createEmptyBorder(10,10,10,10);
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK,2);
        Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, marginBorder);

        //destination label and station list combobox
        stationPanel.setBounds(30,20,360,100);
        stationPanel.setBackground(Color.WHITE);
        stationPanel.setBorder(compoundBorder);
        stationPanel.setLayout(new GridLayout(2,1,10,10));
        stationPanel.add(destinationLabel);
        stationPanel.add(stationComboBox);
//        stationPanel.add(hSeparator);

        destinationLabel.setText("Destination");
        destinationLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        destinationLabel.setForeground(Color.BLACK);

        String filepath = "src/res/stationDetails.csv";
        StationDatabaseReader csvReader = new StationDatabaseReader();
        for (StationDetails stationDetails : csvReader.readCSV(filepath)){
            stationComboBox.addItem(stationDetails.getStationName());
        }
        stationComboBox.setFont(new Font("Roboto", Font.PLAIN, 14));
        stationComboBox.addMouseListener(this);

        //class label and three radio buttons
        classPanel.setBounds(30,140,360,140);
        classPanel.setBackground(Color.WHITE);
        classPanel.setBorder(compoundBorder);
        classPanel.setLayout(new GridLayout(4,1,10,10));
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
        firstClassRadioButton.setOpaque(false);
        firstClassRadioButton.addMouseListener(this);

        secondClassRadioButton.setText("Second Class");
        secondClassRadioButton.setFont(new Font("Roboto", Font.PLAIN, 14));
        secondClassRadioButton.setOpaque(false);
        secondClassRadioButton.addMouseListener(this);

        thirdClassRadioButton.setText("Third Class");
        thirdClassRadioButton.setFont(new Font("Roboto", Font.PLAIN, 14));
        thirdClassRadioButton.setOpaque(false);
        thirdClassRadioButton.setSelected(true);
        thirdClassRadioButton.addMouseListener(this);

        classRadioButtons.add(firstClassRadioButton);
        classRadioButtons.add(secondClassRadioButton);
        classRadioButtons.add(thirdClassRadioButton);

        //amount label and half and full labels and textFields
        amountPanel.setBounds(30, 300, 360,120);
        amountPanel.setBackground(Color.WHITE);
        amountPanel.setBorder(compoundBorder);
        amountPanel.setLayout(new GridLayout(3,1));
        amountPanel.add(amountLabel);
        amountPanel.add(halfFullAmountLabelPanel);
        amountPanel.add(halfFullAmountTextFieldPanel);
//        amountPanel.add(hSeparator);

        amountLabel.setText("Amount");
        amountLabel.setFont(new Font("Roboto", Font.BOLD, 16));

        halfFullAmountLabelPanel.setBounds(30, 300, 360,100);
        halfFullAmountLabelPanel.setBackground(Color.WHITE);
        halfFullAmountLabelPanel.setLayout(new GridLayout(1,2,10,10));

        halfFullAmountLabelPanel.add(halfAmountLabel);
        halfFullAmountLabelPanel.add(fullAmountLabel);

        halfAmountLabel.setText("Half");
        halfAmountLabel.setFont(new Font("Roboto", Font.BOLD, 14));
        fullAmountLabel.setText("Full");
        fullAmountLabel.setFont(new Font("Roboto", Font.BOLD, 14));

        halfFullAmountTextFieldPanel.setBounds(30, 340, 360,100);
        halfFullAmountTextFieldPanel.setBackground(Color.WHITE);
        halfFullAmountTextFieldPanel.setLayout(new GridLayout(1,2,10,10));

        halfFullAmountTextFieldPanel.add(halfAmountTextField);
        halfFullAmountTextFieldPanel.add(fullAmountTextField);

        halfAmountTextField.setText("0");
        halfAmountTextField.setFont(new Font("Roboto", Font.PLAIN, 16));
        halfAmountTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        fullAmountTextField.setText("1");
        fullAmountTextField.setFont(new Font("Roboto", Font.PLAIN, 16));
        fullAmountTextField.setHorizontalAlignment(SwingConstants.RIGHT);

        //back button and proceed button
        buttonPanel.setBounds(30, 450, 360,40);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new GridLayout(1,2,10,10));
        buttonPanel.add(backButton);
        buttonPanel.add(proceedButton);

        backButton.setText("Back");
        backButton.setFont(new Font("Roboto", Font.BOLD, 15));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
        backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        backButton.setFocusable(false);
        backButton.addActionListener(this);
        backButton.addMouseListener(this);

        proceedButton.setText("Proceed");
        proceedButton.setFont(new Font("Roboto", Font.BOLD, 15));
        proceedButton.setForeground(Color.WHITE);
        proceedButton.setBackground(Color.BLACK);
        proceedButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        proceedButton.setFocusable(false);
        proceedButton.addActionListener(this);
        proceedButton.addMouseListener(this);

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

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == backButton){
            backButton.setForeground(Color.BLACK);
            backButton.setBackground(Color.WHITE);
            backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else if (e.getSource() == proceedButton) {
            proceedButton.setForeground(Color.BLACK);
            proceedButton.setBackground(Color.WHITE);
            proceedButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }else{
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == backButton){
            backButton.setForeground(Color.WHITE);
            backButton.setBackground(Color.BLACK);
            backButton.setCursor(Cursor.getDefaultCursor());
        } else if (e.getSource() == proceedButton) {
            proceedButton.setForeground(Color.WHITE);
            proceedButton.setBackground(Color.BLACK);
            proceedButton.setCursor(Cursor.getDefaultCursor());
        }else{
            setCursor(Cursor.getDefaultCursor());
        }
    }
}
