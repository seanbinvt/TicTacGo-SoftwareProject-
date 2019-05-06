package com.example.tictactoe;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;

import com.example.tictactoe.AIGamePlayingSolution.src.minimax.Action;
import com.example.tictactoe.AIGamePlayingSolution.src.minimax.AlphaBetaPlayer;
import com.example.tictactoe.AIGamePlayingSolution.src.minimax.MinimaxPlayer;
import com.example.tictactoe.AIGamePlayingSolution.src.tictactoe.TicTacToeState;

import java.util.List;

public class HardPlayer extends Player {

    @Override
    public void move(Drawable id, View view, List<View> spacesLeft, int[] gamePlays, View[] tiles) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                /**
                // Create players
                Player x = new MinimaxPlayer("X");
                Player o = new AlphaBetaPlayer("O");

                // Create a new game
                TicTacToeState state = new TicTacToeState(x, o);

                // While the game isn't in a terminal state
                while (!state.isTerminal()) {

                    // This could be done more succinctly as:
                    // Action move = state.whoseTurn().chooseMove(state)
                    // but I find this to be easier to understand
                    Action move;
                    if (state.whoseTurn() == x) {

                    } else {
                        move = o.chooseMove(state);
                    }

                    // Perform the action and update the game state accordingly
                    state = (TicTacToeState) move.perform();
                    System.out.println(state);

                }
            }**/
        };

                Handler hand = new Handler();
                //hand.postDelayed(r, 1000);
            };
        }
}
