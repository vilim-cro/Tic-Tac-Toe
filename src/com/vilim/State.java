package com.vilim;

import java.util.ArrayList;
import java.util.List;

public class State {
	private int[][] gameState = new int[3][3];
	private int[] acts;
	private int currentPlayer;
	private int bestMove;

	public State(int[][] gamestate) {
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				this.gameState[i][j] = gamestate[i][j];
			}
		}
	}
	
	public State(int[][] gameState, int currentPlayer) {
		this.currentPlayer = currentPlayer;
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				this.gameState[i][j] = gameState[i][j];
			}
		}
	}
	
	public int[] getActions() {
		return acts;
	}
	
	public void setActions() {
		List<Integer> actions = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if (gameState[i][j] == 0) {
					actions.add(j + 3 * i);
				}
			}
		}
		acts = new int[actions.size()];
		for (int i = 0; i < actions.size(); i++) {
			acts[i] = actions.get(i);
		}
	}
	
	public int checkResult() {
        for (int i = 0; i < 3; i++){
            if (gameState[i][0] == (currentPlayer) &&
                    gameState[i][1] == (currentPlayer) &&
                    gameState[i][2] == (currentPlayer))
                return currentPlayer;
        }
        for (int i = 0; i < 3; i++){
            if (gameState[0][i] == (currentPlayer) &&
                    gameState[1][i] == (currentPlayer) &&
                    gameState[2][i] == (currentPlayer))
            	return currentPlayer;

        }
        if ((gameState[0][0] == (currentPlayer) &&
                gameState[1][1] == (currentPlayer) &&
                gameState[2][2] == (currentPlayer)) || (
                gameState[2][0] == (currentPlayer) &&
                gameState[1][1] == (currentPlayer) &&
                gameState[0][2] == (currentPlayer)))
        	return currentPlayer;
        
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (gameState[i][j] == 0) {
					return 2;
				}
			}
		}

        return 0;
    }

	public int[][] getGameState() {
		return gameState;
	}
	
	public void setGameState(int x, int y, int value) {
		gameState[x][y] = value;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer= currentPlayer; 
	}

	public int getBestMove() {
		return bestMove;
	}

	public void setBestMove(int bestMove) {
		this.bestMove = bestMove;
	}

	public void print() {
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if (gameState[i][j] == 1) {
					System.out.print("X ");
				} else if (gameState[i][j] == -1) {
					System.out.print("O ");
				} else {
					System.out.print("  ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
