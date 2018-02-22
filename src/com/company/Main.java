package com.company;

public class Main {

    public static void main(String[] args) {
	GameField gameField = new GameField(10,10);


	for (int i = 0; i <10 ; i++) {
		gameField.newPlayer(new Player());
	}


	Thread game = new Thread(gameField);
	game.start();
    }
}
