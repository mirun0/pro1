package com.example.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

import com.example.RozvrhJsonReader;
import com.example.jsonObjects.RozvrhovaAkce;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MainFrame extends JFrame {

    private DefaultTableModel tableModel = new DefaultTableModel();
    private HashMap<String, ArrayList<String>> mistnosti;
    private RozvrhJsonReader jsonReader;

    private JComboBox<String> semestrComboBox;
    private JComboBox<String> budovaComboBox;
    private JComboBox<String> mistnostComboBox;

    public MainFrame(RozvrhJsonReader jsonReader, HashMap<String, ArrayList<String>> mistnosti) {
        super("Prohlížeč rozvrhu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        this.mistnosti = mistnosti;
        this.jsonReader = jsonReader;

        JPanel navbar = new JPanel();
        navbar.setPreferredSize(new Dimension(0, 65));
        navbar.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 20));


        // semestr panel
        JPanel semestrPanel = new JPanel();
        semestrPanel.setLayout(new BorderLayout());

        JLabel semestrLabel = new JLabel("Semestr:   ");
        semestrLabel.setOpaque(true);
        semestrPanel.add(semestrLabel, BorderLayout.WEST);

        semestrComboBox = new JComboBox<String>(new String[]{"ZS", "LS"});
        semestrComboBox.setPreferredSize(new Dimension(75, 25));
        semestrPanel.add(semestrComboBox, BorderLayout.CENTER);


        // budova panel
        JPanel budovaPanel = new JPanel();
        budovaPanel.setLayout(new BorderLayout());

        JLabel budovaLabel = new JLabel("Budova:   ");
        budovaPanel.add(budovaLabel, BorderLayout.WEST);

        budovaComboBox = new JComboBox<String>(new String[]{});
        budovaComboBox.setPreferredSize(new Dimension(75, 25));
        budovaPanel.add(budovaComboBox, BorderLayout.CENTER);

        budovaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadMistnostiComboBox((String)budovaComboBox.getSelectedItem());
            }
        });
        // mistnost panel
        JPanel misnostPanel = new JPanel();
        misnostPanel.setLayout(new BorderLayout());

        JLabel mistnostLabel = new JLabel("Místnost:   ");
        misnostPanel.add(mistnostLabel, BorderLayout.WEST);

        mistnostComboBox = new JComboBox<String>(new String[]{});
        mistnostComboBox.setPreferredSize(new Dimension(75, 25));
        misnostPanel.add(mistnostComboBox, BorderLayout.CENTER);

        // search panel

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());

        JButton searchButton = new JButton("Hledat");
        searchPanel.add(searchButton, BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String semestr = (String)semestrComboBox.getSelectedItem();
                String budova = (String)budovaComboBox.getSelectedItem();
                String mistnost = (String)mistnostComboBox.getSelectedItem();

                loadDataToTable(semestr, budova, mistnost);
            }
        });


        JPanel toggleTablePanel = new JPanel();
        JToggleButton toggleTableButton = new JToggleButton(new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("table.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
        toggleTablePanel.add(toggleTableButton);

        navbar.add(semestrPanel);
        navbar.add(budovaPanel);
        navbar.add(misnostPanel);
        navbar.add(searchPanel);
        navbar.add(toggleTablePanel);
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


        loadBudovyComboBox();
        setVisible(true);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    private void loadBudovyComboBox() {
        List<String> budovy = new ArrayList<>();
        for (String budova : mistnosti.keySet()) {
            budovy.add(budova);
        }

        budovy.sort(Comparator.naturalOrder());
        for (String budova : budovy) {
            budovaComboBox.addItem(budova);
        }
    }

    private void loadMistnostiComboBox(String budova) {
        mistnostComboBox.removeAllItems();
        List<String> mist = new ArrayList<>();
        for (String mistnost : mistnosti.get(budova)) {
            mist.add(mistnost);
        }

        mist.sort((s1, s2) -> {
            if(s1.length() < s2.length() || (s1.length() == s2.length() && s1.compareTo(s2) < 0)) {
                return -1;
            }
            return 1;
        });

        for (String mistnost : mist) {
            mistnostComboBox.addItem(mistnost);
        }
    }

    private void loadDataToTable(String semestr, String budova, String mistnost) {
        List<RozvrhovaAkce> rozvrhoveAkce = null;
        try {
            rozvrhoveAkce = jsonReader.readRozvrhoveAkce(semestr, budova, mistnost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        getTableModel().setRowCount(0);
        for (RozvrhovaAkce rozvrhovaAkce : rozvrhoveAkce) {
            if(rozvrhovaAkce.getUcitel() != null)
                getTableModel().addRow(new Object[]{rozvrhovaAkce.getPredmet(), rozvrhovaAkce.getNazev(), rozvrhovaAkce.getUcitel(), rozvrhovaAkce.getDen(), rozvrhovaAkce.getHodinaSkutOd(), rozvrhovaAkce.getHodinaSkutDo()});
        }

        if(getTableModel().getRowCount() == 0) {
            JOptionPane.showMessageDialog(
            null,
            "Nebyla nalezena žádná rozvrhová akce.", 
            "Informace",
            JOptionPane.INFORMATION_MESSAGE
        );
        }
    }
}