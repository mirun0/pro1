package com.example.gui;

public class Span {
    private int rowIndex;
    private int columnIndex;
    private int rows;

    public Span(int rowIndex, int columnIndex, int rows) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.rows = rows;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRows() {
        return rows;
    }
}
