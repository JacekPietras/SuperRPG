package com.ph.rpg.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ph.rpg.scene.SceneManager;

/**
 * Created by Hamish on 2016-05-21.
 */
public class DrawController {
    public static void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(SceneManager.getCurrentScene().getScene(), 0, 0);
        SceneManager.getCurrentScene().drawObjects(spriteBatch);
        SceneManager.getCurrentScene().checkCollisions();
//        SceneManager.getCurrentScene().drawTexts(spriteBatch);




        spriteBatch.end();
        HUDController.render();
    }
}
