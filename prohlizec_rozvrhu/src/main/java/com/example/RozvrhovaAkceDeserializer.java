package com.example;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.example.jsonObjects.RozvrhovaAkce;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

class RozvrhovaAkceDeserializer implements JsonDeserializer<List<RozvrhovaAkce>> {

    @Override
    public List<RozvrhovaAkce> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<RozvrhovaAkce> vysledek = new ArrayList<>();
        JsonArray pole = json.getAsJsonArray();

        for (JsonElement element : pole) {
            JsonObject obj = element.getAsJsonObject();
            String typAkce = obj.has("typAkce") ? obj.get("typAkce").getAsString() : "";
            String tyden = obj.has("tydenZkr") ? obj.get("tydenZkr").getAsString() : "";

            if ("Cvičení".equalsIgnoreCase(typAkce) || "Přednáška".equalsIgnoreCase(typAkce) || "Seminář".equalsIgnoreCase(typAkce) || "Laboratoř".equalsIgnoreCase(typAkce)) {
                if("K".equalsIgnoreCase(tyden)) {
                    RozvrhovaAkce ra = context.deserialize(obj, RozvrhovaAkce.class);
                    vysledek.add(ra);
                }
            }
        }

        return vysledek;
    }
}
