package com.example.tictactoe.AIGamePlayingSolution.src.tictactoe;

import java.util.Collection;
import java.util.HashSet;

import com.example.tictactoe.AIGamePlayingSolution.src.minimax.Player;
import com.example.tictactoe.AIGamePlayingSolution.src.minimax.State;
import com.example.tictactoe.GameActivity;

/**
 * Represents a state in a tic-tac-toe game
 * 
 * @author jeremy
 * 
 */
public class TicTacToeState extends State {
	/** Size of board */
	private static final int SIZE = 3;

	/** char to use for first player */
	private static final char MARKER_ONE = 'X';

	/** char to use for second player */
	private static final char MARKER_TWO = 'O';

	/** char to use for empty spaces */
	private static final char EMPTY = '-';

	/** Game board */
	private char[][] board = new char[SIZE][SIZE];

	int row;
	int col;
	/**
	 * Copies another game state
	 *
	 * @param other
	 */
	public TicTacToeState(TicTacToeState other) {
		super(other);

		// Copy board
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				this.board[i][j] = other.board[i][j];
			}
		}
	}

	public TicTacToeState(Player xPlayer, Player oPlayer, char[][] board) {
		super(xPlayer, oPlayer);
		this.board = board;
	}

	/**
	 * Takes the current state and places the ma
	 * 
	 * @param row
	 *            - row to place marker
	 * @param col
	 *            - column to place marker
	 * @param marker
	 *            - marker to place
	 * @return
	 */
	private TicTacToeState makeMove(int row, int col, char marker) {
		this.row = row;
		this.col = col;
		//System.out.println(row +"-"+col);
		if (marker != MARKER_ONE && marker != MARKER_TWO) {
			throw new IllegalArgumentException(marker + " must be either "
					+ MARKER_ONE + " or " + MARKER_TWO);
		}

		// Clone state
		TicTacToeState successor = new TicTacToeState(this);

		// Place new marker
		successor.board[row][col] = marker;

		// Change turns
		successor.turnTaken();

		return successor;
	}

	public int getI(int row, int col) {
		int i = (row*3) + col;
		return i;
	}

	public char[][] getBoard() {
		return this.board;
	}

	public int getRow() {
		return this.row;
	}

	public int getCol() {
		return this.col;
	}

	@Override
	public Collection<State> successors() {
		Collection<State> successors = new HashSet<State>();

		// Find out whose move it is
		final char MARKER;
		if (whoseTurn() == PLAYER_ONE) {
			MARKER = MARKER_ONE;
		} else {
			MARKER = MARKER_TWO;
		}

		// Loop through all the spots on the board. When an unoccupied space is
		// found, copy the current game board, place the player's marker in the
		// empty spot, and add to the list of successors
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (board[i][j] == EMPTY) {
					TicTacToeState successor = this.makeMove(i, j, MARKER);
					successors.add(successor);
				}
			}
		}

		return successors;
	}

	/**
	 * @return if the first player wins the game
	 */
	private boolean playerOneWins() {
		return winner(MARKER_ONE);
	}

	/**
	 * @return if the second player wins the game
	 */
	private boolean playerTwoWins() {
		return winner(MARKER_TWO);
	}

	/**
	 * 
	 * @return
	 */
	private boolean boardIsFull() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (board[i][j] == EMPTY) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * 
	 * @param c - player's marker
	 * @return if that player has won the game
	 */
	private boolean winner(char c) {
		// Check rows
		for (int i = 0; i < SIZE; i++) {
			if (board[i][0] == c && board[i][1] == c && board[i][2] == c) {
				return true;
			}
		}

		// Check columns
		for (int i = 0; i < SIZE; i++) {
			if (board[0][i] == c && board[1][i] == c && board[2][i] == c) {
				return true;
			}
		}

		// Check first diagonal
		if (board[0][0] == c && board[1][1] == c && board[2][2] == c) {
			return true;
		}

		// Check second diagonal
		if (board[0][2] == c && board[1][1] == c && board[2][0] == c) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isTerminal() {
		// If the first player hasn't won and the second player hasn't won and
		// the board is full we know the game is a draw
		return playerOneWins() || playerTwoWins() || boardIsFull();
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	@Override
	public int utility(Player p) {
		if (p == PLAYER_ONE) {
			if (playerOneWins()) {
				return 1;
			} else if (playerTwoWins()) {
				return -1;
			} else {
				return 0;
			}
		} else if (p == PLAYER_TWO) {
			if (playerOneWins()) {
				return -1;
			} else if (playerTwoWins()) {
				return 1;
			} else {
				return 0;
			}
		} else {
			throw new IllegalArgumentException(p + " is not a valid player");
		}
	}


	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				s = s + board[i][j];
			}

			s = s + '\n';
		}

		return s;
	}

}
