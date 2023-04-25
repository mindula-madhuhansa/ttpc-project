package com.ttpc;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PriceCalculator {
    private final String stationName;
    private final double distance;
    private final double ticketPrice;
    private final int halfTicketsAmount;
    private final int fullTicketsAmount;
    private final int ticketClass;
    LocalDateTime localDateTime = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public PriceCalculator(String stationName, double distance, double ticketPrice, int halfTicketsAmount, int fullTicketsAmount, int ticketClass) {
        this.stationName = stationName;
        this.distance = distance;
        this.ticketPrice = ticketPrice;
        this.halfTicketsAmount = halfTicketsAmount;
        this.fullTicketsAmount = fullTicketsAmount;
        this.ticketClass = ticketClass;
    }

    public String getStationName() {
        return stationName;
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
        double ticketQty = fullTicketsAmount + halfTicketsAmount * 0.5;
        double finalTicketPrice = (ticketQty) * ticketPrice;
        return "From: Colombo Fort\n" +
                "To: " + stationName + "\n" +
                "Trip Distance: " + distance + "km" + "\n" +
                "Class: " + ticketClass + "\n" +
                "Ticket Qty: " + ticketQty + "\n" +
                "Total Price: " + "LKR " + finalTicketPrice + "\n\n" +
                "--------------------------------------------\n\n";
    }

}
