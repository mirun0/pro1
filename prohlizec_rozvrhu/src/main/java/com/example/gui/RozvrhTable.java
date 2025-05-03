package com.example.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import com.example.jsonObjects.RozvrhovaAkce;

public class RozvrhTable extends JTable {

    class MergedCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return new JLabel(); // empty cell, rendering in paint()
        }
    }

    private RozvrhTableModel rozvrhTableModel;
    private List<Span> spans;
 
    public RozvrhTable(RozvrhTableModel rozvrhTableModel) {
        super(rozvrhTableModel);
        this.rozvrhTableModel = rozvrhTableModel;
        this.spans = rozvrhTableModel.getSpans();

        this.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            protected void paintComponent(Graphics g) {
                Color bg = getBackground();
                if (bg != null) {
                    g.setColor(bg);
                    g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
                }
                super.paintComponent(g);
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
        
                JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
                RozvrhovaAkce ra = rozvrhTableModel.getData()[row][column];
                if(ra != null) {
                    if(ra.getTypAkce().equals("Přednáška")) {
                        c.setBackground(Color.decode("#46495b"));
                    } else if(ra.getTypAkce().equals("Cvičení")) {
                        c.setBackground(Color.decode("#46594b"));
                    } else {
                        c.setBackground(Color.decode("#56494b"));
                    }
                } else {
                    c.setBackground(Color.decode("#46494b"));
                }
                
                c.setOpaque(false);
                return c;
            }
        });

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) getDefaultRenderer(Object.class);

        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        renderer.setVerticalAlignment(SwingConstants.CENTER);

        setRowSelectionAllowed(false);
        setColumnSelectionAllowed(false);
        setCellSelectionEnabled(false);
        setFocusable(false);

        UIManager.put("ToolTip.border", BorderFactory.createLineBorder(Color.DARK_GRAY));
    }

    @Override
    public String getToolTipText(MouseEvent e) {
        Point p = e.getPoint();
        int rowIndex = rowAtPoint(p);
        int colIndex = columnAtPoint(p);
        RozvrhovaAkce value;

        RozvrhovaAkce[][] data = rozvrhTableModel.getData();

        for (Span span : spans) {
            if(rowIndex == span.getRowIndex() && span.getColumnIndex() <= colIndex && colIndex < (span.getColumnIndex() + span.getRows())) {
                value = data[rowIndex][span.getColumnIndex()];

                if(value != null) {
                    return "<html><p><b>" + value.getNazev() + "</b> (" + value.getPredmet() + ")</p>" + 
                        "<p>" + value.getUcitel() + "</p>" + 
                        "<p>" + value.getDen() + " " + value.getHodinaSkutOd() + " - " + value.getHodinaSkutDo() + "</p>" + 
                        "<p>" + value.getTypAkce() + "</p></html>";
                }
            }
        }

        if(data[rowIndex] != null & data[rowIndex][colIndex] != null) {
            value = data[rowIndex][colIndex];
            return "<html><p><b>" + value.getNazev() + "</b> (" + value.getPredmet() + ")</p>" + 
                "<p>" + value.getUcitel() + "</p>" + 
                "<p>" + value.getDen() + " " + value.getHodinaSkutOd() + " - " + value.getHodinaSkutDo() + "</p>" + 
                "<p>" + value.getTypAkce() + "</p></html>";
        }

        if(getValueAt(rowIndex, colIndex) != null) {
            return (String) getValueAt(rowIndex, colIndex);
        }
        return null;
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        return super.getCellRenderer(row, column);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RozvrhovaAkce[][] data = rozvrhTableModel.getData();

        for (Span span : spans) {
            //System.out.println(span.getColumnIndex() + " -> " + (span.getColumnIndex() + span.getRows() - 1) + " (" + span.getPredmet() + ")");
            Rectangle merged = getCellRect(span.getRowIndex(), span.getColumnIndex(), true);
            for (int i = 1; i < span.getRows(); i++) {
                merged = merged.union(getCellRect(span.getRowIndex(), span.getColumnIndex() + i, true));
            }

            RozvrhovaAkce ra = data[span.getRowIndex()][span.getColumnIndex()];
            JLabel label = new JLabel(ra.getPredmet());

            if(ra.getTypAkce().equals("Přednáška")) {
                g2.setColor(Color.decode("#46495b"));
            } else if(ra.getTypAkce().equals("Cvičení")) {
                g2.setColor(Color.decode("#46594b"));
            } else {
                g2.setColor(Color.decode("#56494b"));
            }

            g2.fillRect(merged.x, merged.y, merged.width - 1, merged.height - 1);
            g2.setColor(Color.LIGHT_GRAY);

            label.setForeground(getForeground());
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setBounds(merged);

            SwingUtilities.paintComponent(g2, label, this, merged);

        }
        g2.dispose();
    }
}
