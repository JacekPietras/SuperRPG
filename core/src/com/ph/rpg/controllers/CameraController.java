package com.ph.rpg.controllers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ph.rpg.game.Game;

/**
 * Created by Hamish on 2016-05-21.
 */
public class CameraController {

    public static OrthographicCamera cam;
    static boolean print = false;

    public static void init() {
		cam = new OrthographicCamera(Game.WIDTH, Game.HEIGHT);
		cam.translate(Game.WIDTH / 2, Game.HEIGHT/2);
		cam.update();
    }

    public static void scroll(int amount) {
        cam.zoom += amount/10.0f;
        cam.zoom = MathUtils.clamp(cam.zoom, 0.3f, 2.0f);

        float newViewportHeight = cam.viewportHeight * cam.zoom;
        float newViewportWidth  = cam.viewportWidth  * cam.zoom;

        if(newViewportHeight>BackgroundDrawer.getHeight()){
            newViewportHeight = BackgroundDrawer.getHeight();
            cam.zoom = newViewportHeight/cam.viewportHeight;
            newViewportWidth  = cam.viewportWidth  * cam.zoom;
        } else if(newViewportWidth>BackgroundDrawer.getWidth()){
            newViewportWidth=BackgroundDrawer.getWidth();
            cam.zoom = newViewportWidth/cam.viewportWidth;
            newViewportHeight = cam.viewportHeight * cam.zoom;
        }

        setUpCameraPosition(cam.position.x, cam.position.y, newViewportWidth, newViewportHeight);
    }

    private static void setUpCameraPosition(float camX, float camY, float viewportWidth, float viewportHeight) {
        float effectiveViewportWidth = viewportWidth * cam.zoom;
        float effectiveViewportHeight = viewportHeight * cam.zoom;

        cam.position.x = MathUtils.clamp(camX, effectiveViewportWidth/2, BackgroundDrawer.getWidth() - effectiveViewportWidth/2);
        cam.position.y = MathUtils.clamp(camY, effectiveViewportHeight/2, BackgroundDrawer.getHeight() - effectiveViewportHeight/2);
    }

    public static void update(){
        Vector2 position = new Vector2(PlayerDrawer.getInstance().getPlayerX(), PlayerDrawer.getInstance().getPlayerY());
        setUpCameraPosition(position.x,position.y,cam.viewportWidth,cam.viewportHeight);
        cam.update();
    }
}
