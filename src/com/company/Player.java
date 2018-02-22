package com.company;


public class Player implements Runnable {
    Ball ball;
    GameField field;

    long timeToSleep = 1000;

    Player(){
        ball = new Ball();
    }

    public Ball getBall() {
        return ball;
    }

    public void setField(GameField field) {
        this.field = field;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(timeToSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ball.step(field);
        }

    }

}
