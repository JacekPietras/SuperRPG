package com.ph.rpg.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ph.rpg.controllers.CameraController;
import com.ph.rpg.controllers.DrawController;
import com.ph.rpg.objects.MageObject;
import com.ph.rpg.managers.GameInputProcessor;
import com.ph.rpg.managers.GameKeys;
import com.ph.rpg.objects.scene.SceneManager;

public class Game implements ApplicationListener {

    public static int WIDTH;
    public static int HEIGHT;

    public static float stateTime = 0f;

    SpriteBatch spriteBatch;

    @Override
    public void create() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();

        CameraController.init();
        spriteBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(new GameInputProcessor());

        new MageObject();

        SceneManager.goToScene(1);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

        GameKeys.update();
        CameraController.update();
        spriteBatch.setProjectionMatrix(CameraController.cam.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();

        DrawController.render(spriteBatch, stateTime);
        CameraController.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
