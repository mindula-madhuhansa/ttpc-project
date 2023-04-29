package com.ttpc;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class FIlePrinter {
    public FIlePrinter(){
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        if (printerJob.printDialog()) {
            try {
                printerJob.print();
            } catch (PrinterException pe) {
                pe.printStackTrace();
            }
        }
    }
}
