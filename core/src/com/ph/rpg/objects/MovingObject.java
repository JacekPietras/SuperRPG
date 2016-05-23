package com.ph.rpg.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ph.rpg.objects.scene.PHGate;
import com.ph.rpg.objects.scene.SceneManager;

/**
 * Created by Jock on 23.05.2016.
 */
public class MovingObject extends AnimatedObject {
    private static final int BASIC_SPEED = 3;
    protected int speed = BASIC_SPEED;
    protected Vector2 destCoord = new Vector2();

    public static MageObject mainObject;

    public void setAsMainObject() {
        mainObject = (MageObject) this;
    }

    public int getState() {
        return currentAnimation;
    }

    public boolean isRunning() {
        return currentAnimation == 0;
    }

    public boolean isIdle() {
        return currentAnimation == 1;
    }

    public void setRunning() {
        currentAnimation = 0;
    }

    public void setIdle() {
        currentAnimation = 1;
    }


    @Override
    public void setCoord(Vector2 newCoord) {
        currentCoord = destCoord = newCoord;
    }

    public void setSpeed(Float zoom) {
        speed = (int) (BASIC_SPEED * zoom);
    }

    public Vector2 getDestination() {
        if (isIdle()) {
            return currentCoord;
        } else {
            return destCoord;
        }
    }

    public void stop() {
        destCoord.set(currentCoord);
        currentAnimation = 1;
    }

    public void moveToward(Vector2 dest) {
//        destCoord = findAccessibleDestination(dest);
//        destCoord = new Vector2(dest.x - MageObject.getInstance().getPlayerWidth() / 2, dest.y);
        destCoord = new Vector2(dest);
        setRunning();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float stateTime) {
        currentCoord = getNextPosition();
        if (currentCoord.x > getDestination().x && !facingLeft) {
            facingLeft = true;
        } else if (currentCoord.x < getDestination().x && facingLeft) {
            facingLeft = false;
        }
//        levelUp.draw(spriteBatch,stateTime);
//        blood.draw(spriteBatch,stateTime);

        super.draw(spriteBatch, stateTime);
    }


    public void faceToward(Boolean facing) {
        setFacingLeft(!facing);
    }

    public Vector2 getNextPosition() {
        if (isIdle()) {
            return currentCoord;
        } else {
            final Vector2 normal = new Vector2();
            normal.set(destCoord);
            normal.sub(currentCoord);
            final Vector2 nextPosition = new Vector2();


            if (normal.len() <= speed) {
                nextPosition.set(destCoord);
            } else {
                normal.nor();
                normal.setLength(speed);
                nextPosition.set(currentCoord);
                nextPosition.add(normal);


                if (this == mainObject) {
                    if (!SceneManager.getCurrentScene().getMask().getTextureData().isPrepared())
                        SceneManager.getCurrentScene().getMask().getTextureData().prepare();

                    Pixmap mask = SceneManager.getCurrentScene().getMask().getTextureData().consumePixmap();
                    Color color = new Color(mask.getPixel((int) nextPosition.x, (int) (mask.getHeight() - nextPosition.y)));
//                    System.out.println(color.toString() + " " + (int) nextPosition.x + " " + (int) (mask.getHeight() - nextPosition.y));
                    if (color.equals(Color.WHITE)) {
                        nextPosition.set(currentCoord);
                        destCoord.set(currentCoord);
                    } else {
                        PHGate gate = SceneManager.checkGates(color);
                        if (gate != null) {
                            destCoord.set(currentCoord);
                            nextPosition.set(currentCoord);
                            SceneManager.goToScene(gate.getId());
                            return currentCoord;
                        }
                    }
                }
            }

            currentCoord.set(nextPosition);
            if (nextPosition.epsilonEquals(destCoord, 1.0f))
                setIdle();
            return nextPosition;
        }
    }

    public MovingObject(String imagePath) {
        super(imagePath);
        currentAnimation = 1;
    }
}
