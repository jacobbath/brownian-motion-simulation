package com.company;

public class Main {

    public static void main(String[] args) {
        int l = 1;
        int width = 600;
        int height = width+150;
        int amountParticles = 10000;
        int period = 100;
        boolean drawCross = true;

        View theView = new View(width, height);
        Model theModel = new Model(l, amountParticles, width, drawCross);
        new Controller(theView, theModel, period);
    }
}
