package com.example.gui;

import javax.swing.*;
import javax.swing.table.*;

import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MergedCellsExample extends JFrame {

    public MergedCellsExample() {
        Object[][] data = {
            {"Merged", "B1", "C1"},
            {"Merged", "", "C2"},
            {"A3", "B3", "C3"}
        };
        String[] columnNames = {"Column 1", "Column 2", "Column 3"};

        JTable table = new JTable(data, columnNames) {
            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                if (row == 1 && (column == 0 || column == 1)) {
                    return new MergedCellRenderer();
                }
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

                // Zapnutí antialiasingu
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


                // Merging cells 0 and 1 in row 1
                Rectangle cellRect1 = getCellRect(1, 0, true);
                Rectangle cellRect2 = getCellRect(1, 1, true);
                Rectangle mergedRect = cellRect1.union(cellRect2);
                g2.setColor(getBackground());
                g2.fillRect(mergedRect.x, mergedRect.y, mergedRect.width - 1, mergedRect.height - 1);
                g2.setColor(Color.LIGHT_GRAY);
                //g2.drawRect(mergedRect.x - 1, mergedRect.y - 1, mergedRect.width, mergedRect.height);
                
                JLabel label = new JLabel("Merged");
                label.setForeground(getForeground());
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.CENTER);
                label.setBounds(mergedRect);

                SwingUtilities.paintComponent(g2, label, this, mergedRect);

                g2.dispose();
            }

            {
            DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) getDefaultRenderer(Object.class);
            renderer.setHorizontalAlignment(SwingConstants.CENTER);
            renderer.setVerticalAlignment(SwingConstants.CENTER);
            }


            @Override
            public String getToolTipText(MouseEvent e) {
                Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);

                Object value = getValueAt(rowIndex, colIndex);

                //System.out.println("Tooltip at [" + rowIndex + "," + colIndex + "]: " + value);

                if (rowIndex == 1 && colIndex == 0 || rowIndex == 1 && colIndex == 1) return "Tahle buňka je speciálně sloučená";

                if (value != null) {
                    return "<html><b>Hodnota:</b> <font color='yellow'>" + value.toString() + "</font></html>";
                }

                return value != null ? value.toString() : null;
            }

        };

        UIManager.put("ToolTip.background", Color.DARK_GRAY);
        UIManager.put("ToolTip.foreground", Color.WHITE);
        UIManager.put("ToolTip.font", new Font("SansSerif", Font.PLAIN, 12));
        UIManager.put("ToolTip.border", BorderFactory.createLineBorder(Color.RED));

        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(5000); 

        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        table.setShowGrid(true);

        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        table.setCellSelectionEnabled(false);
        table.setFocusable(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        setTitle("JTable Merged Cells Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    static class MergedCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return new JLabel(); // empty cell; actual rendering is done in paint()
        }
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(MergedCellsExample::new);
    }
}