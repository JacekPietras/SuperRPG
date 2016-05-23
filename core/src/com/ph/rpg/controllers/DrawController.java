package com.ph.rpg.controllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ph.rpg.objects.scene.SceneManager;

/**
 * Created by Hamish on 2016-05-21.
 */
public class DrawController {
    public static void render(SpriteBatch spriteBatch, float stateTime) {
        spriteBatch.begin();
        spriteBatch.draw(SceneManager.getCurrentScene().getScene(), 0, 0);
        SceneManager.getCurrentScene().drawObjects(spriteBatch, stateTime);
        spriteBatch.end();
        HUDController.render();
    }
}
