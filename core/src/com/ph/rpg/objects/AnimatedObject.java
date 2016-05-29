package com.ph.rpg.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.ph.rpg.scene.SceneManager;
import com.ph.rpg.utils.PHAnimation;

import java.io.IOException;
import java.util.Vector;

/**
 * Created by Hamish on 2016-05-20.
 */
public class AnimatedObject extends com.ph.rpg.objects.DrawableObject {

    Vector<PHAnimation> animations = new Vector();
    protected int currentAnimation = 0;
    protected boolean facingLeft = false;

    public Vector<PHAnimation> getAnimations() {
        return animations;
    }

    public void setAnimations(Vector<PHAnimation> animations) {
        this.animations = animations;
    }

    public AnimatedObject(String xmlPath) {
        try {
            XmlReader reader = new XmlReader();
            System.out.println("AnimatedObject created: " + xmlPath);
            XmlReader.Element root = reader.parse(Gdx.files.internal(xmlPath));

            //Animations
            XmlReader.Element animationsXML = root.getChildByName("animations");
            Array<XmlReader.Element> animationList = animationsXML.getChildrenByName("animation");
            for (XmlReader.Element child : animationList) {
                animations.add(new PHAnimation(child));
            }
        } catch (IOException e) {
        }
    }

    public void setFacingLeft(boolean b) {
        facingLeft = b;
    }

    @Override
    public void draw(SpriteBatch batch, float stateTime) {
        TextureRegion currentFrame = animations.get(currentAnimation).getAnimation().getKeyFrame(stateTime, true);
        float zoom = SceneManager.getCurrentScene().getZoom();

        batch.draw(currentFrame,
                facingLeft ?
                        currentCoord.x + currentFrame.getRegionWidth()/2 :
                        currentCoord.x - currentFrame.getRegionWidth()/2,
                currentCoord.y,
                facingLeft ?
                        -currentFrame.getRegionWidth() * zoom :
                        currentFrame.getRegionWidth() * zoom
                , currentFrame.getRegionHeight() * zoom);
    }

    ;
}
