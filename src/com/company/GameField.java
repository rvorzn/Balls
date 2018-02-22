package com.company;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameField implements Runnable {
    int sizeX;
    int sizeY;
    Set<Ball> balls = new HashSet<>();
    Set<Player> players = new HashSet<>();
    boolean[][] field ;



    GameField(int sizeX, int  sizeY){
        this.sizeX  =sizeX;
        this.sizeY = sizeY;
        field = new boolean[sizeX][sizeY];
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public synchronized boolean isFiedl(int x, int y){
        return  (field[x][y] == false) ? true : false;
    }

    public synchronized void commitField(int oldX, int oldY, int newX, int newY){
            field[oldX][oldY] = false;
            field[newX][newY] = true;
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
        }while (!(field[x][y] == false));
        newBall.setPositionX(x);
        newBall.setPositionY(y);

        balls.add(newBall);
    }

    public synchronized void viewPole(){
        for (int i = 0; i <sizeY ; i++) {
            for (int j = 0; j <sizeY ; j++) {
                System.out.print((field[i][j]) ? "0" : "-"  );
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------");
    }


    @Override
    public void run() {
        for (Player player : players) {
            Thread thread = new Thread(player);
            thread.start();
        }

        while (true){
                viewPole();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
}
