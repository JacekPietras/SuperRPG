package com.ph.rpg.mechanics.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ph.rpg.controllers.SceneDrawer;
import com.ph.rpg.managers.scene.PHGate;
import com.ph.rpg.managers.scene.SceneManager;

/**
 * Created by Jock on 23.05.2016.
 */
public class EnemyObject extends DrawableObject {
    public EnemyObject() {
        super(ClassFileManager.enemyXML);
    }
}
