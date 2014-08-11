package com.learn.patterns.structural.flyweight;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


//Flyweight design pattern is used when we need to create a lot of Objects of a class.
//To apply flyweight pattern, we need to divide Object property into intrinsic and extrinsic properties. Intrinsic
// properties make the Object unique whereas extrinsic properties are set by client code and used to perform different
// operations. For example, an Object Circle can have extrinsic properties such as color and width.
//For applying flyweight pattern, we need to create a Flyweight factory that returns the shared objects.
// For our example, lets say we need to create a drawing with lines and Ovals. So we will have an interface Shape
// and its concrete implementations as Line and Oval. Oval class will have intrinsic property to determine whether
// to fillTheCashMachine the Oval with given color or not whereas Line will not have any intrinsic property.
//Notice that I have intensionally introduced delay in creating the Object of concrete classes to make the point that
// flyweight pattern can be used for Objects that takes a lot of time while instantiated.
//The flyweight factory will be used by client programs to instantiate the Object, so we need to keep a map of
// Objects in the factory that should not be accessible by client application. Whenever client program makes a
// call to get an instance of Object, it should be returned from the HashMap, if not found then create a new
// Object and put in the Map and then return it. We need to make sure that all the intrinsic properties are considered
// while creating the Object.
public class DrawingClient extends JFrame{

    private static final long serialVersionUID = -1350200437285282550L;
    private final int WIDTH;
    private final int HEIGHT;

    private static final ShapeFactory.ShapeType shapes[] = { ShapeFactory.ShapeType.LINE, ShapeFactory.ShapeType.OVAL_FILL, ShapeFactory.ShapeType.OVAL_NOFILL };
    private static final Color colors[] = { Color.RED, Color.GREEN, Color.YELLOW };

    public DrawingClient(int width, int height){
        this.WIDTH=width;
        this.HEIGHT=height;
        Container contentPane = getContentPane();

        JButton startButton = new JButton("Draw");
        final JPanel panel = new JPanel();

        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(startButton, BorderLayout.SOUTH);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Graphics g = panel.getGraphics();
                for (int i = 0; i < 20; ++i) {
                    Shape shape = ShapeFactory.getShape(getRandomShape());
                    shape.draw(g, getRandomX(), getRandomY(), getRandomWidth(),
                            getRandomHeight(), getRandomColor());
                }
            }
        });
    }

    private ShapeFactory.ShapeType getRandomShape() {
        return shapes[(int) (Math.random() * shapes.length)];
    }

    private int getRandomX() {
        return (int) (Math.random() * WIDTH);
    }

    private int getRandomY() {
        return (int) (Math.random() * HEIGHT);
    }

    private int getRandomWidth() {
        return (int) (Math.random() * (WIDTH / 10));
    }

    private int getRandomHeight() {
        return (int) (Math.random() * (HEIGHT / 10));
    }

    private Color getRandomColor() {
        return colors[(int) (Math.random() * colors.length)];
    }

    public static void main(String[] args) {
        DrawingClient drawing = new DrawingClient(500,600);
    }
}