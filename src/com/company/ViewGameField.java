package com.company;

import java.util.Set;

public class ViewGameField implements Runnable {
    GameField gameField;
    Ball[][] viewField;

    ViewGameField(GameField gameField){
        this.gameField = gameField;
    }

    //получение данных
    private void fillGameFiled(){
        viewField = new Ball[gameField.getSizeX()][gameField.getSizeY()];
        Set<Ball> setBalls;
        synchronized (gameField){ // Синхронизируем по полю в момент  получения данных шаров у поля
           setBalls = gameField.getBalls();
        }
        //установка шаров на нашу матрицу отображения
        for (Ball ball: setBalls) {
            int x = ball.getPositionX();
            int y = ball.getPositionY();
            viewField[x][y] = ball;
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
