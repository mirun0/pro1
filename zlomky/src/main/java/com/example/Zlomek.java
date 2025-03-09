package com.example;

public class Zlomek extends Number {
    private final int citatel;
    private final int jmenovatel;

    public Zlomek(int citatel, int jmenovatel) {
        this.citatel = citatel;
        this.jmenovatel = jmenovatel;
    }

    public int getCitatel() {
        return citatel;
    }

    public int getJmenovatel() {
        return jmenovatel;
    }

    public Zlomek krat(Zlomek zlomek) {
        return new Zlomek(citatel * zlomek.citatel, jmenovatel * zlomek.jmenovatel);
    }

    public Zlomek deleno(Zlomek zlomek) {
        return new Zlomek(citatel * zlomek.jmenovatel, jmenovatel * zlomek.citatel);
    }

    public Zlomek plus(Zlomek zlomek) {
        return new Zlomek(citatel * zlomek.jmenovatel + zlomek.citatel * jmenovatel, jmenovatel * zlomek.jmenovatel);
    }

    public Zlomek minus(Zlomek zlomek) {
        return new Zlomek(citatel * zlomek.jmenovatel - zlomek.citatel * jmenovatel, jmenovatel * zlomek.jmenovatel);
    }

    public Zlomek zkratit() {
        int a = Math.abs(citatel);
        int b = Math.abs(jmenovatel);

        if(a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        int zbytek;
        if (b != 0) {
            do {
                zbytek = a % b;
                a = b;
                b = zbytek;
            } while(zbytek != 0);
            return new Zlomek(citatel / a, jmenovatel / a);
        } else {
            return new Zlomek(0, 1);
        }
    }

    @Override
    public String toString() {
        return String.format("%d / %d", citatel, jmenovatel);
    }

    @Override
    public int intValue() {
        return citatel / jmenovatel;
    }

    @Override
    public long longValue() {
        return intValue();
    }

    @Override
    public float floatValue() {
        return (float)doubleValue();
    }

    @Override
    public double doubleValue() {
        return ((double)citatel) / jmenovatel;
    }
}
