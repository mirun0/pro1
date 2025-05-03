package com.example.jsonObjects;

public class Ucitel {
    private String jmeno;
    private String prijmeni;
    private String titulPred;
    private String titulZa;

    @Override
    public String toString() {
        String str = jmeno + " " + prijmeni;
        if(titulPred != null) {
            str = titulPred + " " + str;
        }
        if(titulZa != null) {
            str += " " + titulZa;
        }

        return str;
    }
}
