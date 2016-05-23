package com.ph.rpg.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ph.rpg.utils.ClassFileManager;

import java.util.ArrayList;

/**
 * Created by Hamish on 2016-05-21.
 */
public class MageObject extends MovingObject {

    private static boolean hasFocus = true;
    private static LevelUpObject levelUp = new LevelUpObject();
    private static BloodObject blood = new BloodObject();
    private static ArrayList<DrawableObject> objects = new ArrayList<DrawableObject>();

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
    }

    public void hit(){
        blood.activate();
    }

    public void levelUp(){
        levelUp.activate();
    }

    public void shoot(Vector2 destination){
        ShootObject.shoot(objects, destination);
    }
}
