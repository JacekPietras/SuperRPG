package com.ph.rpg.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ph.rpg.game.Game;
import com.ph.rpg.utils.ClassFileManager;

import java.util.ArrayList;

/**
 * Created by Hamish on 2016-05-21.
 */
public class MageObject extends MovingObject {

    private static float lastShotTime = 0;
    private static boolean hasFocus = true;
    private static LevelUpObject levelUp = new LevelUpObject();
    private static BloodObject blood = new BloodObject();
    private static ArrayList<DrawableObject> objects = new ArrayList<DrawableObject>();

    private static int experience = 0;
    private static float damage = 20;
    private static int level = 1;
    private static int experienceToNextLevel = 30;

    public static float getLife() {
        return life;
    }

    public static int getExperienceToNextLevel() {
        return experienceToNextLevel;
    }

    public static int getLevel() {
        return level;
    }

    public static int getExperience() {
        return experience;
    }

    private static float life = 100f;

    public static void addExperience(int toAdd) {
        experience += toAdd;
        if (experience >= experienceToNextLevel) {
            experienceToNextLevel *= 5;
            mainObject.levelUp();
        }
        System.out.printf("level "+level+" experience "+experience+"/"+experienceToNextLevel+"\n");
    }

    public float getDamage(){
        return damage;
    }

    public static ArrayList<DrawableObject> getDrawableObjects() {
        return objects;
    }

    public static boolean hasFocus() {
        return hasFocus;
    }


    public MageObject() {
        super(ClassFileManager.MageXML);
        objects.clear();
        objects.add(levelUp);
        objects.add(blood);
        objects.add(this);
        setAsMainObject();
        width = 40;
        height = 90;
    }

    public void hit(float damage) {
        if(blood.activate()){
            Game.punch();
            life -= damage;
            System.out.printf("life "+life+"\n");

            if(life<=0){
                System.out.printf("DIED\n");
            }
        }
    }

    public void levelUp() {
        level++;
        damage *= 1.5;
        levelUp.activate();
    }

    public void shoot(Vector2 destination) {
        if (lastShotTime + .5 > Game.stateTime) return;
        lastShotTime = Game.stateTime;
        ShootObject.shoot(objects, destination, damage);
    }
}
