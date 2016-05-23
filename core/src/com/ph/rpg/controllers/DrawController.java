package com.ph.rpg.controllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ph.rpg.managers.player.PlayerManager;

/**
 * Created by Hamish on 2016-05-21.
 */
public class DrawController {

    public static void render(SpriteBatch spriteBatch, float stateTime){

        spriteBatch.begin();
        BackgroundDrawer.render(spriteBatch);
        BackgroundDrawer.drawBatchesBehindPlayer(spriteBatch, PlayerManager.getPosition());
        PlayerDrawer.render(spriteBatch, stateTime);
        BackgroundDrawer.drawBatchesBeforePlayer(spriteBatch, PlayerManager.getPosition());
        spriteBatch.end();
        HUDDrawer.render();
    }
}
