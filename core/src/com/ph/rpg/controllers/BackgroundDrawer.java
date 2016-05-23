package com.ph.rpg.controllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ph.rpg.managers.scene.SceneManager;

/**
 * Created by Hamish on 2016-05-21.
 */
public class BackgroundDrawer {

    public static BackgroundDrawer instance;

    public static BackgroundDrawer getInstance() {
        if(instance==null){
            instance = new BackgroundDrawer();
        }
        return instance;
    }

    public static void render(SpriteBatch spriteBatch) {
        getInstance().draw(spriteBatch);
    }

    private void draw(SpriteBatch batch) {
        batch.draw(SceneManager.getCurrentScene().getScene(), 0, 0);
    }

    public static void drawBatchesBehindPlayer(SpriteBatch batch, Vector2 player){
        SceneManager.getCurrentScene().drawBatchesBehind(batch, player);
    }

    public static void drawBatchesBeforePlayer(SpriteBatch batch, Vector2 player){
        SceneManager.getCurrentScene().drawBatchesBefore(batch, player);
    }

    public static float getHeight(){
        return SceneManager.getCurrentScene().getScene().getHeight();
    }

    public static float getWidth(){
        return SceneManager.getCurrentScene().getScene().getWidth();
    }

    public Texture getMask() {
        return SceneManager.getCurrentScene().getMask();
    }

    public Texture getTexture(){
        return SceneManager.getCurrentScene().getScene();
    }
}
