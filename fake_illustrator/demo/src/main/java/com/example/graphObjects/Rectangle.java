package com.example.graphObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Rectangle extends DefaultObject {

    protected int a;
    protected int b;

    public Rectangle(Point position, Color color, boolean fill, int a, int b) {
        super(position, color, fill);
        this.a = a;
        this.b = b;
    }

    public Rectangle(Point position, Color color, boolean fill, int a) {
        super(position, color, fill);
        this.a = a;
        this.b = a;
    }

    @Override
    public boolean contains(int x, int y) {
        return x >= position.x && x <= position.x + a && y >= position.y && y <= position.y + b;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        if(fill) {
            g.fillRect(position.x, position.y, a, b);
        } else {
            g.drawRect(position.x, position.y, a, b);
        }
    }
    
}
