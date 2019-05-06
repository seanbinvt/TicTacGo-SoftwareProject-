package com.example.tictactoe.AIGamePlayingSolution.src.minimax;

import java.util.Collection;

/**
 * Abstract class defining the framework for a state in a two player game
 * 
 * @author jeremy
 * 
 */
public abstract class State {
	public final Player PLAYER_ONE;;
	private Player whoseTurn;

	public State(Player playerOne) {
		this.PLAYER_ONE = playerOne;
		this.whoseTurn = PLAYER_ONE;
	}

	public State(State other) {
		this.PLAYER_ONE = other.PLAYER_ONE;
		this.whoseTurn = other.whoseTurn;
	}

	/**
	 * @return which player's turn it is
	 */
	public Player whoseTurn() {
		return whoseTurn;
	}

	/**
	 * Swap the player whose turn it is
	 */
	public void turnTaken() {
		if (whoseTurn == PLAYER_ONE) {

		} else {
			whoseTurn = PLAYER_ONE;
		}
	}

	/**
	 * @return a collection of states that are accessible by a single move from
	 *         the player whose turn it is
	 */
	public abstract Collection<State> successors();

	/**
	 * @return if the state is an end state for the game
	 */
	public abstract boolean isTerminal();

	/**
	 * Calculates the utility of this state for a given player
	 * 
	 * @param p
	 * @return the state's utility for that player
	 */
	public abstract int utility(Player p);
	
	@Override
	public abstract String toString();


public abstract int getRow();
public abstract int getCol();
}
