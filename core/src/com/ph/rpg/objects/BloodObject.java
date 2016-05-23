package com.ph.rpg.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ph.rpg.game.Game;
import com.ph.rpg.utils.ClassFileManager;

/**
 * Created by Jock on 23.05.2016.
 */
public class BloodObject extends AnimatedObject {
    private float startTime = 0;
    private boolean active = false;

    public BloodObject() {
        super(ClassFileManager.BloodXML);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float stateTime) {
        if (active) {
            super.draw(spriteBatch, stateTime- startTime);
            if(stateTime-startTime>6*8*8/1000.){
                active = false;
            }
        }
    }

    public void activate(){
        startTime = Game.stateTime;
        active = true;
        setCoord(new Vector2(MovingObject.mainObject.getPosition()));
        currentCoord.add(-20,-150);
    }
}
