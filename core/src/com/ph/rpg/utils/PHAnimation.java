package com.ph.rpg.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.XmlReader;
import com.ph.rpg.mechanics.player.MovingObject;

/**
 * Created by Hamish on 2016-05-20.
 */
public class PHAnimation {

    private Integer frameCols;
    private String name;
    private String sprite;
    private Integer frameRows;
    private Float animationTime;

//    MovingObject.State type;

    private Texture walkSheet;
    private TextureRegion[] walkFrames;
    private Animation animation;

    public PHAnimation(XmlReader.Element xmlObject) {
        frameCols = Integer.parseInt(xmlObject.get("frameCols"));
        frameRows = Integer.parseInt(xmlObject.get("frameRows"));
        name = xmlObject.get("name");
        sprite = xmlObject.get("sprite");
        animationTime = Float.parseFloat(xmlObject.get("time"))/100.0f;

//        if (name.equals("RUN")) {
//            type = MovingObject.State.Running;
//        } else if (name.equals("IDLE")) {
//            type = MovingObject.State.Idle;
//        }

        walkSheet = new Texture(Gdx.files.internal(sprite));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / frameCols, walkSheet.getHeight() / frameRows);
        walkFrames = new TextureRegion[frameCols * frameRows];
        int index = 0;
        for (int i = 0; i < frameRows; i++) {
            for (int j = 0; j < frameCols; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        animation = new Animation(animationTime/(frameCols*frameRows), walkFrames);
    }

    public Integer getFrameCols() {
        return frameCols;
    }

    public void setFrameCols(Integer frameCols) {
        this.frameCols = frameCols;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public Integer getFrameRows() {
        return frameRows;
    }

    public void setFrameRows(Integer frameRows) {
        this.frameRows = frameRows;
    }

//    public MovingObject.State getType() {
//        return type;
//    }

    public Animation getAnimation() {
        return animation;
    }
}
