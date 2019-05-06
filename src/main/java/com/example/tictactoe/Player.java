package com.example.tictactoe;

import android.graphics.drawable.Drawable;
import android.view.View;

import java.util.List;

public abstract class Player {

    /**
     * Chooses a place where the player wants to go
     */
    public abstract void move(Drawable id, View view, List<View> spacesLeft, int[] gamePlays, View[] tiles);
}
