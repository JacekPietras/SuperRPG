package com.ph.rpg.controllers;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ph.rpg.game.Game;
import com.ph.rpg.managers.player.PlayerManager;
import com.ph.rpg.managers.player.PlayerState;
import com.ph.rpg.managers.scene.SceneManager;
import com.ph.rpg.utils.PHAnimation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hamish on 2016-05-21.
 */
public class PlayerDrawer {

    private Map<PlayerState,PHAnimation> animations = new HashMap();
    private TextureRegion currentFrame;
    public static PlayerDrawer instance;
    private static boolean facingLeft = false;
    private Vector2 position = new Vector2();

    public static PlayerDrawer getInstance() {
        if(instance==null){
            instance = new PlayerDrawer();
            instance.init();
        }
        return instance;
    }

    public void init(){
        for(PHAnimation a : PlayerManager.getPlayerClass().getAnimations()){
            animations.put(a.getType(),a);
        }
    }

    public static void render(SpriteBatch spriteBatch, float stateTime) {
        getInstance().draw(spriteBatch, stateTime);
    }

    public void draw(SpriteBatch spriteBatch, float stateTime){
        currentFrame = getProperAnimation().getAnimation().getKeyFrame(stateTime, true);  // #16

        position = PlayerManager.getNextPosition();
        if(position.x > PlayerManager.getDestination().x && !facingLeft){
            facingLeft = true;
        } else if(position.x < PlayerManager.getDestination().x && facingLeft){
            facingLeft = false;
        }

        float zoom = SceneManager.getCurrentScene().getZoom();

        spriteBatch.draw(currentFrame,
                facingLeft ?
                        position.x+currentFrame.getRegionWidth() :
                        position.x,
                position.y,
                facingLeft ?
                        -currentFrame.getRegionWidth() * zoom:
                        currentFrame.getRegionWidth() * zoom
                ,currentFrame.getRegionHeight() * zoom);             // #17
    }

    private PHAnimation getProperAnimation(){
        return animations.get(PlayerManager.getState());
    }

    public int getPlayerWidth() {
        return getProperAnimation().getAnimation().getKeyFrame(0).getRegionWidth();
    }

    public float getPlayerX(){
        try {
            return position.x + currentFrame.getRegionWidth() / 2;
        } catch(NullPointerException e){
            return position.x;
        }
    }

    public float getPlayerY(){
        try {
            return position.y + currentFrame.getRegionHeight()/2;
        } catch(NullPointerException e){
            return position.y;
        }
    }

    public int getPlayerHeight() {
        return getProperAnimation().getAnimation().getKeyFrame(0).getRegionHeight();
    }

    public static void setFacingLeft(boolean b) {
        facingLeft = b;
    }
}
