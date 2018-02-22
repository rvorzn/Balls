package com.company;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameField {
    int sizeX;
    int sizeY;
    Set<Ball> balls = new HashSet<>();
    Set<Player> players = new HashSet<>();


    GameField(int sizeX, int  sizeY){
        this.sizeX  =sizeX;
        this.sizeY = sizeY;

    }

    public Set<Ball> getBalls() {
        return balls;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public synchronized boolean isEmpty(int x, int y){
        if (balls.size() == 0) return true;

        boolean result = true;
        for (Ball ball:balls) {
             if (ball.getPositionX() == x && ball.getPositionY()==y){
                 result = false;
             }
        }
        return  result;
    }


    public void newPlayer(Player player){
        player.setField(this);
        players.add(player);
        Ball newBall = player.getBall();

        Random random = new Random();
        int x;
        int y;
        do {
            x =random.nextInt(sizeX);
            y = random.nextInt(sizeY);
        }while (!(isEmpty(x, y) == true));
        newBall.setPositionX(x);
        newBall.setPositionY(y);

        balls.add(newBall);
    }




    public void startGame() {
        for (Player player : players) {
            Thread thread = new Thread(player);
            thread.start();
        }

    }
}
