package com.example.gui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.example.graphObjects.DefaultObject;


public class GraphicsPanel extends JPanel {
    List<DefaultObject> sceneObjects = new ArrayList<>();

    public GraphicsPanel(List<DefaultObject> sceneObjects) {
        this.sceneObjects = sceneObjects;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3f));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (DefaultObject object : sceneObjects) {
            object.draw(g2);
        }
    }
}
