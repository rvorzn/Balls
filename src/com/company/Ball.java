package com.company;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Ball {
    private int positionX;
    private int positionY;

    public synchronized int getPositionX() {
        return positionX;
    }

    public synchronized int getPositionY() {
        return positionY;
    }

    public synchronized void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public synchronized void setPositionY(int positionY) {
        this.positionY = positionY;
    }


}
