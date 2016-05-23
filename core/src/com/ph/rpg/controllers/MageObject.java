package com.ph.rpg.controllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ph.rpg.mechanics.player.ClassFileManager;
import com.ph.rpg.mechanics.player.MovingObject;

/**
 * Created by Hamish on 2016-05-21.
 */
public class MageObject extends MovingObject {

    private static boolean hasFocus = true;

    public static boolean hasFocus() {
        return hasFocus;
    }



    @Override
    public void draw(SpriteBatch spriteBatch, float stateTime) {
        currentCoord = getNextPosition();
        if (currentCoord.x > getDestination().x && !facingLeft) {
            facingLeft = true;
        } else if (currentCoord.x < getDestination().x && facingLeft) {
            facingLeft = false;
        }
        super.draw(spriteBatch, stateTime);
    }

    public MageObject() {
        super(ClassFileManager.MageXML);
        setAsMainObject();
    }
}
