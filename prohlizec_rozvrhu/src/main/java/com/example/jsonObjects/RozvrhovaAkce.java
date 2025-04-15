package com.example.jsonObjects;

public class RozvrhovaAkce {
    String nazev;
    String predmet;
    Ucitel ucitel;
    String den;
    Hodina hodinaSkutOd;
    Hodina hodinaSkutDo;
    String semestr;

    public RozvrhovaAkce() {
    }

    public String getSemestr() {
        return semestr;
    }

    public String getNazev() {
        return nazev;
    }

    public String getPredmet() {
        return predmet;
    }

    public Ucitel getUcitel() {
        return ucitel;
    }

    public String getDen() {
        return den;
    }

    public Hodina getHodinaSkutOd() {
        return hodinaSkutOd;
    }

    public Hodina getHodinaSkutDo() {
        return hodinaSkutDo;
    }

    @Override
    public String toString() {
        return nazev + " " + predmet + " " + ucitel + " " + den + " od: " + hodinaSkutOd + " do: " + hodinaSkutDo;
    }
}
