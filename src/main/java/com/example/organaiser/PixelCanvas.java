package com.example.organaiser;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;

public class PixelCanvas extends Canvas {
    private static final int WIDTH = 4000;
    private static final int HEIGHT = 4000;
    private static final int HEARTHEIGHT = 400;
    private static final int HEARTWIDTH = 400;
    private static final Random random = new Random();

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int i = 400; i <= 500; i++) {
            for (int j = 100; j <= i; j++) {
                g.setColor(randomColor());
                g.drawLine(j, i, j, i);
            }
        }

        for (int i = 400; i >= 100; i--) {
            for (int j = i; j >= 100; j--) {
                g.setColor(randomColor());
                g.drawLine(j, i, j, i);
            }
        }
    }

    public Color randomColor(){
        double blue = 255*Math.random();
        double transperancy = 100*Math.random()+50;
        return new Color(255, 0, (int) blue, (int) transperancy);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setSize(WIDTH, HEIGHT);
        frame.add(new PixelCanvas());

        frame.setVisible(true);
    }
}
