package com.example.tictactoe.AIGamePlayingSolution.src.minimax;

import com.example.tictactoe.AIGamePlayingSolution.src.tictactoe.TicTacToeState;
import com.example.tictactoe.GameActivity;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Player that uses minimax with alpha-beta pruning to choose its moves
 * 
 * @author jeremy
 * 
 */
public class AlphaBetaPlayer extends Player {

	public AlphaBetaPlayer(String name) {
		super(name);
	}

	@Override
	public Action chooseMove(State state) {
		return alphabeta(state);
	}

	/**
	 * Uses alpha-beta pruning to select an action to perform
	 * 
	 * @param state
	 *            - current game state
	 * @return
	 */
	private Action alphabeta(State state) {
		// Reset number of states visited
		nStatesVisited = 0;
		
		// Use a TreeSet to keep the actions sorted by utility
		SortedSet<Action> moves = new TreeSet<Action>();

		// Explore all successor states
		for (State s : state.successors()) {
			// Call minimax with pruning to get utilty of all possible actions.
			// By default, assume the maximum utility on the subtree (alpha) is
			// -infinity and the minimum utility on the subtree (beta) is
			// +infinity.
			moves.add(new Action(s, minValue(s, Integer.MIN_VALUE,
					Integer.MAX_VALUE)));
		}

		System.out.println("States visited: " + nStatesVisited);
		
		// The last action will have the highest utility
		int row = moves.last().perform().getRow();
		int col = moves.last().perform().getCol();

		//System.out.println(row+ " - "+col+"\n");
		int i = (row*3) + col;
		System.out.println(i + "\n");
		System.out.println(moves.last().perform() + "\n\n");
		return moves.last();
	}

	/**
	 * @param state
	 *            - game state
	 * @param alpha
	 *            - max utility found in the subtree so far
	 * @param beta
	 *            - min utility found in the subtree so far
	 * @return the maximum utility achievable in this subtree
	 */
	private int maxValue(State state, int alpha, int beta) {
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
			// Call min for next level down
			v = Math.max(v, minValue(s, alpha, beta));

			// If this utility is larger than the minimum utility found so far
			// we know this subtree contains no more useful branches to explore
			if (v >= beta) {
				return v;
			}

			// If this utility is larger than the max utility found so far
			// update that max utility
			alpha = Math.max(alpha, v);
		}

		// Return max utility if pruning didn't do that already
		return v;
	}

	/**
	 * @param state
	 *            - game state
	 * @param alpha
	 *            - max utility found in the subtree so far
	 * @param beta
	 *            - min utility found in the subtree so far
	 * @return the minimum utility achievable in this subtree
	 */	
	private int minValue(State state, int alpha, int beta) {
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
			// Call max for next level down
			v = Math.min(v, maxValue(s, alpha, beta));

			// If this utility is smaller than the maximum utility found so far
			// we know this subtree contains no more useful branches to explore
			if (v <= alpha) {
				return v;
			}

			// If this utility is smaller than the min utility found so far
			// update that min utility
			beta = Math.min(beta, v);
		}

		// Return min utility if pruning didn't do that already
		return v;
	}

}
