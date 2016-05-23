package com.ph.rpg.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ph.rpg.game.Game;
import com.ph.rpg.utils.ClassFileManager;

/**
 * Created by Jock on 23.05.2016.
 */
public class LevelUpObject extends AnimatedObject {
    private float startTime = 0;
    private boolean active = false;

    public LevelUpObject() {
        super(ClassFileManager.LevelUpXML);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float stateTime) {
        if (active) {
            setCoord(new Vector2(MovingObject.mainObject.getPosition()));
            currentCoord.add(0,-50);
            super.draw(spriteBatch, stateTime- startTime);
            if(stateTime-startTime>8*8*8/1000.){
                active = false;
            }
        }
    }

    public void activate(){
        startTime = Game.stateTime;
        active = true;
    }
    public Vector2 getPosition() {
        return new Vector2(0,500);
    }
}
