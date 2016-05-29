package com.ph.rpg.objects;

import com.ph.rpg.utils.ClassFileManager;

/**
 * Created by Jock on 23.05.2016.
 */
public class EnemyObject extends DrawableObject {
    public EnemyObject() {
        super(ClassFileManager.enemyXML);
        width = 100;
        height = 90;
    }

    public void hit(){
        System.out.print("DOSTALEM!\n");
    }
}
