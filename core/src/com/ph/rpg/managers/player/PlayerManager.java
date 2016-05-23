package com.ph.rpg.managers.player;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.ph.rpg.controllers.BackgroundDrawer;
import com.ph.rpg.controllers.PlayerDrawer;
import com.ph.rpg.managers.scene.PHGate;
import com.ph.rpg.managers.scene.SceneManager;
import com.ph.rpg.mechanics.player.MageClass;
import com.ph.rpg.mechanics.player.PlayerClass;

/**
 * Created by Hamish on 2016-05-20.
 */
public class PlayerManager {

    private static PlayerClass playerClass = new MageClass();

    private static final int BASIC_SPEED = 3;
    private static int SPEED = 3;

    private static PlayerState currentState = PlayerState.Idle;
    private static Vector2 playerCoord = new Vector2(0, 0);
    private static Vector2 destCoord = new Vector2();

    private static boolean hasFocus = true;

    public static PlayerState getState() {
        return currentState;
    }

    public static boolean isRunning() {
        return currentState == PlayerState.Running;
    }

    public static boolean isIdle() {
        return currentState == PlayerState.Idle;
    }

    public static void setRunning() {
        currentState = PlayerState.Running;
    }

    public static void setIdle() {
        currentState = PlayerState.Idle;
    }


    public static Vector2 getDestination() {
        if (isIdle()) {
            return playerCoord;
        } else {
            return destCoord;
        }
    }

    public static void moveToward(Vector2 dest) {
//        destCoord = findAccessibleDestination(dest);
        destCoord = new Vector2(dest.x - PlayerDrawer.getInstance().getPlayerWidth() / 2, dest.y);
        setRunning();
    }

    public static Vector2 getNextPosition() {
        if (isIdle()) {
            return playerCoord;
        } else {
            final Vector2 normal = new Vector2();
            normal.set(destCoord);
            normal.sub(playerCoord);
            final Vector2 nextPosition = new Vector2();


            if (normal.len() <= SPEED) {
                nextPosition.set(destCoord);
            } else {
                normal.nor();
                normal.setLength(SPEED);
                nextPosition.set(playerCoord);
                nextPosition.add(normal);

                if (!BackgroundDrawer.getInstance().getMask().getTextureData().isPrepared())
                    BackgroundDrawer.getInstance().getMask().getTextureData().prepare();

                Pixmap mask = BackgroundDrawer.getInstance().getMask().getTextureData().consumePixmap();
                Color color = new Color(mask.getPixel((int)nextPosition.x + PlayerDrawer.getInstance().getPlayerWidth()/2, (int)(mask.getHeight() - nextPosition.y)));
                System.out.println(color.toString() + " " + (int)nextPosition.x + PlayerDrawer.getInstance().getPlayerWidth()/2+ " " +(int)(mask.getHeight() - nextPosition.y));
                if (color.equals(Color.WHITE)) {
                    nextPosition.set(playerCoord);
                    destCoord.set(playerCoord);
                } else {
                    PHGate gate = SceneManager.checkGates(color);
                    if(gate!=null){
                        destCoord.set(playerCoord);
                        nextPosition.set(playerCoord);
                        SceneManager.goToScene(gate.getId());
                        return playerCoord;
                    }
                }
            }

            playerCoord.set(nextPosition);
            if (nextPosition.epsilonEquals(destCoord, 1.0f))
                setIdle();
            return nextPosition;
        }
    }

    public static boolean hasFocus() {
        return hasFocus;
    }

    public static PlayerClass getPlayerClass() {
        return playerClass;
    }

    public static Vector2 getPosition() {
        return playerCoord;
    }

    public static void setPlayerCoord(Vector2 playerCoord) {
        System.out.print(playerCoord);
        PlayerManager.playerCoord = playerCoord;
    }

    public static void changeSpeed(Float zoom) {
        SPEED = (int)(BASIC_SPEED*zoom);
    }

    public static void stop() {
        destCoord.set(playerCoord);
        currentState=PlayerState.Idle;
    }

    public static void faceToward(Boolean facing) {
        PlayerDrawer.setFacingLeft(!facing);
    }
}
