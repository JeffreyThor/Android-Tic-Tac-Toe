package com.csc335.jeffreythor.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity {

    GameSurfaceView surfaceView;
    Point touched = new Point();
    boolean wasTouched;
    public int size;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        wasTouched = false;

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle extras = getIntent().getExtras();
        size = extras.getInt("size", 0);

        surfaceView = new GameSurfaceView(this);

//         Set the view content to our surface view, not R.layout.activity_game
        setContentView(surfaceView);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch(action & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                wasTouched = true;
                touched.x = (int) event.getX();
                touched.y = (int) event.getY();
                break;
        }

        return true;
    }

    public boolean wasScreenTouched(){
        return wasTouched;
    }
    public Point getTouched(){
        wasTouched = false;
        return touched;
    }

    protected void onPause() {
        super.onPause();
        surfaceView.onPause();  // tell surfaceview that it's not running
    }

    protected void onResume() {
        super.onResume();
        surfaceView.onResume();  // tell surfaceview that it is running
    }
}
