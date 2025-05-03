package com.example.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.example.jsonObjects.RozvrhovaAkce;

public class ListRozvrhTableModel extends AbstractTableModel {

    private String[] columnNames = {"Zkratka", "Název", "Učitel", "Den", "Od", "Do", "c"};
    private List<RozvrhovaAkce> rozvrhoveAkce = new ArrayList<>();

    public ListRozvrhTableModel() {

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RozvrhovaAkce ra = rozvrhoveAkce.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ra.getPredmet() != null ? ra.getPredmet() : "";
            case 1:
                return ra.getNazev() != null ? ra.getNazev() : "";
            case 2:
                return ra.getUcitel() != null ? ra.getUcitel() : "";
            case 3:
                return ra.getDen() != null ? ra.getDen() : "";
            case 4:
                return ra.getHodinaSkutOd() != null ? ra.getHodinaSkutOd().getValue() : "";
            case 5:
                return ra.getHodinaSkutDo() != null ? ra.getHodinaSkutDo().getValue() : "";
            case 6:
                if(ra.getTypAkce().equals("Přednáška")) {
                    return "46495b";
                } else if(ra.getTypAkce().equals("Cvičení")) {
                    return "46594b";
                }
                return "56494b";
            default:
                return null;
        }
    }

    public void setRozvrhoveAkce(List<RozvrhovaAkce> rozvrhoveAkce) {
        this.rozvrhoveAkce = rozvrhoveAkce;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return rozvrhoveAkce.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
