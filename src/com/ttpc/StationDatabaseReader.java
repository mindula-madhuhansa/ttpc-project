package com.ttpc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StationDatabaseReader {
    public ArrayList<StationDetails> readCSV(String filepath) {
        ArrayList<StationDetails> stations = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                StationDetails stationDetails = new StationDetails();
                stationDetails.setStationName((values[0]));
                stationDetails.setDistance(Double.parseDouble(values[1]));
                stationDetails.setFirstClassPrice(Double.parseDouble(values[2]));
                stationDetails.setSecondClassPrice(Double.parseDouble(values[3]));
                stationDetails.setThirdClassPrice(Double.parseDouble(values[4]));
                stations.add(stationDetails);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stations;
    }

}
