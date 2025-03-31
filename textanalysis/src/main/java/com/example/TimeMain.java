package com.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class TimeMain {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(date));
    }
}
