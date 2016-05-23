package com.ph.rpg.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader;

/**
 * Created by Hamish on 2016-05-20.
 */
public class DrawableObject implements Comparable<DrawableObject> {

    protected Vector2 currentCoord = new Vector2(0, 0);
    private Texture texture;

    public void setCoord(Vector2 currentCoord) {
        this.currentCoord = currentCoord;
    }
    public DrawableObject(String imagePath) {
        XmlReader reader = new XmlReader();
        System.out.println("DrawableObject created: " + imagePath);
        texture = new Texture(Gdx.files.internal(imagePath));
    }

    public DrawableObject(String imagePath, Vector2 currentCoord) {
        XmlReader reader = new XmlReader();
        System.out.println("DrawableObject created: " + imagePath);
        this.currentCoord = currentCoord;
        texture = new Texture(Gdx.files.internal(imagePath));
    }

    public DrawableObject() {
    }

    public void draw(SpriteBatch batch, float stateTime) {
        batch.draw(texture, currentCoord.x - texture.getWidth() / 2, currentCoord.y);
    }

    @Override
    public int compareTo(DrawableObject o) {
            return (int)(currentCoord.y-o.currentCoord.y);
    }
}
