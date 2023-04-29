package com.ttpc;

import com.ttpc.components.FrameComponent;
import com.ttpc.components.PanelComponent;
import com.ttpc.components.LabelComponent;
import com.ttpc.components.RadioButtonComponent;
import com.ttpc.components.TextFieldComponent;
import com.ttpc.components.ButtonComponent;
import com.ttpc.interfaces.FrameCreatable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TicketIssuingWindow extends FrameComponent implements ActionListener, FrameCreatable {

    PanelComponent stationPanel = new PanelComponent(true);
    PanelComponent classPanel = new PanelComponent(true);
    PanelComponent amountPanel = new PanelComponent(true);
    PanelComponent halfFullAmountLabelPanel = new PanelComponent(false);
    PanelComponent halfFullAmountTextFieldPanel = new PanelComponent(false);
    PanelComponent buttonPanel = new PanelComponent(false);
    LabelComponent destinationLabel = new LabelComponent("Destination", 16);
    JComboBox<String> stationComboBox = new JComboBox<>();
    LabelComponent classLabel = new LabelComponent("Class", 16);
    RadioButtonComponent firstClassRadioButton = new RadioButtonComponent("First Class", false);
    RadioButtonComponent secondClassRadioButton = new RadioButtonComponent("Second Class", true);
    RadioButtonComponent thirdClassRadioButton = new RadioButtonComponent("Third Class", true);
    ButtonGroup classRadioButtons = new ButtonGroup();
    LabelComponent amountLabel = new LabelComponent("Amount", 16);
    LabelComponent halfAmountLabel = new LabelComponent("Half", 14);
    LabelComponent fullAmountLabel = new LabelComponent("Full", 14);
    TextFieldComponent halfAmountTextField = new TextFieldComponent("0", 16, true,3);
    TextFieldComponent fullAmountTextField = new TextFieldComponent("1", 16, true,3);
    ButtonComponent startButton = new ButtonComponent("Pay");
    ButtonComponent totalButton = new ButtonComponent("Total");
    ButtonComponent helpButton = new ButtonComponent("Help");
    ButtonComponent aboutButton = new ButtonComponent("About");
    ArrayList<String> stationNames = new ArrayList<>();
    ArrayList<Double> distances = new ArrayList<>();
    ArrayList<Double> firstClassPrices = new ArrayList<>();
    ArrayList<Double> secondClassPrices = new ArrayList<>();
    ArrayList<Double> thirdClassPrices = new ArrayList<>();

    public TicketIssuingWindow() {
        super("Train Ticket Price Calculator");
        prepareGUI(430,580);
    }

    @Override
    public void prepareGUI(int width, int height) {
        this.setSize(width, height);
        this.add(stationPanel);
        this.add(classPanel);
        this.add(amountPanel);
        this.add(buttonPanel);

        //destination label and station list combobox
        stationPanel.setBounds(30, 20, 360, 100);
        stationPanel.setLayout(new GridLayout(2, 1, 10, 10));
        stationPanel.add(destinationLabel);
        stationPanel.add(stationComboBox);

        //add station names to combobox
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
        stationComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //class label and three radio buttons
        classPanel.setBounds(30, 140, 360, 140);
        classPanel.setLayout(new GridLayout(4, 1, 10, 10));
        classPanel.add(classLabel);
        classPanel.add(firstClassRadioButton);
        classPanel.add(secondClassRadioButton);
        classPanel.add(thirdClassRadioButton);

        //set thirdClassRadioButton selected by default
        thirdClassRadioButton.setSelected(true);

        classRadioButtons.add(firstClassRadioButton);
        classRadioButtons.add(secondClassRadioButton);
        classRadioButtons.add(thirdClassRadioButton);

        //amount label and half and full labels and textFields
        amountPanel.setBounds(30, 300, 360, 120);
        amountPanel.setLayout(new GridLayout(3, 1));
        amountPanel.add(amountLabel);
        amountPanel.add(halfFullAmountLabelPanel);
        amountPanel.add(halfFullAmountTextFieldPanel);

        halfFullAmountLabelPanel.setBounds(30, 300, 360, 100);
        halfFullAmountLabelPanel.setLayout(new GridLayout(1, 2, 10, 10));

        halfFullAmountLabelPanel.add(halfAmountLabel);
        halfFullAmountLabelPanel.add(fullAmountLabel);

        halfFullAmountTextFieldPanel.setBounds(30, 340, 360, 100);
        halfFullAmountTextFieldPanel.setLayout(new GridLayout(1, 2, 10, 10));

        halfFullAmountTextFieldPanel.add(halfAmountTextField);
        halfFullAmountTextFieldPanel.add(fullAmountTextField);

        //back button and proceed button
        buttonPanel.setBounds(30, 450, 360, 80);
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));
        buttonPanel.add(startButton);
        buttonPanel.add(totalButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(aboutButton);

        startButton.addActionListener(this);
        totalButton.addActionListener(this);
        helpButton.addActionListener(this);
        aboutButton.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (Integer.parseInt(halfAmountTextField.getText()) == 0 && Integer.parseInt(fullAmountTextField.getText()) == 0) {
                JOptionPane.showMessageDialog(this, "Both fields cannot be zero.\nPlease enter a valid value in at least one of the fields", "TTPC", JOptionPane.INFORMATION_MESSAGE);
            } else {
                this.setEnabled(false);
                int destinationIndex = stationComboBox.getSelectedIndex();
                double distance = distances.get(destinationIndex);
                String stationName = stationNames.get(destinationIndex);
                int halfTicketsAmount = Integer.parseInt(halfAmountTextField.getText());
                int fullTicketsAmount = Integer.parseInt(fullAmountTextField.getText());

                int trainClass = 0;
                double ticketPrice = 0;

                if (firstClassRadioButton.isSelected()) {
                    trainClass = 1;
                    ticketPrice = firstClassPrices.get(destinationIndex);
                } else if (secondClassRadioButton.isSelected()) {
                    trainClass = 2;
                    ticketPrice = secondClassPrices.get(destinationIndex);
                } else if (thirdClassRadioButton.isSelected()) {
                    trainClass = 3;
                    ticketPrice = thirdClassPrices.get(destinationIndex);
                }

                PriceCalculator priceCalculator = new PriceCalculator(stationName, distance, ticketPrice, halfTicketsAmount, fullTicketsAmount, trainClass);
                priceCalculator.ticketDetailsSaver();

                new TicketDetailsWindow(priceCalculator, this);

                //reset the values after ticket issued
                stationComboBox.setSelectedIndex(0);
                halfAmountTextField.setText("0");
                fullAmountTextField.setText("1");
                thirdClassRadioButton.setSelected(true);
            }


        } else if (e.getSource() == totalButton) {
            String filepath = "src/res/tickets.txt";
            File file = new File(filepath);
            if (file.exists()) {
                this.dispose();
                new TotalTicketsWindow();
            } else {
                JOptionPane.showMessageDialog(this, "Issued tickets file does not exist.", "TTPC", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == helpButton) {
            try {
                String pdfPath = "src/res/TTPC User Guide.pdf";
                File helpPdf = new File(pdfPath);
                if (helpPdf.exists()) {
                    Desktop.getDesktop().open(helpPdf);
                } else {
                    JOptionPane.showMessageDialog(this, "Help pdf does not exist", "Train Ticket Price Calculator", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == aboutButton) {
            JOptionPane.showMessageDialog(this, "<html>Train Ticket Price Calculator<sub>1.1.6v</sub></html>", "TTPC", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
