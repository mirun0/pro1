package com.example.graphObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public abstract class DefaultObject {
    
    protected Point position;
    protected Color color;

    public DefaultObject(Point position, Color color) {
        this.position = position;
        this.color = color;
    }

    public DefaultObject() {
        position = new Point(0, 0);
        color = Color.BLACK;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.position = new Point(x, y);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract boolean contains(int x, int y);

    public abstract void draw(Graphics2D g);
}
