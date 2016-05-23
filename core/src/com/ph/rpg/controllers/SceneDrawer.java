package com.ph.rpg.controllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ph.rpg.managers.scene.SceneManager;

/**
 * Created by Hamish on 2016-05-21.
 */
public class SceneDrawer {
    public static SceneDrawer instance;

    public static SceneDrawer getInstance() {
        if(instance == null){
            instance = new SceneDrawer();
        }
        return instance;
    }

    public static void render(SpriteBatch batch, float stateTime) {
        batch.draw(getTexture(), 0, 0);
        SceneManager.getCurrentScene().drawObjects(batch, stateTime);
    }

    public static float getHeight(){
        return SceneManager.getCurrentScene().getScene().getHeight();
    }

    public static float getWidth(){
        return SceneManager.getCurrentScene().getScene().getWidth();
    }

    public static Texture getMask() {
        return SceneManager.getCurrentScene().getMask();
    }

    public static Texture getTexture(){
        return SceneManager.getCurrentScene().getScene();
    }
}
