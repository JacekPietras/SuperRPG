package com.ph.rpg.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.ph.rpg.managers.scene.PHObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * Hamish
 * 2016-05-22.
 */
public class ObjectProvider {

    private static HashMap<String, PHObject> objects = new HashMap();

    static{
        try {
            XmlReader reader = new XmlReader();
            XmlReader.Element root = reader.parse(Gdx.files.internal("objects.xml"));

            //Animations
            XmlReader.Element scenesXML = root.getChildByName("objects");
            Array<XmlReader.Element> scenesList = scenesXML.getChildrenByName("object");
            for (XmlReader.Element child : scenesList) {
                objects.put(child.get("id"),new PHObject(child));
            }
        } catch (IOException e){}
    }

    public static PHObject get(String id) {
        return objects.get(id);
    }
}
