package com.ph.rpg.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ph.rpg.game.Game;

/**
 * Created by Hamish on 2016-05-21.
 */
public class HUDDrawer {

    private static HUDDrawer instance;

    private SpriteBatch HUDBatch;

    private Texture playerInfo, experienceBar;

    public static HUDDrawer getInstance() {
        if(instance==null){
            instance = new HUDDrawer();
            instance.init();
        }
        return instance;
    }

    public void init(){
        HUDBatch = new SpriteBatch();
        playerInfo = new Texture(Gdx.files.internal("hud/character_info.png"));
        experienceBar = new Texture(Gdx.files.internal("hud/experience_bar.png"));
    }

    public static void render(){
        HUDDrawer.getInstance().draw();
    }

    private void draw() {
        HUDBatch.begin();
        HUDBatch.draw(playerInfo, 20, Game.HEIGHT - playerInfo.getHeight() - 20);
        HUDBatch.draw(experienceBar,Game.WIDTH/2 - experienceBar.getWidth()/2, 10);


        HUDBatch.end();
    }
}
