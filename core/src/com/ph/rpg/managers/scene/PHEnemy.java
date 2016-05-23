package com.ph.rpg.managers.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.XmlReader;

/**
 * Hamish
 * 2016-05-22.
 */
public class PHEnemy {

    private String id;
    private String path;
    private Texture texture;

    public PHEnemy(XmlReader.Element node){
        id = node.get("id");
        path = node.get("path");
        texture = new Texture(Gdx.files.internal(path));
    }

    public String getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public Texture getTexture() {
        return texture;
    }
}
