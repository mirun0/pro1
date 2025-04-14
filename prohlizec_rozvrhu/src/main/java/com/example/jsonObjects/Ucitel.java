package com.example.jsonObjects;

public class Ucitel {
    String jmeno;
    String prijmeni;
    String titulPred;
    String titulZa;

    @Override
    public String toString() {
        return titulPred + " " + jmeno + " " + prijmeni + " " + titulZa;
    }
}
