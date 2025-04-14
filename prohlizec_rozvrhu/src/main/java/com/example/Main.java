package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import com.example.gui.MainFrame;
import com.example.jsonObjects.RozvrhovaAkce;
import com.google.gson.Gson; 
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main { 
    public static void main(String[] args) throws IOException { 
      
        Gson gson = new Gson();

        new MainFrame();

        //Reader reader = new InputStreamReader(new FileInputStream("src/main/resources/test_data.json"), "UTF-8");
        String mistnost = "J1";
        URL url = new URL("https://stag-demo.uhk.cz/ws/services/rest2/rozvrhy/getRozvrhByMistnost?semestr=%25&budova=J&mistnost=" + mistnost + "&outputFormat=JSON"); // Změň na svou API URL
        InputStream input = url.openStream();
        Reader reader = new InputStreamReader(input, "UTF-8");

        JsonElement rootElement = JsonParser.parseReader(reader);
        JsonObject rootObj = rootElement.getAsJsonObject();

        RozvrhovaAkce[] data = gson.fromJson(rootObj.get("rozvrhovaAkce"), RozvrhovaAkce[].class);

        for (RozvrhovaAkce rozvrhovaAkce : data) {
            System.out.println(rozvrhovaAkce);
        }
    }
}
