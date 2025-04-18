package com.example.gui;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.example.jsonObjects.RozvrhovaAkce;

public class RozvrhTableModel extends DefaultTableModel {
    
    public RozvrhTableModel(String[][] rows, String[] columns) {
        super(rows, columns);        
    }

    public void loadRozvrhoveAkce(List<RozvrhovaAkce> rozvrhoveAkce) {
        String[][] data = new String[6][getColumnCount() - 1];
        
        data[0][0] = "PO";
        data[1][0] = "ÚT";
        data[2][0] = "ST";
        data[3][0] = "ČT";
        data[4][0] = "PÁ";
        data[5][0] = "SO";

        for (RozvrhovaAkce rozvrhovaAkce : rozvrhoveAkce) {
            data[dayNumber(rozvrhovaAkce.getDen())][timeNumber(rozvrhovaAkce.getHodinaSkutOd().toString())] = rozvrhovaAkce.getPredmet();
        }

        for (String[] row : data) {
            addRow(row);
        }
    }

    private int timeNumber(String time) {
        for (int i = 0; i < getColumnCount(); i++) {
            System.out.println(time + " dd:" + getColumnName(i));
            if(getColumnName(i).equalsIgnoreCase(time)) {
                System.out.println(time + " done");
                return i;
            }
        }
        return -1;
    }

    private int dayNumber(String day) {
        switch (day) {
            case "Pondělí":
                return 0;
            case "Úterý":
                return 1;
            case "Středa":
                return 2;
            case "Čtvrtek":
                return 3;
            case "Pátek":
                return 4;
            case "Sobota":
                return 5;
            default:
                return -1;
        }
    }
}
