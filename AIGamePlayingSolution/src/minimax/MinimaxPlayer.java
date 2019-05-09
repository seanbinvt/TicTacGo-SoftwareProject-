package com.example.tictactoe.AIGamePlayingSolution.src.minimax;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Player that uses the minimax algorithm to choose moves
 * 
 * @author jeremy
 * 
 */
public class MinimaxPlayer extends Player {

	public MinimaxPlayer(String name) {
		super(name);
	}

	@Override
	public Action chooseMove(State state) {
		return minimax(state);
	}

	/**
	 * Uses minimax pruning to select an action to perform
	 * 
	 * @param state
	 *            - current game state
	 * @return
	 */
	private Action minimax(State state) {
		// Reset number of states visited
		nStatesVisited = 0;
		
		// Use a TreeSet to keep the actions sorted by utility
		SortedSet<Action> moves = new TreeSet<Action>();

		// Explore all successor states
		for (State s : state.successors()) {
			moves.add(new Action(s, minValue(s)));
		}

		System.out.println("States visited: " + nStatesVisited);
		
		// The last action will have the highest utility
		return moves.last();
	}

	/**
	 * @param state
	 *            - game state
	 * @return the maximum utility achievable in this subtree
	 */
	private int maxValue(State state) {
		// Add to number of states visited
		nStatesVisited++;
		
		// If state is terminal return the utility to this player		
		if (state.isTerminal()) {
			return state.utility(this);
		}
		
		// By default assume the maximum utility is -infinity
		int v = Integer.MIN_VALUE;

		// Loop over successor states
		for (State s : state.successors()) {
			// Call min for the next level down
			v = Math.max(v, minValue(s));
		}

		// Return max utility
		return v;
	}
	
	/**
	 * @param state
	 *            - game state
	 * @return the minimum utility achievable in this subtree
	 */
	private int minValue(State state) {
		// Add to number of states visited
		nStatesVisited++;
		
		// If state is terminal return the utility to this player
		if (state.isTerminal()) {
			return state.utility(this);
		}

		// By default assume the minimum utility is infinity
		int v = Integer.MAX_VALUE;

		// Loop over successor states
		for (State s : state.successors()) {
			v = Math.min(v, maxValue(s));
		}

		// Return min utility
		return v;
	}

}
