package com.company;

public class Main {

    public static void main(String[] args) {
	GameField gameField = new GameField(10,10);


	for (int i = 0; i <3 ; i++) {
		gameField.newPlayer(new Player());
	}

	gameField.startGame();

	ViewGameField view1 = new ViewGameField(gameField);
	new Thread(view1).start();
    }
}
