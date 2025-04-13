package com.example.graphObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Triangle extends DefaultObject {

    protected int a;
    protected int height;

    public Triangle(Point position, Color color, boolean fill, int a) {
        super(position, color, fill);
        this.a = a;
        this.height = (int)(a * Math.sqrt(3) / 2);
    }

    @Override
    public boolean contains(int x, int y) {
        Point p = new Point(x, y);
        Point b = new Point(position.x, position.y);
        Point c = new Point(position.x + a / 2, position.y + height);
        Point d = new Point(position.x - a / 2, position.y + height);
        
        return Math.abs(area(b, c, d) - (area(p, c, d) + area(b, p, d) + area(b, c, p))) < 0.1;
    }

    public double area(Point a, Point b, Point c) {
        return Math.abs((a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) / 2.0);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        if(fill) {
            g.fillPolygon(new int[]{position.x, position.x + a / 2, position.x - a / 2}, new int[]{position.y, position.y + height, position.y + height}, 3);

        } else {
            g.drawPolygon(new int[]{position.x, position.x + a / 2, position.x - a / 2}, new int[]{position.y, position.y + height, position.y + height}, 3);
        }
    }
    
}
