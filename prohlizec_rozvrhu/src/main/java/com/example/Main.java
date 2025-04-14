package com.example;

import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.example.jsonObjects.Mistnost;
import com.formdev.flatlaf.FlatDarkLaf;

public class Main { 
    public static void main(String[] args) throws IOException { 
      
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }


        RozvrhJsonReader jsonReader = new RozvrhJsonReader();
        Mistnost[] mistnosti = jsonReader.readMistnosti();

        for (Mistnost mistnost : mistnosti) {
            System.out.println(mistnost);
        }



        // MainFrame mainFrame = new MainFrame();

        //Reader reader = new InputStreamReader(new FileInputStream("src/main/resources/test_data.json"), "UTF-8");
        /*String mistnost = "J1";
        URL url = new URL("https://stag-demo.uhk.cz/ws/services/rest2/rozvrhy/getRozvrhByMistnost?semestr=%25&budova=J&mistnost=" + mistnost + "&outputFormat=JSON"); // Změň na svou API URL
        InputStream input = url.openStream();
        Reader reader = new InputStreamReader(input, "UTF-8");

        JsonElement rootElement = JsonParser.parseReader(reader);
        JsonObject rootObj = rootElement.getAsJsonObject();

        RozvrhovaAkce[] data = gson.fromJson(rootObj.get("rozvrhovaAkce"), RozvrhovaAkce[].class);

        for (RozvrhovaAkce rozvrhovaAkce : data) {
            System.out.println(rozvrhovaAkce);
            mainFrame.getTableModel().addRow(new Object[]{rozvrhovaAkce.getPredmet(), rozvrhovaAkce.getNazev(), rozvrhovaAkce.getUcitel(), rozvrhovaAkce.getDen(), rozvrhovaAkce.getHodinaSkutOd(), rozvrhovaAkce.getHodinaSkutDo()});
        }*/
    }
}
