package com.example.tictactoe;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;

import java.util.List;

public class MediumPlayer extends Player {

    @Override
    public void move(Drawable id, View view, List<View> spacesLeft, int[] gamePlays, View[] tiles) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                // Code goes here for the move
            }
        };
        Handler hand = new Handler();
        hand.postDelayed(r, 1000);
    }
}
