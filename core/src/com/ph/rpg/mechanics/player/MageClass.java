package com.ph.rpg.mechanics.player;

/**
 * Created by Hamish on 2016-05-20.
 */
public class MageClass extends PlayerClass {

    private static MageClass playerClass = new MageClass();

    public MageClass() {
        super(ClassFileManager.MageXML);
    }

    public static MageClass getPlayerClass(){
        return playerClass;
    }
}
