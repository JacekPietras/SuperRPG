package com.ph.rpg.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.ph.rpg.game.Game;
import com.ph.rpg.scene.SceneManager;
import com.ph.rpg.utils.ClassFileManager;

import java.util.Random;

/**
 * Created by Jock on 23.05.2016.
 */
public class TipObject extends DrawableObject {

    private String statement = "";

    public void setStatement(String statement){
        this.statement = statement;
    }

    public TipObject() {
        super(ClassFileManager.tipXML);
        width = 190;
        height = 100;
    }

    @Override
    public void draw(SpriteBatch batch, float stateTime) {
        super.draw(batch, stateTime);
        GlyphLayout glyphLayout = new GlyphLayout();


        BitmapFont font = new BitmapFont(Gdx.files.internal("arial.fnt"), new TextureRegion(Game.fontTexture), false);
        batch.setShader(Game.fontShader);
        font.setColor(Color.BLACK);
        font.getData().setScale(0.5f);
        glyphLayout.setText(font, statement, Color.BLACK, width, Align.center, true);
        font.draw(batch, glyphLayout, currentCoord.x-width/2, currentCoord.y+height/2+glyphLayout.height/2+10);
        batch.setShader(null);
    }
}
