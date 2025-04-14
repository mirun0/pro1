package com.example.gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class MainFrame extends JFrame {

    private DefaultTableModel tableModel = new DefaultTableModel();

    public MainFrame() {
        super("Prohlížeč rozvrhu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel navbar = new JPanel();
        //navbar.setBackground(Color.LIGHT_GRAY);
        navbar.setPreferredSize(new Dimension(0, 65));
        navbar.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 20));

        //Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY);
        //navbar.setBorder(bottomBorder);


        // budova panel
        JPanel budovaPanel = new JPanel();
        budovaPanel.setLayout(new BorderLayout());

        JLabel budovaLabel = new JLabel("Budova:   ");
        budovaLabel.setOpaque(true);
        //budovaLabel.setBackground(Color.LIGHT_GRAY);
        budovaPanel.add(budovaLabel, BorderLayout.WEST);

        JComboBox<String> budovaComboBox = new JComboBox<String>(new String[]{"1", "2", "3"});
        budovaComboBox.setPreferredSize(new Dimension(75, 25));
        budovaPanel.add(budovaComboBox, BorderLayout.CENTER);

        // mistnost panel
        JPanel misnostPanel = new JPanel();
        misnostPanel.setLayout(new BorderLayout());

        JLabel mistnostLabel = new JLabel("Mistnost:   ");
        mistnostLabel.setOpaque(true);
        misnostPanel.add(mistnostLabel, BorderLayout.WEST);

        JComboBox<String> mistnostComboBox = new JComboBox<String>(new String[]{"4", "5", "6"});
        mistnostComboBox.setPreferredSize(new Dimension(75, 25));
        misnostPanel.add(mistnostComboBox, BorderLayout.CENTER);

        // search panel

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());

        JButton searchButton = new JButton("Hledat");
        searchPanel.add(searchButton, BorderLayout.CENTER);


        navbar.add(budovaPanel);
        navbar.add(misnostPanel);
        navbar.add(searchPanel);
        add(navbar, BorderLayout.NORTH);


        String[][] data = {};
        String[] columnNames = {"Zkratka", "Název", "Učitel", "Den", "Od", "Do"};

        tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(175);
        table.getColumnModel().getColumn(2).setPreferredWidth(175);
        table.getColumnModel().getColumn(3).setPreferredWidth(30);
        table.getColumnModel().getColumn(4).setPreferredWidth(25);
        table.getColumnModel().getColumn(5).setPreferredWidth(25);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel leftPanel = new JPanel();
        add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        add(rightPanel, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel();
        add(bottomPanel, BorderLayout.SOUTH);



        setVisible(true);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}