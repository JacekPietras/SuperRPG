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

    public FriendObject(Vector2 destination) {
        super(ClassFileManager.friendXML);
        setCoord(new Vector2(Game.WIDTH+100, Game.HEIGHT/2));
        moveToward(destination);
    }
}
