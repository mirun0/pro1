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
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableModel;

import com.example.RozvrhJsonReader;
import com.example.jsonObjects.RozvrhovaAkce;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MainFrame extends JFrame {

    private DefaultTableModel tableModel = new DefaultTableModel();
    RozvrhTableModel rozvrhTableModel;
    private HashMap<String, ArrayList<String>> mistnosti;
    private RozvrhJsonReader jsonReader;
    List<RozvrhovaAkce> rozvrhoveAkce;

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
                rozvrhTableModel.loadRozvrhoveAkce(rozvrhoveAkce);
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









        JPanel rozvrhPanel = new JPanel();
        rozvrhPanel.setLayout(new BorderLayout());



        String[][] dataRozvrh = {};
        String[] columnNamesRozvrh = {"", "07:25", "08:15", "09:05", "09:55", "10:45", "11:35", "12:25", 
        "13:15", "14:05", "14:55", "15:45", "16:35", "17:25", "18:15", "19:05", "19:55"};

        rozvrhTableModel = new RozvrhTableModel(dataRozvrh, columnNamesRozvrh) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable rozvrhTable = new JTable(rozvrhTableModel) {
            @Override
            public String getToolTipText(MouseEvent e) {
                Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                Object value = getValueAt(rowIndex, colIndex);
                return value != null ? value.toString() : null;
                }
            };
            
        JScrollPane scrollPane = new JScrollPane(rozvrhTable);

        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(5000); 

        rozvrhTable.setRowHeight(45);
        rozvrhTable.setShowGrid(true);
        rozvrhTable.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        JPanel bottomPanelR = new JPanel();
        bottomPanelR.setPreferredSize(new Dimension(100, 189));
        rozvrhPanel.add(bottomPanelR, BorderLayout.SOUTH);

        JPanel topPanelR = new JPanel();
        //topPanelR.setPreferredSize(new Dimension(100, 189));
        topPanelR.setBackground(Color.RED);
        //rozvrhPanel.add(topPanelR, BorderLayout.NORTH);

        rozvrhPanel.add(scrollPane, BorderLayout.CENTER);


        JScrollPane scrollPaneTable = new JScrollPane(table);
        add(scrollPaneTable, BorderLayout.CENTER);


        toggleTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (toggleTableButton.isSelected()) {
                    remove(scrollPaneTable);
                    add(rozvrhPanel, BorderLayout.CENTER);
                } else {
                    remove(rozvrhPanel);
                    add(scrollPaneTable, BorderLayout.CENTER);
                }

                revalidate();
                repaint();
            }
        });

        JPanel leftPanel = new JPanel();
        add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        add(rightPanel, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel();
        add(bottomPanel, BorderLayout.SOUTH);


        loadBudovyComboBox();
        setVisible(true);
        setResizable(false);
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