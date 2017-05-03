package com.csc335.jeffreythor.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EndGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        Bundle extras = getIntent().getExtras();
        String message = extras.getString("winner");

        TextView textView = (TextView) findViewById(R.id.victoryText);
        textView.setText(message);
    }
}
