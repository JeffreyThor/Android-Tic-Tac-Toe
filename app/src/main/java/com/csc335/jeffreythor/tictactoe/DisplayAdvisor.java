package com.csc335.jeffreythor.tictactoe;

import android.graphics.Bitmap;
import android.util.DisplayMetrics;

/**
 * Created by jeffreythor on 4/3/17.
 */

public class DisplayAdvisor {
    static int maxX;
    static int maxY;
    static float scaleX;
    static float scaleY;
    final static int IDEAL_WIDTH = 600;
    final static int IDEAL_HEIGHT = 1024;

    public static void setScreenDimensions(DisplayMetrics screenDimensions) {
        maxX = screenDimensions.widthPixels;
        maxY = screenDimensions.heightPixels;

        scaleX = (float) maxX/IDEAL_WIDTH;
        scaleY = (float) maxY/IDEAL_HEIGHT;

        scaleX = Math.min(scaleX, scaleY);
        scaleY = scaleX;
    }
}
