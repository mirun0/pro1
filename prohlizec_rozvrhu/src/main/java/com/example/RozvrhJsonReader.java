package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import com.example.jsonObjects.Mistnost;
import com.example.jsonObjects.RozvrhovaAkce;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class RozvrhJsonReader {
    
    private Gson gsonMistnostiFilter;
    private Gson gsonRozvrhovaAkceFilter;

    public RozvrhJsonReader() {
        gsonMistnostiFilter = new GsonBuilder().registerTypeAdapter(new TypeToken<List<Mistnost>>(){}.getType(), new MistnostiDeserializer()).create();
        gsonRozvrhovaAkceFilter = new GsonBuilder().registerTypeAdapter(new TypeToken<List<RozvrhovaAkce>>(){}.getType(), new RozvrhovaAkceDeserializer()).create();
    }

    public List<Mistnost> readMistnosti() throws IOException {
        URL url = new URL("https://stag-demo.uhk.cz/ws/services/rest2/mistnost/getMistnostiInfo?outputFormat=JSON");
        InputStream input = url.openStream();
        Reader reader = new InputStreamReader(input, "UTF-8");

        JsonElement rootElement = JsonParser.parseReader(reader);
        JsonObject rootObj = rootElement.getAsJsonObject();

        List<Mistnost> mistnosti = gsonMistnostiFilter.fromJson(rootObj.get("mistnostInfo"), new TypeToken<List<Mistnost>>(){}.getType());

        return mistnosti;
    }

    public List<RozvrhovaAkce> readRozvrhoveAkce(String semestr, String budova, String mistnost) throws IOException {
        String rawUrl = "https://stag-demo.uhk.cz/ws/services/rest2/rozvrhy/getRozvrhByMistnost?outputFormat=JSON";
        
        if(semestr != null) {
            rawUrl += "&semestr=" + URLEncoder.encode(semestr, "UTF-8");
        }
        if(budova != null) {
            rawUrl += "&budova=" + URLEncoder.encode(budova, "UTF-8");
        }
        if(mistnost != null) {
            rawUrl += "&mistnost=" + URLEncoder.encode(mistnost, "UTF-8");
        }

        URL url = new URL(rawUrl);
        InputStream input = url.openStream();
        Reader reader = new InputStreamReader(input, "UTF-8");

        JsonElement rootElement = JsonParser.parseReader(reader);
        JsonObject rootObj = rootElement.getAsJsonObject();

        List<RozvrhovaAkce> rozAkce = gsonRozvrhovaAkceFilter.fromJson(rootObj.get("rozvrhovaAkce"), new TypeToken<List<RozvrhovaAkce>>(){}.getType());
        
        return rozAkce;
    }
}
