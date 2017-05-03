package com.csc335.jeffreythor.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class StartGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        DisplayAdvisor.setScreenDimensions(metrics);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                } finally {
                    Intent openMainActivity = new Intent("com.example.jeffrey.SELECTIONACTIVITY");
                    startActivity(openMainActivity);
                }
            }
        };

        timer.start();
    }
}
