package com.ph.rpg.objects;

import com.ph.rpg.utils.ClassFileManager;

/**
 * Created by Jock on 23.05.2016.
 */
public class GemObject extends DrawableObject {
    public GemObject() {
        super(ClassFileManager.gemsXML);
        width = 30;
        height = 30;
    }

    public void collected() {
        System.out.printf("Collected!\n");
    }
}
