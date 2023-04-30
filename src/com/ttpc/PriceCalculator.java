package com.ttpc;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PriceCalculator {
    private final String endStationName;
    private final double distance;
    private final double ticketPrice;
    private final int halfTicketsAmount;
    private final int fullTicketsAmount;
    private final int ticketClass;
    LocalDateTime localDateTime = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public PriceCalculator(String endStationName, double distance, double ticketPrice, int halfTicketsAmount, int fullTicketsAmount, int ticketClass) {
        this.endStationName = endStationName;
        this.distance = distance;
        this.ticketPrice = ticketPrice;
        this.halfTicketsAmount = halfTicketsAmount;
        this.fullTicketsAmount = fullTicketsAmount;
        this.ticketClass = ticketClass;
    }

    public String getEndStationName() {
        return endStationName;
    }

    public double getDistance() {
        return distance;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public int getHalfTicketsAmount() {
        return halfTicketsAmount;
    }

    public int getFullTicketsAmount() {
        return fullTicketsAmount;
    }

    public int getTicketClass() {
        return ticketClass;
    }

    private double ticketQtyCalculate(int fullTicketsAmount, int halfTicketsAmount){
        return fullTicketsAmount + halfTicketsAmount * 0.5;
    }

    private double ticketPriceCalculate(double ticketQ, double price){
        return ticketQ * price;
    }

    public void ticketDetailsSaver() {
        try {
            String filePath = "src/res/tickets.txt";
            File ticketsFile = new File(filePath);
            FileWriter ticketSaver = new FileWriter(ticketsFile, true);
            ticketSaver.write("Issued date: " + localDateTime.format(dateTimeFormatter) + "\n");
            ticketSaver.write(this.toString());
            ticketSaver.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(new TicketIssuingWindow(), "An error occurred: " + e.getMessage(), "TTPC", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public String toString() {
        String startStationName = "Colombo Fort";
        double ticketQty = ticketQtyCalculate(fullTicketsAmount, halfTicketsAmount);
        double finalTicketPrice = ticketPriceCalculate(ticketQty, ticketPrice);
        return "From: " + startStationName + "\n" +
                "To: " + endStationName + "\n" +
                "Trip Distance: " + distance + "km" + "\n" +
                "Class: " + ticketClass + "\n" +
                "Ticket Qty: " + ticketQty + "\n" +
                "Total Price: " + "LKR " + finalTicketPrice + "\n\n" +
                "------------------------------------------\n\n";
    }

}
