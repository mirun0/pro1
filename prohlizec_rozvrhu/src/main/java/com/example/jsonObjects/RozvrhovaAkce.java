package com.example.jsonObjects;

public class RozvrhovaAkce {
    private String nazev;
    private String predmet;
    private Ucitel ucitel;
    private String den;
    private Hodina hodinaSkutOd;
    private Hodina hodinaSkutDo;
    private String semestr;
    private String typAkce;

    public String getTypAkce() {
        return typAkce;
    }

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
