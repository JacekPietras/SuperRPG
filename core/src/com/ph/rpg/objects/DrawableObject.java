package com.ph.rpg.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader;

import java.awt.Rectangle;


/**
 * Created by Hamish on 2016-05-20.
 */
public class DrawableObject implements Comparable<DrawableObject> {

    protected Vector2 currentCoord = new Vector2(0, 0);
    private Texture texture;

    public int width = 0;
    public int height = 0;

    public Rectangle getRectangle(){
        return new Rectangle((int)(currentCoord.x-width/2), (int)currentCoord.y, width,height);
    }

    public boolean isColliding(int x, int y){
        return getRectangle().contains(x,y);
    }

    public boolean isColliding(Vector2 coord){
        return isColliding((int)coord.x,(int)coord.y);
    }

    public boolean isColliding(Rectangle collider){
        return !getRectangle().intersection(collider).isEmpty();
    }

    public boolean isColliding(DrawableObject collider){
        return isColliding(collider.getRectangle());
    }

    public void setCoord(Vector2 currentCoord) {
        this.currentCoord = currentCoord;
    }
    public DrawableObject(String imagePath) {
        System.out.println("DrawableObject created: " + imagePath);
        texture = new Texture(Gdx.files.internal(imagePath));
    }

    public DrawableObject() {
    }

    public void draw(SpriteBatch batch, float stateTime) {
        batch.draw(texture, currentCoord.x - texture.getWidth() / 2, currentCoord.y);
    }

    public Vector2 getPosition() {
        return currentCoord;
    }

    @Override
    public int compareTo(DrawableObject o) {
            return (int)(o.getPosition().y-getPosition().y);
    }
}
