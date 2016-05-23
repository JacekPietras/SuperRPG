package com.ph.rpg.controllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Hamish on 2016-05-21.
 */
public class DrawController {
    public static void render(SpriteBatch spriteBatch, float stateTime){
        spriteBatch.begin();
        SceneDrawer.render(spriteBatch, stateTime);
        spriteBatch.end();
        HUDDrawer.render();
    }
}
