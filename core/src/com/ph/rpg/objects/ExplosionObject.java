package com.ph.rpg.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ph.rpg.game.Game;
import com.ph.rpg.utils.ClassFileManager;

import java.util.ArrayList;

/**
 * Created by Jock on 23.05.2016.
 */
public class ExplosionObject extends AnimatedObject {
    private float startTime = 0;
    private ArrayList<DrawableObject> shoots;

    public ExplosionObject(ArrayList<DrawableObject> shoots) {
        super(ClassFileManager.ExplosionXML);
        startTime = Game.stateTime;
        this.shoots = shoots;
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float stateTime) {
        if (stateTime - startTime > .5) {
            shoots.remove(shoots.indexOf(this));
            return;
        }
        super.draw(spriteBatch, stateTime - startTime);
    }
    public Vector2 getPosition() {
        return new Vector2(currentCoord.x,currentCoord.y-30);
    }
}
