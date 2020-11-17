package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Model {
    private ArrayList<Particle> particles = new ArrayList<>();
    private int rangeMin = 1;
    private int rangeMax;
    Random r = new Random();
    int l;
    int amountParticles;
    boolean [ ] [ ] stuck;

    public Model(int l, int amountParticles, int width, boolean drawCross) {
        this.l = l;
        rangeMax = width-1;
        this.amountParticles = amountParticles;
        stuck = new boolean[rangeMax+2][rangeMax+2];
        if (drawCross) {
            int center = (rangeMax+rangeMin)/2;
            for(int i = 0; i<100; i++){
                stuck[center-i][center+i] = true;
                stuck[center+i][center-i] = true;
                stuck[center-i][center-i] = true;
                stuck[center+i][center+i] = true;

            }
        }
    }

    public ArrayList<Particle> createParticles(){
        for(int i=0;i<amountParticles;i++){
            float x = rangeMin + (rangeMax - rangeMin) * r.nextFloat();
            float y = rangeMin + (rangeMax - rangeMin) * r.nextFloat();
            particles.add(new Particle(x, y));
        }
        return particles;
    }

    public ArrayList<Particle> updateParticles(ArrayList<Particle> particles){
        for(Particle p : particles){
            float phi = (float) (2*(Math.PI) * r.nextFloat());
            if(p.isMoving()){
                float x = p.getX();
                float y = p.getY();
                p.setX((float) (x + l *Math.cos(phi)));
                p.setY((float) (y + l *Math.sin(phi)));
                int intX = (int) p.getX();
                int intY = (int) p.getY();

                if(intX<=rangeMin && intY <=rangeMin) { killParticle(p, rangeMin, rangeMin); continue;}
                if(intX>=rangeMax && intY <=rangeMin) { killParticle(p, rangeMax, rangeMin); continue;}
                if(intX>=rangeMax && intY >=rangeMax) { killParticle(p, rangeMax, rangeMax); continue;}
                if(intX<=rangeMin && intY >=rangeMax) { killParticle(p, rangeMin, rangeMax); continue;}

                if(intX<=rangeMin) { killParticle(p, rangeMin, intY); continue;}
                if(intX>=rangeMax) { killParticle(p, rangeMax, intY); continue;}
                if(intY<=rangeMin) { killParticle(p, intX, rangeMin); continue;}
                if(intY>=rangeMax) { killParticle(p, intX, rangeMax); continue;}

                if (stuck[intX-1][intY]) { killParticle(p, intX, intY); continue;}
                if (stuck[intX+1][intY]) { killParticle(p, intX, intY); continue;}
                if (stuck[intX][intY-1]) { killParticle(p, intX, intY); continue;}
                if (stuck[intX][intY+1]) { killParticle(p, intX, intY); continue;}
                if (stuck[intX-1][intY-1]) { killParticle(p, intX, intY); continue;}
                if (stuck[intX+1][intY-1]) { killParticle(p, intX, intY); continue;}
                if (stuck[intX-1][intY+1]) { killParticle(p, intX, intY); continue;}
                if (stuck[intX+1][intY+1]) { killParticle(p, intX, intY);}
            }
        } return particles;
    }

    public void killParticle(Particle p, int x, int y) {
        p.setX((float) x);
        p.setY((float) y);
        stuck[x][y] = true;
        p.setMoving(false);
    }

    public void setL(int l) {
        this.l = l;
    }
}
