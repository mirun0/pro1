package com.example.gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("Prohlížeč rozvrhu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel navbar = new JPanel();
        navbar.setBackground(Color.LIGHT_GRAY);
        navbar.setPreferredSize(new Dimension(0, 65));
        navbar.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 20));

        // budova panel
        JPanel budovaPanel = new JPanel();
        budovaPanel.setLayout(new BorderLayout());

        JLabel budovaLabel = new JLabel("Budova: ");
        budovaLabel.setOpaque(true);
        budovaLabel.setBackground(Color.LIGHT_GRAY);
        budovaPanel.add(budovaLabel, BorderLayout.WEST);

        JComboBox<String> budovaComboBox = new JComboBox<String>(new String[]{"1", "2", "3"});
        budovaComboBox.setPreferredSize(new Dimension(75, 25));
        budovaPanel.add(budovaComboBox, BorderLayout.CENTER);

        // mistnost panel
        JPanel misnostPanel = new JPanel();
        misnostPanel.setLayout(new BorderLayout());

        JLabel mistnostLabel = new JLabel("Mistnost: ");
        mistnostLabel.setOpaque(true);
        mistnostLabel.setBackground(Color.LIGHT_GRAY);
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

        setVisible(true);
    }
}
