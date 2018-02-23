package com.company;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Ball {
    private int positionX;
    private int positionY;
    boolean isMove = false;

    Ball(){

    }

    Ball(Ball oldBall){
        this.positionX = oldBall.getPositionX();
        this.positionY = oldBall.getPositionY();
    }

    public  int getPositionX() {
        return positionX;
    }

    public  int getPositionY() {
        return positionY;
    }

    public  void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public  void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public boolean isMove() { return isMove; }

    public void setMove(boolean move) { isMove = move; }
}
