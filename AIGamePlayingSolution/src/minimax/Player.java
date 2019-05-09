package com.example.tictactoe.AIGamePlayingSolution.src.minimax;

/**
 * Abstract class defining the framework for a player in a two-player game
 * 
 * @author jeremy
 * 
 */
public abstract class Player {
	/** Number of states visited, used for testing */
	protected int nStatesVisited = 0;
	
	private String name;

	public Player(String name) {
		this.name = name;
	}

	/**
	 * Given a game state returns the Action chosen by this player. Concrete
	 * classes extending this one will implement a strategy for choosing the
	 * Action to perform
	 * 
	 * @param state
	 * @return the Action chosen by the player
	 */
	public abstract Action chooseMove(State state);

	@Override
	public String toString() {
		return name;
	}
}
