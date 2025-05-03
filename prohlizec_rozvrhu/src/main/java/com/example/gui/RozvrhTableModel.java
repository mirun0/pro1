package com.example.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.example.TimeComparator;
import com.example.jsonObjects.RozvrhovaAkce;

public class RozvrhTableModel extends AbstractTableModel {

    private RozvrhovaAkce[][] data = {};
    private String[] columns = {"", "07:25", "08:15", "09:05", "09:55", "10:45", "11:35", "12:25", 
    "13:15", "14:05", "14:55", "15:45", "16:35", "17:25", "18:15", "19:05", "19:55"};
    private List<Span> spans = new ArrayList<>();
    private TimeComparator timeComparator = new TimeComparator();
    
    public RozvrhTableModel() {
        initData();
    }

    private void initData() {
        data = new RozvrhovaAkce[6][getColumnCount()];
        spans.clear();
    }

    public void loadRozvrhoveAkce(List<RozvrhovaAkce> rozvrhoveAkce) {
        initData();
        List<String> columnsList = Arrays.asList(columns);

        for (RozvrhovaAkce rozvrhovaAkce : rozvrhoveAkce) {
            if(columnsList.contains(rozvrhovaAkce.getHodinaSkutOd().toString())) {
                int size = checkSpanSize(rozvrhovaAkce);
                if(size > 1) {
                    spans.add(new Span(dayNumber(rozvrhovaAkce.getDen()), timeNumber(rozvrhovaAkce.getHodinaSkutOd().toString()), size));
                }
                data[dayNumber(rozvrhovaAkce.getDen())][timeNumber(rozvrhovaAkce.getHodinaSkutOd().toString())] = rozvrhovaAkce;
            }
        }
        fireTableDataChanged();
    }

    private int checkSpanSize(RozvrhovaAkce rozvrhovaAkce) {
        int i = 0;
        String timeDo = rozvrhovaAkce.getHodinaSkutDo().toString();
        String timeOd = rozvrhovaAkce.getHodinaSkutOd().toString();

        while (timeComparator.compare(timeDo, columns[timeNumber(timeOd) + i]) > 0) {
            i++;
            if(timeNumber(timeOd) + i == columns.length) {
                break;
            }
        }
        return i;
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

    @Override
    public int getRowCount() {
        return 6;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int index) {
        return columns[index];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0) {
            switch (rowIndex) {
                case 0: return "PO";
                case 1: return "ÚT";
                case 2: return "ST";
                case 3: return "ČT";
                case 4: return "PÁ";
                case 5: return "SO";
            }
        }
        if(data[rowIndex][columnIndex] != null) {
            return data[rowIndex][columnIndex].getPredmet();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public RozvrhovaAkce[][] getData() {
        return data;
    }

    public List<Span> getSpans() {
        return spans;
    }
}
