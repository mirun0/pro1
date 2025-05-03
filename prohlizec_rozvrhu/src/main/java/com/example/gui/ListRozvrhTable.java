package com.example.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ListRozvrhTable extends JTable {

    public ListRozvrhTable(ListRozvrhTableModel tableModel) {
        super(tableModel);
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        if (!isRowSelected(row)) {
            Color baseColor = Color.decode("#" + (String)this.getValueAt(row, convertColumnIndexToModel(6)));
            c.setBackground(baseColor);
        } else {
            c.setBackground(getSelectionBackground());
        }

        this.setOpaque(true);
        return c;
    }
}
