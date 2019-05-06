        package com.example.tictactoe;
        import android.os.Handler;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import com.example.tictactoe.AIGamePlayingSolution.src.minimax.Action;
        import com.example.tictactoe.AIGamePlayingSolution.src.minimax.AlphaBetaPlayer;
        import com.example.tictactoe.AIGamePlayingSolution.src.minimax.MinimaxPlayer;
        import com.example.tictactoe.AIGamePlayingSolution.src.minimax.Player;
        import com.example.tictactoe.AIGamePlayingSolution.src.tictactoe.TicTacToeState;

        import java.util.Arrays;
        import java.util.Collections;
        import java.util.LinkedList;
        import java.util.List;

        import static java.lang.Thread.sleep;

public class GameActivity extends AppCompatActivity {
    private Button exit;

    /**
     * gamePlay is an array with 9 elements, one for each place in tictactoe
     * 0 is no one has played there
     * 1 is player one has played there
     * 2 is player two have played there
     */
    private int[] gamePlays = {0,0,0,0,0,0,0,0,0};

    /** ArrayList of the Image Buttons */
    private View[] numbers = new View[9];


    /** Number of turns that have gone by */
    private int turnNumber;

    /**If the game has a winner then true, if not, then false */
    private boolean winner;

    //---
    /**If the game is in AI mode the set true in code*/
    private String aiMode; //Only manually true for testing

    /**Updates to have all possible moves if in AI mode*/
    private List<View> unsetNumbers;

    TextView output;

