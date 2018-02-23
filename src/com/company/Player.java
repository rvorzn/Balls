package com.company;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Player implements Runnable {
    Ball ball;
    GameField field;

    final int TOP = 0, RIGTH = 1, BOTTOM = 2, LEFT = 3;

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

    public  void step(){
        Random random = new Random();
        boolean exit = false;

        Set<Integer> attempt = new HashSet<>();
        do {
            int newX=0, newY=0;
            int positionX, positionY;
            synchronized (field){
                positionX = ball.getPositionX();
                positionY = ball.getPositionY();
            }

            int step = random.nextInt(4);
            switch (step){
                case TOP:
                    newX = positionX;
                    newY = positionY+1;
                    break;
                case RIGTH:
                    newX = positionX+1;
                    newY = positionY;
                    break;
                case BOTTOM:
                    newX = positionX;
                    newY = positionY-1;
                    break;
                case LEFT:
                    newX = positionX-1;
                    newY = positionY;
                    break;
            }
            synchronized (field) {
                if (newX >= 0 && newY >= 0 && newX < field.getSizeX() && newY < field.getSizeY() && field.isEmpty(newX, newY)) {
                    ball.setPositionX(newX);
                    ball.setPositionY(newY);
                    ball.setMove(true);
                    exit = true;
                }else{
                    attempt.add(step);
                    if (attempt.size() == 4) {//если попробывали сходить во все 4 стороны и у нас не вышло
                        return;
                    }
                }
            }
        }while (!exit);

    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(timeToSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            step();
        }

    }



}
