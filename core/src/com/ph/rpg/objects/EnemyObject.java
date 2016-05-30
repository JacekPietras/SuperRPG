package com.ph.rpg.objects;

import com.badlogic.gdx.math.Vector2;
import com.ph.rpg.utils.ClassFileManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jock on 23.05.2016.
 */
public class EnemyObject extends DrawableObject {
    private final ArrayList<DrawableObject> objects;
    private int experience = 10;
    private float damage = 10f;
    private float life = 100f;


    public EnemyObject(ArrayList<DrawableObject> objects) {
        super(ClassFileManager.enemyXML);
        width = 100;
        height = 90;
        this.objects = objects;
    }

    public float getDamage(){
        return damage;
    }

    public void hit(float damage){
        MageObject.mainObject.addExperience(experience);
        life -= damage;
        System.out.printf("enemy life "+life+"\n");

        if(life<=0){
            System.out.printf("enemy DIED\n");
            objects.remove(this);

            Random r = new Random();
            for(int i=0; i<5; i++) {
                Vector2 pos = currentCoord.cpy();
                pos.add(new Vector2(r.nextInt(width/2)-width/4,r.nextInt(height/2)-height/4));
                CoinObject thing = new CoinObject();
                thing.setCoord(pos);
                objects.add(thing);
            }
        }
    }
}
