package com.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.example.graphObjects.Circle;
import com.example.graphObjects.DefaultObject;
import com.example.graphObjects.Square;

public class MainFrame extends JFrame {

    private List<DefaultObject> sceneObjects = new ArrayList<>();

    public MainFrame() {
        super("Fake Illustrator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        initTestData();
        add(new GraphicsPanel(sceneObjects), BorderLayout.CENTER);
        
    }

    private void initTestData() {
        sceneObjects.add(new Square(new Point(100, 100), Color.BLUE, 100));
        sceneObjects.add(new Square(new Point(200, 100), Color.RED, 150));
        sceneObjects.add(new Circle(new Point(300, 100), Color.GREEN, 200));
        sceneObjects.add(new Circle(new Point(100, 300), Color.BLACK, 100));
    }
}
