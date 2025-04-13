package com.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import com.example.graphObjects.Circle;
import com.example.graphObjects.DefaultObject;
import com.example.graphObjects.Rectangle;
import com.example.graphObjects.Triangle;
import com.example.RandomColor;

public class MainFrame extends JFrame {

    private List<DefaultObject> sceneObjects = new ArrayList<>();

    private JToolBar toolBar;
    private JToggleButton btSquare;
    private JToggleButton btCircle;
    private JToggleButton btRectangle;
    private JToggleButton btTriangle;
    private JToggleButton btMove;

    private DefaultObject activeObject;
    private Point initialMousePosition;

    public MainFrame() {
        super("Fake Illustrator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        //initTestData();
        createToolBar();
        GraphicsPanel panel = new GraphicsPanel(sceneObjects);

        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                    if(btMove.isSelected()) {
                        for (int i = sceneObjects.size() - 1; i >= 0; i--) {
                            if(sceneObjects.get(i).contains(e.getX(), e.getY())) {
                                activeObject = sceneObjects.get(i);
                                sceneObjects.remove(i);
                                sceneObjects.add(activeObject);
                                initialMousePosition = e.getPoint();
                                break;
                            }
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                if(btSquare.isSelected()) {
                    sceneObjects.add(new Rectangle(new Point(e.getX(), e.getY()), RandomColor.getRandomColor(), (e.getButton() == MouseEvent.BUTTON1) ? true : false, 25));
                }
                if(btRectangle.isSelected()) {
                    sceneObjects.add(new Rectangle(new Point(e.getX(), e.getY()), RandomColor.getRandomColor(), (e.getButton() == MouseEvent.BUTTON1) ? true : false, 25, 35));
                }
                if(btCircle.isSelected()) {
                    sceneObjects.add(new Circle(new Point(e.getX(), e.getY()), RandomColor.getRandomColor(), (e.getButton() == MouseEvent.BUTTON1) ? true : false, 25));
                }
                if(btTriangle.isSelected()) {
                    sceneObjects.add(new Triangle(new Point(e.getX(), e.getY()), RandomColor.getRandomColor(), (e.getButton() == MouseEvent.BUTTON1) ? true : false, 25));
                }

                repaint();

                if(btMove.isSelected()) {
                    activeObject = null;
                }
            }
        
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if(btMove.isSelected() && activeObject != null) {
                    int diffX = e.getX() - initialMousePosition.x;
                    int diffY = e.getY() - initialMousePosition.y;

                    activeObject.setPosition(activeObject.getPosition().x + diffX, activeObject.getPosition().y + diffY);
                    initialMousePosition = e.getPoint();
                    repaint();
                }
            }

        });

        add(panel, BorderLayout.CENTER);
    }

    private void createToolBar() {
        toolBar = new JToolBar(JToolBar.HORIZONTAL);
        add(toolBar, BorderLayout.NORTH);
        btSquare = new JToggleButton(new ImageIcon(new ImageIcon("demo/res/square.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
        btCircle = new JToggleButton(new ImageIcon(new ImageIcon("demo/res/circle.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
        btRectangle = new JToggleButton(new ImageIcon(new ImageIcon("demo/res/square.png").getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH)));
        btTriangle = new JToggleButton(new ImageIcon(new ImageIcon("demo/res/triangle.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
        btMove = new JToggleButton(new ImageIcon(new ImageIcon("demo/res/move.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
        toolBar.add(btSquare);
        toolBar.add(btCircle);
        toolBar.add(btRectangle);
        toolBar.add(btTriangle);
        toolBar.add(btMove);
        ButtonGroup group = new ButtonGroup();
        group.add(btSquare);
        group.add(btCircle);
        group.add(btRectangle);
        group.add(btTriangle);
        group.add(btMove);
    }

    /*
    private void initTestData() {
        sceneObjects.add(new Rectangle(new Point(500, 100), Color.BLUE, 100, 150));
        sceneObjects.add(new Rectangle(new Point(200, 100), Color.RED, 150));
        sceneObjects.add(new Circle(new Point(300, 100), Color.GREEN, 200));
        sceneObjects.add(new Circle(new Point(100, 300), Color.BLACK, 100));
    }*/


}
