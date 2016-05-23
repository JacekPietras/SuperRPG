package com.ph.rpg.managers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.ph.rpg.controllers.CameraController;
import com.ph.rpg.objects.MageObject;

/**
 * Created by Hamish on 2016-05-20.
 */
public class GameInputProcessor extends InputAdapter {

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.ESCAPE){
            GameKeys.setKey(GameKeys.ESC,true);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.ESCAPE){
            GameKeys.setKey(GameKeys.ESC,false);
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 absolutePoint = convertTouchToAbsolute(screenX, screenY);
        MouseManager.setPoint(screenX,screenY);
        if(MageObject.hasFocus()){
            MageObject.mainObject.moveToward(new Vector2(absolutePoint.x, absolutePoint.y));
            MageObject.mainObject.shoot(new Vector2(absolutePoint.x, absolutePoint.y));
        }
        return true;
    }

    private Vector3 convertTouchToAbsolute(int screenX, int screenY) {
        return CameraController.cam.unproject(new Vector3(screenX,screenY,0));
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        CameraController.scroll(amount);
        return true;
    }
}
