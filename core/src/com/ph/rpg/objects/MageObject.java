package com.ph.rpg.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ph.rpg.utils.ClassFileManager;

/**
 * Created by Hamish on 2016-05-21.
 */
public class MageObject extends MovingObject {

    private static boolean hasFocus = true;
    private static AnimatedObject levelUp = new LevelUpObject();

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
        levelUp.draw(spriteBatch,stateTime);

        super.draw(spriteBatch, stateTime);
    }

    public MageObject() {
        super(ClassFileManager.MageXML);
        setAsMainObject();
    }
}
