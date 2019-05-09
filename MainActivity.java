package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.tictactoe.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    public void buttonPlayPVP(View view) {

        Intent intent = new Intent(this, com.example.tictactoe.GameActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "pvp");
        startActivity(intent);
    }

    public void buttonPlayPVC(View view) {

        Intent intent = new Intent(this, com.example.tictactoe.ModeActivity.class);
        startActivity(intent);
    }

    public void info(View view) {
        Intent intent = new Intent(this, com.example.tictactoe.InfoActivity.class);
        startActivity(intent);
    }

    public void closeGame(View view) {
        finish();
    }



}
