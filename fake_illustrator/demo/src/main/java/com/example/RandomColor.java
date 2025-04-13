package com.example;

import java.awt.Color;
import java.util.Random;

public class RandomColor {

    private static Random random = new Random();

    public static Color getRandomColor() {
        Color[] colors = new Color[]{Color.RED, Color.BLACK, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.RED, Color.PINK, Color.YELLOW};
        return colors[random.nextInt(colors.length)];
    }
}