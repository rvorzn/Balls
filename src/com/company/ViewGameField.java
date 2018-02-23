package com.company;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ViewGameField implements Runnable {
    GameField gameField;
    Ball[][] viewField ;

    ViewGameField(GameField gameField){
        this.gameField = gameField;
    }

    //получение данных
    private void fillGameFiled(){
        viewField = new Ball[gameField.getSizeX()][gameField.getSizeY()];
        synchronized (gameField) {
            for (Ball ball: gameField.getBalls()) {
                int x = ball.getPositionX();
                int y = ball.getPositionY();
                viewField[x][y] = ball;
            }
        }
    }

    private  void refreshGameField(){
        for (int i = 0; i < gameField.getSizeX() ; i++) {
            for (int j = 0; j < gameField.getSizeY(); j++) {

                Ball tmpBall = viewField[i][j];
                int newX = 0, newY = 0;
                if (tmpBall != null) {
                    synchronized (gameField) {
                        for (Ball ball : gameField.getBalls()) {
                            if (ball.equals(tmpBall) && ball.isMove) {
                                newX = ball.getPositionX();
                                newY = ball.getPositionY();
                            }
                        }
                    }
                }
                viewField[i][j] = null;
                viewField[newX][newY] = tmpBall;

            }
        }
    }

    //отрисовка матрицы игрового поля
    private void printGameField(){
        for (int i = 0; i < gameField.getSizeX() ; i++) {
            for (int j = 0; j <gameField.getSizeY() ; j++) {
                System.out.print((viewField[i][j] == null) ? "-" : "0"  );
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------");
    }

    @Override
    public void run() {
        while (true){
            fillGameFiled(); // сбор данных
            printGameField(); //отрисовка

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