    Player x;
    TicTacToeState state;
    private char[][] board = new char[3][3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        aiMode = getIntent().getExtras().getString("com.example.tictactoe.MESSAGE");
        System.out.println(aiMode);
        turnNumber = 0;
        winner = false;

        exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        numbers[0] = findViewById(R.id.one);
        numbers[1] = findViewById(R.id.two);
        numbers[2] = findViewById(R.id.three);
        numbers[3] = findViewById(R.id.four);
        numbers[4] = findViewById(R.id.five);
        numbers[5] = findViewById(R.id.six);
        numbers[6] = findViewById(R.id.seven);
        numbers[7] = findViewById(R.id.eight);
        numbers[8] = findViewById(R.id.nine);

        unsetNumbers = new LinkedList<>(Arrays.asList(numbers));
        output = findViewById(R.id.game_state);
        if (aiMode.equalsIgnoreCase("pvcmed") || aiMode.equalsIgnoreCase("pvchard")) {
            x = new AlphaBetaPlayer("X");

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = '-';
                }
            }
        }
    }

    public void buttonClick(View view) {

        // Make a way to try again/play again

        turnNumber++;

        // Player one's turn
        if ((turnNumber % 2) == 1) {
            view.setBackgroundResource(R.drawable.cat);
            unsetNumbers.remove(view);
            int i;
            for (i = 0; i < 9; i++) {
                if (view == numbers[i]) {
                    gamePlays[i] = 1;
                    unsetNumbers.remove(view);
                    break;
                }
            }
            if (aiMode.equalsIgnoreCase("pvcmed") || aiMode.equalsIgnoreCase("pvchard")) {
                int row;
                int col;
                row = i/3;
                col = i%3;
                board[row][col] = 'O';
                }

            view.setEnabled(false);
            checkWin();
            if(aiMode.equalsIgnoreCase("pvceasy") && winner==false) {
                output.setText("O's turn to play!");

                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        aiEasyPlay();
                    }
                };
                Handler hand = new Handler();
                hand.postDelayed(r, 1000);
            }

            if(aiMode.equalsIgnoreCase("pvcmed") && winner==false) {
                output.setText("O's turn to play!");
                double rand = Math.random();
                Runnable r;
                if (rand < .5) {
                    r = new Runnable() {
                        @Override
                        public void run() {
                            aiEasyPlay();
                        }
                    };
                } else {
                    r = new Runnable() {
                        @Override
                        public void run() {
                            aiHardPlay();
                        }
                    };
                }
                Handler hand = new Handler();
                hand.postDelayed(r, 1000);
            }

            if(aiMode.equalsIgnoreCase("pvchard") && winner==false) {
                output.setText("O's turn to play!");

                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        aiHardPlay();
                    }
                };
                Handler hand = new Handler();
                hand.postDelayed(r, 1000);
            }
        }
        // Player two's turn
        else if (((turnNumber % 2) == 0) && aiMode.equalsIgnoreCase("pvp")) {
            view.setBackgroundResource(R.drawable.dog);

            for (int i = 0; i < 9; i++) {
                if (view == numbers[i]) {
                    gamePlays[i] = 2;
                    //unsetNumbers.remove(view);
                }
            }
            view.setEnabled(false);
            output.setText("X's turn to play!");
            checkWin();
        }
    }


    private void checkWin() {
        checkWinPlayerOne();

        checkWinPlayerTwo();

        checkTie();
    }

    private void checkWinPlayerOne () {
        TextView output = findViewById(R.id.game_state);
        if (gamePlays[0] == 1 && gamePlays[1] == 1 && gamePlays[2] == 1) {
            output.setText("The winner is X!");
            winner = true;
            disableTiles();
        }
        if(gamePlays[0] == 1 && gamePlays[3] == 1 && gamePlays[6] == 1) {
            output.setText("The winner is X!");
            winner = true;
            disableTiles();
        }
        if(gamePlays[2] == 1 && gamePlays[5] == 1 && gamePlays[8] == 1) {
            output.setText("The winner is X!");
            winner = true;
            disableTiles();
        }
        if(gamePlays[6] == 1 && gamePlays[7] == 1 && gamePlays[8] == 1) {
            output.setText("The winner is X!");
            winner = true;
            disableTiles();
        }
        if(gamePlays[3] == 1 && gamePlays[4] == 1 && gamePlays[5] == 1) {
            output.setText("The winner is X!");
            winner = true;
            disableTiles();
        }
        if(gamePlays[1] == 1 && gamePlays[4] == 1 && gamePlays[7] == 1) {
            output.setText("The winner is X!");
            winner = true;
            disableTiles();
        }
        if(gamePlays[0] == 1 && gamePlays[4] == 1 && gamePlays[8] == 1) {
            output.setText("The winner is X!");
            winner = true;
            disableTiles();
        }
        if(gamePlays[6] == 1 && gamePlays[4] == 1 && gamePlays[2] == 1) {
            output.setText("The winner is X!");
            winner = true;
            disableTiles();
        }
    }

    private void checkWinPlayerTwo() {
        TextView output = findViewById(R.id.game_state);
        if (gamePlays[0] == 2 &&  gamePlays[1] == 2 && gamePlays[2] == 2) {
            output.setText("The winner is 0!");
            winner = true;
            disableTiles();
        }
        if(gamePlays[0] == 2 && gamePlays[3] == 2 && gamePlays[6] == 2) {
            output.setText("The winner is O!");
            winner = true;
            disableTiles();
        }
        if(gamePlays[2] == 2 && gamePlays[5] == 2 && gamePlays[8] == 2) {
            output.setText("The winner is O!");
            winner = true;
            disableTiles();
        }
        if(gamePlays[6] == 2 && gamePlays[7] == 2 && gamePlays[8] == 2) {
            output.setText("The winner is O!");
            winner = true;
            disableTiles();
        }
        if(gamePlays[3] == 2 && gamePlays[4] == 2 && gamePlays[5] == 2) {
            output.setText("The winner is O!");
            winner = true;
            disableTiles();
        }
        if(gamePlays[1] == 2 && gamePlays[4] == 2 && gamePlays[7] == 2) {
            output.setText("The winner is O!");
            winner = true;
            disableTiles();
        }
        if(gamePlays[0] == 2 && gamePlays[4] == 2 && gamePlays[8] == 2) {
            output.setText("The winner is O!");
            winner = true;
            disableTiles();
        }
        if(gamePlays[6] == 2 && gamePlays[4] == 2 && gamePlays[2] == 2) {
            output.setText("The winner is O!");
            winner = true;
            disableTiles();
        }
    }

    private void checkTie() {
        TextView output = findViewById(R.id.game_state);
        if(gamePlays[0] != 0 && gamePlays[1] != 0 && gamePlays[2] != 0 && gamePlays[3] != 0 && gamePlays[4] != 0
                && gamePlays[5] != 0 && gamePlays[6] != 0 && gamePlays[7] != 0 && gamePlays[8] != 0 && !winner) {
            output.setText("Tie game!");
        }
    }

    private void disableTiles() {
        for(int i = 0; i < 9; i++) {
            numbers[i].setEnabled(false);
        }
    }

    /*
By default player two is the AI in AI mode.
Easy mode: Choose random open game space.
 */
    private void aiEasyPlay() {
        turnNumber++;
        output.setText("X's turn to play!");
        Collections.shuffle(unsetNumbers);
        unsetNumbers.get(0).setBackgroundResource(R.drawable.dog);
        unsetNumbers.get(0).setEnabled(false);
        for (int i = 0; i < 9; i++) {
            if (unsetNumbers.get(0) == numbers[i]) {
                gamePlays[i] = 2;
            }
        }
        unsetNumbers.remove(0);
        checkWin();
    }

    private void aiHardPlay() {
        turnNumber++;
        output.setText("X's turn to play!");
        state = new TicTacToeState(x, board);
        System.out.println(state);
        Action move = x.chooseMove(state);
        state = (TicTacToeState) move.perform();

        int get = (state.getRow()*3) + state.getCol();

        numbers[get].setBackgroundResource(R.drawable.dog);
        gamePlays[get] = 2;
        numbers[get].setEnabled(false);
        unsetNumbers.remove(numbers[get]);
        board[state.getRow()][state.getCol()] = 'X';
        board = state.getBoard();
        System.out.println(state);
        checkWin();
    }


}
