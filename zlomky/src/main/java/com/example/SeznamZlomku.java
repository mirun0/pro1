package com.example;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class SeznamZlomku {
    private final List<Zlomek> zlomky;

    public SeznamZlomku() {
        zlomky = new ArrayList<>();
    }

    public void pridat(Zlomek zlomek) {
        if(zlomek == null) {
            throw new NullPointerException("Zlomek null");
        }
        zlomky.add(zlomek);
    }

    public Zlomek vybrat(int index) {
        return zlomky.get(index);
    }

    public void odebrat(int index) {
        zlomky.remove(index);
    }

    public void vypis(PrintStream out) {
        for (int i = 0; i < zlomky.size(); i++) {
            out.printf("[%d] => %s\n", i + 1, zlomky.get(i));
        }
    }

    public Zlomek soucet() {
        Zlomek suma = new Zlomek(0, 1);
        for (Zlomek z : zlomky) {
            suma = suma.plus(z);
        }
        return suma.zkratit();
    }

    public Zlomek prumer() {
        Zlomek suma = soucet();
        Zlomek pocet = new Zlomek(zlomky.size(),1);
        return suma.deleno(pocet).zkratit();
    }

    public int pocet() {
        return zlomky.size();
    }
}
