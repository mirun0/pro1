package com.example.jsonObjects;

public class Ucitel {
    String jmeno;
    String prijmeni;
    String titulPred;
    String titulZa;

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
