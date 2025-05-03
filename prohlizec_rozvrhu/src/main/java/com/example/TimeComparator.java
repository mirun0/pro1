package com.example;

import java.time.LocalTime;
import java.util.Comparator;

public class TimeComparator implements Comparator<String> {

    @Override
    public int compare(String time1, String time2) {
        LocalTime t1 = LocalTime.parse(time1);
        LocalTime t2 = LocalTime.parse(time2);

        return t1.compareTo(t2);
    }
}