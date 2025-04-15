package com.example.jsonObjects;

public class Mistnost {
    String zkrBudovy;
    String cisloMistnosti;

    public String getZkrBudovy() {
        return zkrBudovy;
    }

    public String getCisloMistnosti() {
        return cisloMistnosti;
    }

    @Override
    public String toString() {
        return zkrBudovy + " " + cisloMistnosti;
    }
}
