package com.company;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    private View theView;
    private Model theModel;
    private ArrayList<Particle> particles;
    private boolean isActive;
    private int period;
    Timer timer = new Timer();
    TimerTask task = new TimerTask() { public void run(){ updateView(); }};

    public Controller(View theView, Model theModel, int period) {
        this.period = period;
        this.theView = theView;
        this.theModel = theModel;
        particles = theModel.createParticles();
        this.start(timer, task, this.period);

        theView.speedBtn.addActionListener(e -> {
            try {
                this.period = Integer.parseInt(theView.speedInput.getText());
                stopMoving();
                continueMoving(this.period);
            } catch (NumberFormatException error) {
                theView.speedInput.setText("Has to be an integer!");
            }
        });
        theView.stopBtn.addActionListener(e -> {
            if (isActive) {
                stopMoving();
            }
        });
        theView.startBtn.addActionListener(e -> {
            if (!isActive) {
                continueMoving(this.period);
            }
        });
        theView.lBtn.addActionListener(e -> {
            try {
                theModel.setL(Integer.parseInt(theView.lInput.getText()));
            } catch (NumberFormatException error) {
                theView.lInput.setText("Has to be an integer!");
            }
        });

    }

    public void updateView() {
        theView.moveParticles(theModel.updateParticles(particles));
    }

    public void start(Timer t, TimerTask task, int period){
        isActive = true;
        t.scheduleAtFixedRate(task, 1, period);
    }

    public void stopMoving() {
        isActive = false;
        timer.cancel();
        task.cancel();
    }

    public void continueMoving(int period) {
        timer = new Timer();
        task = new TimerTask() { public void run(){ updateView(); }};
        start(timer, task, period);
    }

}
