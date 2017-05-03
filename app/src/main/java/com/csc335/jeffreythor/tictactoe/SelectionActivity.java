package com.csc335.jeffreythor.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectionActivity extends AppCompatActivity {

    private int boardSizeSelection = 3;
    Button upButton;
    Button selectButton;
    Button downButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        upButton = (Button)findViewById(R.id.upButton);
        selectButton = (Button)findViewById(R.id.selectButton);
        downButton = (Button)findViewById(R.id.downButton);
        selectButton.setText(boardSizeSelection + "x" + boardSizeSelection);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(boardSizeSelection < 7)
                    boardSizeSelection++;
                selectButton.setText(boardSizeSelection + "x" + boardSizeSelection);
            }
        });

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMainActivity = new Intent("com.example.jeffrey.GAMEACTIVITY");
                openMainActivity.putExtra("size", boardSizeSelection);
                startActivity(openMainActivity);
            }
        });

        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(boardSizeSelection > 3)
                    boardSizeSelection--;
                selectButton.setText(boardSizeSelection + "x" + boardSizeSelection);
            }
        });
    }

//    public void upButtonClicked(View v) {
//        if(boardSizeSelection <= 7)
//            boardSizeSelection++;
//        selectButton.setText(boardSizeSelection + "x" + boardSizeSelection);
//    }

//    public void sizeSelectionButtonClicked(View v) {
//        Intent openMainActivity = new Intent("com.example.jeffrey.GAMEACTIVITY");
//        openMainActivity.putExtra("size", boardSizeSelection);
//        startActivity(openMainActivity);
//    }

//    public void downButtonClicked(View v) {
//        if(boardSizeSelection >= 3)
//            boardSizeSelection++;
//        selectButton.setText(boardSizeSelection + "x" + boardSizeSelection);
//    }
}
