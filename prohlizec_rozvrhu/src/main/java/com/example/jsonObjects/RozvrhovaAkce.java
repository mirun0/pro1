package com.example.jsonObjects;

public class RozvrhovaAkce {
    String nazev;
    String predmet;
    Ucitel ucitel;
    String den;
    Hodina hodinaSkutOd;
    Hodina hodinaSkutDo;

    @Override
    public String toString() {
        return nazev + " " + predmet + " " + ucitel + " " + den + " od: " + hodinaSkutOd + " do: " + hodinaSkutDo;
    }
}
