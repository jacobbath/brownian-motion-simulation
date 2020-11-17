package com.company;

public class Particle {
    private float x;
    private float y;
    private boolean isMoving;

    public Particle(float x, float y) {
        this.x = x;
        this.y = y;
        isMoving = true;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setMoving(boolean b) {
        isMoving = b;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isMoving() {
        return isMoving;
    }

}
