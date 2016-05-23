package com.ph.rpg.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.ph.rpg.managers.scene.PHEnemy;

import java.io.IOException;
import java.util.HashMap;

/**
 * Hamish
 * 2016-05-22.
 */
public class EnemyProvider {

    private static HashMap<String, PHEnemy> enemies = new HashMap();

    static{
        try {
            XmlReader reader = new XmlReader();
            XmlReader.Element root = reader.parse(Gdx.files.internal("enemies.xml"));

            //Animations
            XmlReader.Element scenesXML = root.getChildByName("enemies");
            Array<XmlReader.Element> scenesList = scenesXML.getChildrenByName("enemy");
            for (XmlReader.Element child : scenesList) {
                enemies.put(child.get("id"),new PHEnemy(child));
            }
        } catch (IOException e){}
    }

    public static PHEnemy get(String id) {
        return enemies.get(id);
    }
}
