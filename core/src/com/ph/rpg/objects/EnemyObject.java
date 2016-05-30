package com.ph.rpg.objects;

import com.ph.rpg.utils.ClassFileManager;

/**
 * Created by Jock on 23.05.2016.
 */
public class EnemyObject extends DrawableObject {
    private int experience = 10;
    private float damage = 10f;
    private float life = 100f;


    public EnemyObject() {
        super(ClassFileManager.enemyXML);
        width = 100;
        height = 90;
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
        }
    }
}
