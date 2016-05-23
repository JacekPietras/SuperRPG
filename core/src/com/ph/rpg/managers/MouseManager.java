package com.ph.rpg.managers;

/**
 * Created by Hamish on 2016-05-20.
 */
public class MouseManager {

    private static int x;
    private static int y;

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static void setPoint(int _x, int _y){
        x = _x;
        y = _y;
    }
}
