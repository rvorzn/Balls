package com.company;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Ball {
    private int positionX;
    private int positionY;

    final int TOP = 0, RIGTH = 1, BOTTOM = 2, LEFT = 3;

    public synchronized   int getPositionX() {
        return positionX;
    }

    public synchronized  int getPositionY() {
        return positionY;
    }

    public synchronized  void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public synchronized  void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public  void step(GameField field){
        Random random = new Random();
        boolean exit = false;

        Set<Integer> attempt = new HashSet<>();
        do {
            int newX=0, newY=0;
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
                if (newX >= 0 && newY >= 0 && newX < field.getSizeX() && newY < field.getSizeY() && field.isFiedl(newX, newY)) {
                    field.commitField(positionX, positionY, newX, newY);
                    positionX = newX;
                    positionY = newY;
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
}
