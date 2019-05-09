package com.example.tictactoe.AIGamePlayingSolution.src.minimax;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Player who choose a random move from their possible options
 * 
 * @author jeremy
 * 
 */
public class RandomPlayer extends Player {

	public RandomPlayer(String name) {
		super(name);
	}

	@Override
	public Action chooseMove(State state) {
		// Create set to hold all actions
		SortedSet<Action> moves = new TreeSet<Action>();

		// Loop over successor states
		for (State s : state.successors()) {
			// Set utility for each state to a random positive integer
			moves.add(new Action(s, (int) (Integer.MAX_VALUE * Math.random())));
		}

		// The last move in the tree should be just a random choice since the
		// utilities used to sort the moves are random
		return moves.last();
	}

}
