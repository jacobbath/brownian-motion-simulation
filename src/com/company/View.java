package com.company;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.*;

public class View extends JFrame {
    ArrayList<Particle> particles;
    JTextField speedInput;
    JTextField lInput;
    JButton speedBtn;
    JButton startBtn;
    JButton stopBtn;
    JButton lBtn;
    DrawStuff ds;

    public View(int width, int height) {
        this.setSize(width, height);
        this.setTitle("Brownierörelse");
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel cPanel = new JPanel();
        cPanel.setBackground(Color.darkGray);
        cPanel.setLayout(new GridLayout(3,2));

        speedBtn = new JButton("CHANGE SPEED");
        cPanel.add(speedBtn);
        speedInput = new JTextField("Select speed");
        speedInput.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                speedInput.setText(null);
            }
            public void focusLost(FocusEvent e) {
                speedInput.setText(speedInput.getText());
            }
        });
        cPanel.add(speedInput);

        lBtn = new JButton("CHANGE L");
        cPanel.add(lBtn);
        lInput = new JTextField("Select L");
        lInput.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                lInput.setText(null);
            }
            public void focusLost(FocusEvent e) {
                lInput.setText(lInput.getText());
            }
        });
        cPanel.add(lInput);

        stopBtn = new JButton("STOP");
        cPanel.add(stopBtn);
        startBtn = new JButton("CONTINUE");
        cPanel.add(startBtn);

        ds = new DrawStuff();
        ds.setBackground(Color.lightGray);
        this.add(ds, BorderLayout.CENTER);
        this.add(cPanel, BorderLayout.SOUTH);
    }

    public void moveParticles(ArrayList<Particle> particles){
        ds.removeAll();
        this.particles = particles;
        repaint();
        this.setVisible(true);
    }

    public class DrawStuff extends JPanel {

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2 =(Graphics2D) g;
            //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            for (Particle p : particles) {
                if (!p.isMoving()) {
                    g2.setColor(Color.RED);
                } else { g2.setColor(Color.BLACK);}
                g2.draw(new Rectangle2D.Float(p.getX(), p.getY(), (float) 0.5, (float) 0.5));
            }
        }
    }
}