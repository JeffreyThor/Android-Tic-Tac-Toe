package com.csc335.jeffreythor.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Created by jeffreythor on 4/3/17.
 */

public class GameSurfaceView extends SurfaceView implements Runnable{
    private final GameActivity gameActivity;
    // note that this is NOT the main UI thread
    // we spawned this thread

    boolean isRunning = false;     // true when activity is active and running
    private Thread thread = null;  // the thread that's doing the drawing.
    Paint paint = new Paint();
    GameBoard board;

    public GameSurfaceView(Context context) {
        super(context);
        gameActivity = (GameActivity) context;
        System.out.println("Size: " + gameActivity.size);
        board = new GameBoard(gameActivity.size);
//        board.boardSize = gameActivity.size;
        board.clearBoard();
        System.out.println("Clearing Board");
    }

    public void run() {
        while (isRunning) {
            // Get the canvas    all your base is are belong to us
            SurfaceHolder surfaceHolder = getHolder();
            if (!surfaceHolder.getSurface().isValid()) {
                continue;
            }
            Canvas canvas = surfaceHolder.lockCanvas();

            if(gameActivity.wasTouched){
                Point p = gameActivity.getTouched();
                board.handleTouch(p);
            }
            drawEverything(canvas);

            // We're going to be doing a lot more things here.

            // Display our canvas
            surfaceHolder.unlockCanvasAndPost(canvas);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawEverything(Canvas canvas) {
        canvas.drawARGB(255, 0, 0, 0);
        for(int i = 1; i < board.boardSize; i++) {
            canvas.drawRect(DisplayAdvisor.maxX / board.boardSize * i - 2, 0, DisplayAdvisor.maxX / board.boardSize * i + 2, DisplayAdvisor.maxY, paint);
            canvas.drawRect(0, DisplayAdvisor.maxY / board.boardSize * i - 2, DisplayAdvisor.maxX, DisplayAdvisor.maxY / board.boardSize * i + 2, paint);
        }
//        canvas.drawRect(DisplayAdvisor.maxX / 3 - 2, 0, DisplayAdvisor.maxX / 3 + 2, DisplayAdvisor.maxY, paint);
//        canvas.drawRect(DisplayAdvisor.maxX/3 * 2 - 2, 0, DisplayAdvisor.maxX/3 * 2 + 2, DisplayAdvisor.maxY, paint);
//        canvas.drawRect(0, DisplayAdvisor.maxY/3 - 2, DisplayAdvisor.maxX, DisplayAdvisor.maxY/3 + 2, paint);
//        canvas.drawRect(0, DisplayAdvisor.maxY/3 * 2 - 2, DisplayAdvisor.maxX, DisplayAdvisor.maxY/3 * 2 + 2, paint);
//        for(int i = 0; i < board.boardSize; i++){
//            for(int j = 0; j < board.boardSize; j++){
//                if(board.gameBoard[i][j] != MoveType.EMPTY) {
//                    //TO-DO
//                    //Draw board.gameBoard[i][j] (X or O) at
//                    //DisplayAdvisor.maxX / 3 * i,
//                    //DisplayAdvisor.maxY / 3 * j
//                    if (board.gameBoard[i][j] == MoveType.O) {
//                        System.out.println("Draw O");
//                        canvas.drawCircle(DisplayAdvisor.maxX / board.boardSize * i + DisplayAdvisor.maxX/(board.boardSize*2), DisplayAdvisor.maxY / board.boardSize * j + DisplayAdvisor.maxY/(board.boardSize*2), 20, paint);
//                    } else if(board.gameBoard[i][j] == MoveType.X){
//                        System.out.println("Draw X");
//                        canvas.drawLine(DisplayAdvisor.maxX / board.boardSize * i, DisplayAdvisor.maxY / board.boardSize * j + DisplayAdvisor.maxY/(board.boardSize*2), DisplayAdvisor.maxX / board.boardSize * (i+1), DisplayAdvisor.maxY / board.boardSize * (j+1), paint);
//                    }
//                }
//            }
//        }
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLUE);
        for (int i = 0; i < board.boardSize; i++) {
            for(int j = 0; j < board.boardSize; j++) {
                if (board.gameBoard[i][j] != MoveType.EMPTY) {
                    if(board.gameBoard[i][j] == MoveType.X) {
                        canvas.drawLine(board.cellWidth * i, board.cellHeight * j, board.cellWidth * (i+1), board.cellHeight * (j+1), paint);
                        canvas.drawLine(board.cellWidth * i, board.cellHeight * (j+1), board.cellWidth * (i+1), board.cellHeight * j, paint);
                    } else if(board.gameBoard[i][j] == MoveType.O) {
                        canvas.drawCircle(board.cellWidth * i + DisplayAdvisor.maxX/(board.boardSize*2), board.cellHeight * j + DisplayAdvisor.maxY/(board.boardSize*2), board.cellWidth / 2, paint);
                    }
                }
            }
        }
        paint.setStyle(Paint.Style.FILL);

        if(board.isGameOver) {
            Intent intent = new Intent(gameActivity, EndGameActivity.class);
            intent.putExtra("winner", board.winner);
            gameActivity.startActivity(intent);
        }

    }

    public void onResume() {
        // We've become active.  Create a thread
        // and start drawing.
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void onPause() {
        // We're no longer active.
        isRunning = false;  // This will stop the loop in run and thread will end.
        while (thread != null) {
            try {
                thread.join();  // Wait for thread to end.
                thread = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
