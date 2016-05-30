package com.ph.rpg.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ph.rpg.game.Game;
import com.ph.rpg.utils.ClassFileManager;

import java.util.ArrayList;

/**
 * Created by Jock on 23.05.2016.
 */
public class FriendObject extends MovingObject {
    public FriendObject() {
        super(ClassFileManager.friendXML);
        speed = 4;
        width = 100;
        height = 150;
        setCoord(new Vector2(300, -20));
    }


    public void say() {
        System.out.printf("Hejo\n");
    }
}
