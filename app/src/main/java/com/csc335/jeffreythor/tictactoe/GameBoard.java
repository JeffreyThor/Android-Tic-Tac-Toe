package com.csc335.jeffreythor.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;

import java.util.Random;

/**
 * Created by jeffreythor on 4/7/17.
 */

public class GameBoard {
    public int boardSize;
    MoveType move = MoveType.X;
    MoveType[][] gameBoard;
    Random rand = new Random();
    public int cellWidth;
    public int cellHeight;
    int c;
    int r;
    int moveCount = 0;
    public boolean isGameOver = false;
    public String winner;

    public GameBoard(int boardSize) {
        this.boardSize = boardSize;
        gameBoard = new MoveType[boardSize][boardSize];
        cellWidth = DisplayAdvisor.maxX / boardSize;
        cellHeight = DisplayAdvisor.maxY / boardSize;
    }

    public void clearBoard() {
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                gameBoard[i][j] = MoveType.EMPTY;
            }
        }
    }

    public void handleTouch(Point p) {


//        for(int i = 0; i < boardSize ; i++) {
//            if(p.x > DisplayAdvisor.maxX / boardSize * i && p.x < DisplayAdvisor.maxX / boardSize * (i+1)) {
//                for (int j = 0; j < boardSize; j++) {
//                    if(p.y > DisplayAdvisor.maxY / boardSize * j && p.y < DisplayAdvisor.maxY / boardSize * (j+1)) {
//                        if(gameBoard[i][j] == MoveType.EMPTY) {
//                            gameBoard[i][j] = move;
//                            switchMove();
//                        } else {
//                            System.out.println("Space is taken");
//                        }
//                        break;
//                    }
//                }
//            }
//        }
        System.out.println("p.x: " + p.x + " / " + cellWidth);
        System.out.println("p.y: " + p.y + " / " + cellHeight);
        c = p.x / cellWidth;
        r = p.y / cellHeight;

        if(gameBoard[c][r] == MoveType.EMPTY) {
            System.out.println("Placing " + move + " at " + r + ", " + c);
            gameBoard[c][r] = move;
            moveCount++;
            checkConditions(c, r);
            switchMove();
        }


//        if(p.x < DisplayAdvisor.maxX / 3){
//            System.out.println("Placing in first col");
//            if(p.y < DisplayAdvisor.maxY / 3) {
//                if(gameBoard[0][0] == MoveType.EMPTY) {
//                    //store current players move into gameboard
//                    gameBoard[0][0] = move;
//                    switchMove();
//                }
//            } else if(p.y < DisplayAdvisor.maxY / 3 * 2) {
//                if(gameBoard[0][1] == MoveType.EMPTY) {
//                    //store current players move into gameboard
//                    gameBoard[0][1] = move;
//                    switchMove();
//                }
//            } else {
//                if(gameBoard[0][2] == MoveType.EMPTY) {
//                    //store current players move into gameboard
//                    gameBoard[0][2] = move;
//                    switchMove();
//                }
//            }
//        } else if(p.x < DisplayAdvisor.maxX / 3 * 2){
//            System.out.println("Placing in second col");
//            if(p.y < DisplayAdvisor.maxY / 3) {
//                if(gameBoard[1][0] == MoveType.EMPTY) {
//                    //store current players move into gameboard
//                    gameBoard[1][0] = move;
//                    switchMove();
//                }
//            } else if(p.y < DisplayAdvisor.maxY / 3 * 2 && gameBoard[1][1] == MoveType.EMPTY) {
//                if(gameBoard[1][1] == MoveType.EMPTY) {
//                    //store current players move into gameboard
//                    gameBoard[1][1] = move;
//                    switchMove();
//                }
//            } else {
//                if(gameBoard[1][2] == MoveType.EMPTY) {
//                    //store current players move into gameboard
//                    gameBoard[1][2] = move;
//                    switchMove();
//                }
//            }
//        } else {
//            System.out.println("Placing in third col");
//            if(p.y < DisplayAdvisor.maxY / 3) {
//                if(gameBoard[2][0] == MoveType.EMPTY) {
//                    //store current players move into gameboard
//                    gameBoard[2][0] = move;
//                    switchMove();
//                }
//            } else if(p.y < DisplayAdvisor.maxY / 3 * 2) {
//                if(gameBoard[2][1] == MoveType.EMPTY) {
//                    //store current players move into gameboard
//                    gameBoard[2][1] = move;
//                    switchMove();
//                }
//            } else {
//                if(gameBoard[2][2] == MoveType.EMPTY) {
//                    //store current players move into gameboard
//                    gameBoard[2][2] = move;
//                    switchMove();
//                }
//            }
//        }



    }

    public void switchMove() {

        if(move == MoveType.X) {
            System.out.println("Switching to O");
            move = MoveType.O;
        } else if(move == MoveType.O) {
            System.out.println("Switching to X");
            move = MoveType.X;
        }

        computerMove();
    }

    public void computerMove() {
        int x = rand.nextInt(boardSize);
        int y = rand.nextInt(boardSize);
        while(gameBoard[x][y] != MoveType.EMPTY) {
            x = rand.nextInt(boardSize);
            y = rand.nextInt(boardSize);
        }
        if(gameBoard[x][y] == MoveType.EMPTY){
            System.out.println("Setting move at x: " + x + ", y: " + y);
            gameBoard[x][y] = move;
            moveCount++;
        }

        checkConditions(x, y);

        if(move == MoveType.X) {
            System.out.println("Switching to O");
            move = MoveType.O;
        } else if(move == MoveType.O) {
            System.out.println("Switching to X");
            move = MoveType.X;
        }
    }

    public void checkConditions(int x, int y) {
//        //TO-DO
//        //Check win conditions
//        System.out.println("Checking for win");
//        for(int i = 0; i < 2; i++) {
//            if (gameBoard[1][1] == move) { //This whole 'if' only checks for the middle
//                if (gameBoard[0][0] == move) {
//                    if (gameBoard[2][2] == move) {
//                        System.out.println(move + " wins");
//                        //Add intent, show win/lose activity
//                    }
//                } else if (gameBoard[0][1] == move) {
//                    if (gameBoard[2][1] == move) {
//                        System.out.println(move + " wins");
//                        //Add intent, show win/lose activity
//                    }
//                } else if (gameBoard[1][0] == move) {
//                    if (gameBoard[1][2] == move) {
//                        System.out.println(move + " wins");
//                        //Add intent, show win/lose activity
//                    }
//                } else if (gameBoard[2][0] == move) {
//                    if (gameBoard[0][2] == move) {
//                        System.out.println(move + " wins");
//                        //Add intent, show win/lose activity
//                    }
//                }
//            }
//        }


        //check end conditions

        //check col
        for(int i = 0; i < boardSize; i++){
            if(gameBoard[x][i] != move)
                break;
            if(i == boardSize-1){
                System.out.println(move + " wins!");
                isGameOver = true;
                winner = move.name() + " Wins!";
            }
        }

        //check row
        for(int i = 0; i < boardSize; i++){
            if(gameBoard[i][y] != move)
                break;
            if(i == boardSize-1){
                System.out.println(move + " wins!");
                isGameOver = true;
                winner = move.name() + " Wins!";
            }
        }

        //check diag
        if(x == y){
            //we're on a diagonal
            for(int i = 0; i < boardSize; i++){
                if(gameBoard[i][i] != move)
                    break;
                if(i == boardSize-1){
                    System.out.println(move + " wins!");
                    isGameOver = true;
                    winner = move.name() + " Wins!";
                }
            }
        }

        //check anti diag
        if(x + y == boardSize - 1){
            for(int i = 0;i<boardSize;i++){
                if(gameBoard[i][(boardSize-1)-i] != move)
                    break;
                if(i == boardSize-1){
                    System.out.println(move + " wins!");
                    isGameOver = true;
                    winner = move.name() + " Wins!";
                }
            }
        }

        //check draw
        if(moveCount == (Math.pow(boardSize, 2)) && moveCount != 0){
            System.out.println("Draw!");
            isGameOver = true;
            winner = "Draw!";
        }

        System.out.println("Move count: " + moveCount);

    }
}
