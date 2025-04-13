package com.example.graphObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public abstract class DefaultObject {
    
    protected Point position;
    protected Color color;
    protected boolean fill;

    public DefaultObject(Point position, Color color, boolean fill) {
        this.position = position;
        this.color = color;
        this.fill = fill;
    }

    public DefaultObject() {
        position = new Point(0, 0);
        color = Color.BLACK;
        this.fill = false;
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

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public abstract boolean contains(int x, int y);

    public abstract void draw(Graphics2D g);
}
