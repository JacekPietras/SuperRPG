package com.ph.rpg.managers;

/**
 * Created by Hamish on 2016-05-20.
 */
public class GameKeys {

    private static boolean[] keys;
    private static boolean[] pkeys;

    private static final int NUM_KEYS = 1;
    public static final int ESC = 0;

    static {
        keys = new boolean[NUM_KEYS];
        pkeys = new boolean[NUM_KEYS];
    }

    public static void update(){
        for(int i=0;i<NUM_KEYS;i++) {
            pkeys[i] = keys[i];
        }
    }

    public static void setKey(int k, boolean b){
        keys[k] = b;
    }

    public static boolean isDown(int k){
        return keys[k];
    }

    public static boolean isPressed(int k){
        return keys[k] && !pkeys[k];
    }
}
