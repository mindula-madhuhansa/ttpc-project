package com.ttpc;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.attribute.PosixFilePermission;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

public class PriceCalculator {
    public String stationName;
    public double distance;
    public double ticketPrice;
    public int halfTicketsAmount;
    public int fullTicketsAmount;
    public int ticketClass;
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

    public void ticketDetailsSaver(){
        try{
            String filePath = "src/res/tickets.txt";
            File ticketsFile = new File(filePath);
            FileWriter ticketSaver = new FileWriter(ticketsFile,true);
            ticketSaver.write("Issued date: " + localDateTime.format(dateTimeFormatter) + "\n");
            ticketSaver.write(this.toString());
            ticketSaver.close();

        }catch (IOException e){
            JOptionPane.showMessageDialog(new TicketIssuingWindow(), "An error occurred: " + e.getMessage(), "TTPC", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public String toString(){
        double finalTicketPrice = fullTicketsAmount * ticketPrice + (halfTicketsAmount * ticketPrice) * 0.5;
        return "From: Colombo Fort\n" +
                "To: " + stationName + "\n" +
                "Trip Distance: " + distance + "km" + "\n" +
                "Class: " + ticketClass + "\n" +
                "Total Price: " + "LKR " + finalTicketPrice + "\n\n";
    }

}
