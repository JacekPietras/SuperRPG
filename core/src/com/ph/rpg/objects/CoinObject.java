package com.ph.rpg.objects;

import com.ph.rpg.utils.ClassFileManager;

/**
 * Created by Jock on 23.05.2016.
 */
public class CoinObject extends AnimatedObject {
    protected int value = 5;

    public CoinObject() {
        super(ClassFileManager.coinXML);
        width = 10;
        height = 10;
    }

    public CoinObject(String xmlPath) {
        super(xmlPath);
        width = 10;
        height = 10;
    }

    public void collected() {
        MageObject.mainObject.addExperience(value);
//        System.out.printf("Collected! ("+value+")\n");
    }
}
