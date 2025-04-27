package com.example.gui;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.example.jsonObjects.RozvrhovaAkce;

public class RozvrhTableModel extends DefaultTableModel {

    static String[][] data = new String[][]{};
    static String[] columns = new String[]{"", "07:25", "08:15", "09:05", "09:55", "10:45", "11:35", "12:25", 
    "13:15", "14:05", "14:55", "15:45", "16:35", "17:25", "18:15", "19:05", "19:55"};
    
    public RozvrhTableModel() {
        super(data, columns);  
    }

    public void loadRozvrhoveAkce(List<RozvrhovaAkce> rozvrhoveAkce) {
        String[][] data = new String[6][getColumnCount() - 1];
        
        data[0][0] = "PO";
        data[1][0] = "ÚT";
        data[2][0] = "ST";
        data[3][0] = "ČT";
        data[4][0] = "PÁ";
        data[5][0] = "SO";

        List<String> columnsList = Arrays.asList(columns);

        for (RozvrhovaAkce rozvrhovaAkce : rozvrhoveAkce) {
            if(columnsList.contains(rozvrhovaAkce.getHodinaSkutOd().toString())) {
                data[dayNumber(rozvrhovaAkce.getDen())][timeNumber(rozvrhovaAkce.getHodinaSkutOd().toString())] = rozvrhovaAkce.getPredmet();
            }
        }

        for (String[] row : data) {
            addRow(row);
        }
    }

    private int timeNumber(String time) {
        for (int i = 0; i < getColumnCount(); i++) {
            if(getColumnName(i).equalsIgnoreCase(time)) {
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
