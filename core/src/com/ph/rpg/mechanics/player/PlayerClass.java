package com.ph.rpg.mechanics.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.ph.rpg.utils.PHAnimation;

import java.io.IOException;
import java.util.Vector;

/**
 * Created by Hamish on 2016-05-20.
 */
public class PlayerClass {

    Vector<PHAnimation> animations = new Vector();

    public Vector<PHAnimation> getAnimations() {
        return animations;
    }

    public void setAnimations(Vector<PHAnimation> animations) {
        this.animations = animations;
    }

    public PlayerClass(String xmlPath){
        try {
            XmlReader reader = new XmlReader();
            System.out.println("PlayerClass created: "+xmlPath);
            XmlReader.Element root = reader.parse(Gdx.files.internal(xmlPath));

            //Animations
            XmlReader.Element animationsXML = root.getChildByName("animations");
            Array<XmlReader.Element> animationList = animationsXML.getChildrenByName("animation");
            for (XmlReader.Element child : animationList) {
                animations.add(new PHAnimation(child));
            }
        } catch (IOException e){}
    }
}
