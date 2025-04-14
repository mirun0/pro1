package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import com.example.jsonObjects.Mistnost;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RozvrhJsonReader {
    
    private Gson gson;

    public RozvrhJsonReader() {
        gson = new Gson();
    }

    public Mistnost[] readMistnosti() throws IOException {
        URL url = new URL("https://stag-demo.uhk.cz/ws/services/rest2/mistnost/getMistnostiInfo?outputFormat=JSON");
        InputStream input = url.openStream();
        Reader reader = new InputStreamReader(input, "UTF-8");

        JsonElement rootElement = JsonParser.parseReader(reader);
        JsonObject rootObj = rootElement.getAsJsonObject();

        return gson.fromJson(rootObj.get("mistnostInfo"), Mistnost[].class);
    }
}
