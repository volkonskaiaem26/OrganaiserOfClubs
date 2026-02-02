package com.example.organaiser;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Heart extends Applet {


    @Override
    public void paint(Graphics g) {

        Graphics2D g2 = (Graphics2D)g;

        for(int i = 0;i < 400;i++){
            g2.setColor(randomColor());
            g2.fillOval(200 + i, i, 60,350);

            try {
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(Heart.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        for(int i = 0;i < 400;i++){
            g2.setColor(randomColor());
            g2.fillOval(600 + i,400 - i , 60,350);
            try {
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(Heart.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public Color randomColor(){
        double blue = 205*Math.random() + 50;
        double transperancy = 100*Math.random()+50;
        return new Color(255, 0, (int) blue, (int) transperancy);
    }

    public static void main(String[] args) {
        JFrame win = new JFrame();
        win.setVisible(true);
        win.setSize(1366,766);
        win.getContentPane().setBackground(new Color(255,255,255));
        win.getContentPane().add(new Heart());
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
