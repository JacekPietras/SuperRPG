package com.ph.rpg.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ph.rpg.game.Game;
import com.ph.rpg.utils.ClassFileManager;

import java.util.ArrayList;

/**
 * Created by Jock on 23.05.2016.
 */
public class ShootObject extends MovingObject {
    private float startTime = 0;
    private ArrayList<DrawableObject> shoots;

    public ShootObject(ArrayList<DrawableObject> shoots, Vector2 destination) {
        super(ClassFileManager.ShootXML);
        setCoord(new Vector2(MovingObject.mainObject.getPosition()));
        currentCoord.add(0, 45);
        startTime = Game.stateTime;
        moveToward(destination);
        speed = 5;

        setFacingLeft(MovingObject.mainObject.facingLeft);
        this.shoots = shoots;
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float stateTime) {
        currentCoord = getNextPosition();
//        currentCoord.add(facingLeft?-4:4, 0);

        if(stateTime - startTime>50 || isIdle()) {
            shoots.remove(shoots.indexOf(this));
            ExplosionObject explosion = new ExplosionObject(shoots);
            explosion.setCoord(currentCoord);
            explosion.currentCoord.add(facingLeft?-15:15,0);
            shoots.add(explosion);
            return;
        }
        super.draw(spriteBatch, stateTime - startTime);
    }
    public Vector2 getPosition() {
        return new Vector2(currentCoord.x,currentCoord.y-100);
    }

    public static void shoot(ArrayList<DrawableObject> shoots, Vector2 destination){
        shoots.add(new ShootObject(shoots, destination));
    }
}
