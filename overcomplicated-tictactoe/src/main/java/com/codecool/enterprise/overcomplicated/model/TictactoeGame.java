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

    public boolean isOver() {
        return (playerHasWon('X') || playerHasWon('O') || !new String(gameState).contains("-"));
    }

    public String getEndGameMsg() {
        String message;

        if (playerHasWon('X')) {
            message = "You won!";
        }
        else if (playerHasWon('O')) {
            message = "You lost!";
        }
        else {
            message = "It's a tie!";
        }

        return message;
    }

    private boolean playerHasWon(char c) {

        // check rows
        for (int i = 0; i < 2; i++) {
            if (gameState[3 * i] == c && gameState[3 * i + 1] == c && gameState[3 * i + 2] == c)
                return true;

        }

        //check columns
        for (int i = 0; i < 2; i++) {
            if (gameState[i] == c && gameState[i + 3] == c && gameState[i + 6] == c)
                return true;
        }

        //check diagonals
        if (gameState[0] == c && gameState[4] == c && gameState[8] == c)
            return true;

        if (gameState[2] == c && gameState[4] == c && gameState[6] == c)
            return true;


        //not winning.
        return false;
    }
}
