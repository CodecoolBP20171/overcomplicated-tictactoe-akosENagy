package com.codecool.enterprise.overcomplicated.model;

public class TictactoeGame {

    private char[] gameState = {'-', '-', '-',
                                '-', '-', '-',
                                '-', '-', '-'};

    public char[] getGameState() {
        return gameState;
    }

    public void setGameState(char[] gameState) {
        this.gameState = gameState;
    }

    public void movePlayer(int move) {
        if (gameState[move] == '-')
            this.gameState[move] = 'X';
    }

    public void moveAI(int move) {
        if (move >= 0 && gameState[move] == '-')
            this.gameState[move] = 'O';
    }
}
