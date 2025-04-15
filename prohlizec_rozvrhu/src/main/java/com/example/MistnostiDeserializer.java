package com.example;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.example.jsonObjects.Mistnost;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

class MistnostiDeserializer implements JsonDeserializer<List<Mistnost>> {

    @Override
    public List<Mistnost> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<Mistnost> vysledek = new ArrayList<>();
        JsonArray pole = json.getAsJsonArray();

        for (JsonElement element : pole) {
            JsonObject obj = element.getAsJsonObject();
            String typ = obj.has("typ") ? obj.get("typ").getAsString() : "";

            if ("Učebna".equalsIgnoreCase(typ)) {
                Mistnost m = context.deserialize(obj, Mistnost.class);
                vysledek.add(m);
            }
        }

        return vysledek;
    }
}
