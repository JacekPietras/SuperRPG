package com.ph.rpg.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ph.rpg.game.Game;
import com.ph.rpg.scene.SceneManager;
import com.ph.rpg.utils.ClassFileManager;

import java.util.Random;

/**
 * Created by Jock on 23.05.2016.
 */
public class SkorpionObject extends MovingObject {
    protected int experience = 10;
    protected float damage = 50f;
    protected float life = 100f;
    protected boolean alive = true;
    protected float deadTime = 0;


    public SkorpionObject() {
        super(ClassFileManager.skorpionXML);
        width = 100;
        height = 90;
        speed = 1;
        facingSwitch = true;
    }


    public SkorpionObject(String imagePath) {
        super(imagePath);
        width = 100;
        height = 90;
        speed = 1;
        facingSwitch = true;
    }

    @Override
    public void draw(SpriteBatch batch, float stateTime) {
        if (!alive && deadTime + 0.8f < stateTime) {
            SceneManager.getCurrentScene().removeMeFromScene(this);
        }
        if (alive) {
            moveToward(MageObject.mainObject.currentCoord);
        }
        super.draw(batch, stateTime-deadTime);
    }

    public float getDamage() {
        return damage;
    }

    public void hit(float damage) {
        if(!alive || life<=0) return;
        MageObject.mainObject.addExperience(experience);
        life -= damage;
        System.out.printf("enemy life " + life + "\n");

        if (life <= 0) {
            System.out.printf("enemy DIED\n");
//            SceneManager.getCurrentScene().removeMeFromScene(this);
            alive = false;
            stop();
            currentAnimation = 2;
            deadTime = Game.stateTime;

            Random r = new Random();
            for (int i = 0; i < Math.sqrt(experience); i++) {
                Vector2 pos = currentCoord.cpy();
                pos.add(new Vector2(r.nextInt(width / 2) - width / 4, r.nextInt(height / 2) - height / 4));
                CoinObject thing = new CoinObject();
                thing.setCoord(pos);
                SceneManager.getCurrentScene().addMeToScene(thing);
            }
        }
    }
}
