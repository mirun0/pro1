package com.example;

public class Main {
    public static void main(String[] args) {
        SeznamZlomku seznamZlomku = new SeznamZlomku();

        seznamZlomku.pridat(new Zlomek(1, 2));
        seznamZlomku.pridat(new Zlomek(2, 3));
        seznamZlomku.pridat(new Zlomek(-4, 5));
        seznamZlomku.pridat(new Zlomek(7, 8));

        seznamZlomku.vypis(System.out);

        Zlomek suma = seznamZlomku.soucet();
        System.out.println("Soucet zlomku je: " + suma);
        System.out.println("Soucet zlomku je (v R): " + suma.doubleValue());

        Zlomek prumer = seznamZlomku.prumer();
        System.out.println("Prumer zlomku je: " + prumer);
        System.out.println("Prumer zlomku je (v R): " + prumer.doubleValue());
    }
}