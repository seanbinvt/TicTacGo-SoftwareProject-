package com.example.tictactoe.AIGamePlayingSolution.src.minimax;

/**
 * Represents an action which, when performed, returns a new game state
 * 
 * @author jeremy
 * 
 */
public class Action implements Comparable<Action> {
	/** State that results when the action is performed */
	private State resultingState;
	
	/** Utility of this state */
	private int utility;

	public Action(State state, int utility) {
		this.resultingState = state;
		this.utility = utility;
	}

	/**
	 * Performs the action for this state
	 *  
	 * @return new game state
	 */
	public State perform() {
		System.out.println(resultingState.getRow() +" --- "+ resultingState.getCol()+"\n\n");
		System.out.println(resultingState);
		return resultingState;
	}

	/**
	 * Actions are compared by their utility
	 */
	@Override
	public int compareTo(Action other) {
		return Integer.compare(this.utility, other.utility);
	}
}
