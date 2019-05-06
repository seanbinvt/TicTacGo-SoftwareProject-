package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ModeActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.tictactoe.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);
    }

    public void buttonEasyClick(View view) {
        Intent intent = new Intent(this, com.example.tictactoe.GameActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "pvceasy");
        startActivity(intent);
    }

    public void buttonMediumClick(View view) {
        Intent intent = new Intent(this, com.example.tictactoe.GameActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "pvcmed");
        startActivity(intent);
    }

    public void buttonHardClick(View view) {
        Intent intent = new Intent(this, com.example.tictactoe.GameActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "pvchard");
        startActivity(intent);
    }

    public void backButton(View view) {
        finish();
    }

}
