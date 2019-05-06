package com.example.tictactoe;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;

import java.util.Collections;
import java.util.List;

public class EasyPlayer extends Player {
    private List<View> spaces;

    @Override
    public void move(Drawable id, View view, List<View> spacesLeft, int[] gamePlays, View[] tiles) {
        spaces = spacesLeft;
    }

    private void aiPlay(List<View> spacesLeft, View[] numbers, int[] gamePlays, Drawable id) {
        Collections.shuffle(spacesLeft);
        spacesLeft.get(0).setBackground(id);
        spacesLeft.get(0).setEnabled(false);
        for (int i = 0; i < 9; i++) {
            if (spacesLeft.get(0) == numbers[i]) {
                gamePlays[i] = 2;
            }
        }
    }
}
