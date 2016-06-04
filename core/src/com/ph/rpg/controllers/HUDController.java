package com.ph.rpg.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;
import com.ph.rpg.game.Game;
import com.ph.rpg.objects.MageObject;

/**
 * Created by Hamish on 2016-05-21.
 */
public class HUDController {

    private static HUDController instance;

    private SpriteBatch HUDBatch;

    private Texture playerInfo, experienceBar, lifeBar;

    public static HUDController getInstance() {
        if(instance==null){
            instance = new HUDController();
            instance.init();
        }
        return instance;
    }

    public void init(){
        HUDBatch = new SpriteBatch();
        playerInfo = new Texture(Gdx.files.internal("hud/character_info.png"));
        lifeBar = new Texture(Gdx.files.internal("hud/life_bar.png"));
        experienceBar = new Texture(Gdx.files.internal("hud/experience_bar.png"));
    }

    public static void render(){
        HUDController.getInstance().draw();
    }

    private void draw() {

        float lifePercent = ((MageObject)MageObject.mainObject).getLife()/100.f;
        float expPercent = ((MageObject)MageObject.mainObject).getExperience()/(float)((MageObject)MageObject.mainObject).getExperienceToNextLevel();

        HUDBatch.begin();
        HUDBatch.draw(playerInfo, 10, Game.HEIGHT - playerInfo.getHeight()-10);
        if(lifePercent>0);
        HUDBatch.draw(lifeBar,87 , Game.HEIGHT - lifeBar.getHeight()-28,lifeBar.getWidth()*lifePercent,lifeBar.getHeight());
        HUDBatch.draw(experienceBar, 87, Game.HEIGHT - experienceBar.getHeight()-56,experienceBar.getWidth()*expPercent,experienceBar.getHeight());


        GlyphLayout glyphLayout = new GlyphLayout();
        BitmapFont font = new BitmapFont(Gdx.files.internal("arial.fnt"), new TextureRegion(Game.fontTexture), false);
        HUDBatch.setShader(Game.fontShader);
        font.setColor(Color.WHITE);
        font.getData().setScale(0.4f);
        glyphLayout.setText(font, ((MageObject)MageObject.mainObject).getLevel()+"", Color.WHITE, 50, Align.center, true);
        font.draw(HUDBatch, glyphLayout, 190, Game.HEIGHT-61);
        HUDBatch.setShader(null);


        HUDBatch.end();
    }
}
