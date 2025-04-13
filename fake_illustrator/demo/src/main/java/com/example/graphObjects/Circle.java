package com.example.graphObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Circle extends DefaultObject {

    protected int r;

    public Circle(Point position, Color color, boolean fill, int r) {
        super(position, color, fill);
        this.r = r;
    }

    @Override
    public boolean contains(int x, int y) {
        return (Math.pow(x - (position.x + r), 2) + Math.pow(y - (position.y + r), 2)) <= r * r;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        if(fill) {
            g.fillOval(position.x, position.y,  2 * r, 2 * r);
        } else {
            g.drawOval(position.x, position.y, 2 * r, 2 * r);
        }
    }
}
