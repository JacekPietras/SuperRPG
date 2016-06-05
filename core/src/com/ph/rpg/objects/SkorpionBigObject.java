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
public class SkorpionBigObject extends SkorpionObject {


    public SkorpionBigObject() {
        super(ClassFileManager.skorpionbigXML);
        width = 200;
        height = 180;
        speed = 0.3f;
        experience = 50;
        damage = 150f;
        life = 300f;
    }
}
