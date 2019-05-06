package com.example.tictactoe;

import android.graphics.drawable.Drawable;
import android.view.View;

import java.util.List;

public class HumanPlayer extends Player{

    @Override
    public void move(Drawable id, View view, List<View> spacesLeft, int[] gamePlays, View[] tiles) {
        view.setBackground(id);
        view.setEnabled(false);
    }
}
