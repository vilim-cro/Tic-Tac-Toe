package com.vilim;

public class AI {
	private State state;
	private int[][] initialState = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

	public AI() {
		this.state = new State(initialState);
		state.setCurrentPlayer(1);
	}
	
	public void setBoardValue(int index, String value) {
		int x = index / 3;
		int y = index % 3;
		if (value == "X") {
			state.setGameState(x, y, 1);
			state.setCurrentPlayer(-1);
		} else {
			state.setGameState(x, y, -1);
			state.setCurrentPlayer(1);
		}
	}
	public int getBestMove() {
		if (state.getCurrentPlayer() == 1) {
			maxValue(state);
		} else {
			minValue(state);
		}
		return state.getBestMove();
	}
	
	private State result(State state, int action) {
		int x = action / 3;
		int y = action % 3;
		State s = new State(state.getGameState(), state.getCurrentPlayer());
		s.getGameState()[x][y] = s.getCurrentPlayer();
		return s;
	}
	
	private int maxValue(State state) {
		int bestResult = -2;
		int stateResult = state.checkResult();
		if (stateResult != 2) {
			return stateResult;
		}
		state.setCurrentPlayer(1);
		state.setActions();
		int[] actions = state.getActions();
		for (int a : actions) {
			int minValue = minValue(result(state, a));
			if (minValue > bestResult) {
				bestResult = minValue;
				state.setBestMove(a);
			}
			if (bestResult == 1) {
				return 1;
			}
		}
		return bestResult;
	}
	
	private int minValue(State state) {
		int bestResult = 2;
		int stateResult = state.checkResult();
		if (stateResult != 2) {
			return stateResult;
		}
		state.setCurrentPlayer(-1);
		state.setActions();
		int[] actions = state.getActions();
		for (int a : actions) {
			int maxValue = maxValue(result(state, a));
			if (maxValue < bestResult) {
				bestResult = maxValue;
				state.setBestMove(a);
			}
			if (bestResult == -1) {
				return -1;
			}
		}
		return bestResult;
	}
}
