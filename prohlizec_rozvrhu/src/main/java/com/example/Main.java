package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.example.gui.MainFrame;
import com.example.jsonObjects.Mistnost;
import com.formdev.flatlaf.FlatDarkLaf;

public class Main {

    private static HashMap<String, ArrayList<String>> mistnosti = new HashMap<>();
    private static RozvrhJsonReader jsonReader = new RozvrhJsonReader();
    public static void main(String[] args) throws IOException { 
      try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        parseMistnosti();
        new MainFrame(jsonReader, mistnosti);
    }

    private static void parseMistnosti() throws IOException {
        List<Mistnost> rozAkce = jsonReader.readMistnosti();
        for (Mistnost mistnost : rozAkce) {
            if(!mistnosti.containsKey(mistnost.getZkrBudovy())) {
                mistnosti.put(mistnost.getZkrBudovy(), new ArrayList<String>());
            }
            mistnosti.get(mistnost.getZkrBudovy()).add(mistnost.getCisloMistnosti());
        }
    }
}
