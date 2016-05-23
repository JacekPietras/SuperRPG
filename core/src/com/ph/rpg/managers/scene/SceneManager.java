package com.ph.rpg.managers.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.ph.rpg.controllers.CameraController;
import com.ph.rpg.managers.player.PlayerManager;

import java.io.IOException;
import java.util.Vector;

/**
 * Created by Hamish on 2016-05-21.
 */
public class SceneManager {

    private static final String scenesXMLPath = "scenes.xml";
    public static Vector<PHScene> scenes = new Vector();

    public static int currentScene = -1;
    public static int previousScene;

    static{
        try {
            XmlReader reader = new XmlReader();
            XmlReader.Element root = reader.parse(Gdx.files.internal(scenesXMLPath));

            //Animations
            XmlReader.Element scenesXML = root.getChildByName("scenes");
            Array<XmlReader.Element> scenesList = scenesXML.getChildrenByName("scene");
            for (XmlReader.Element child : scenesList) {
                scenes.add(new PHScene(child));
            }
        } catch (IOException e){}
    }

    public static PHScene getCurrentScene(){
        for(PHScene scene : scenes){
            if(scene.getId()==currentScene)
                return scene;
        }
        throw new IllegalStateException();
    }

    public static void goToScene(int i){
        previousScene = currentScene;
        currentScene = i;

        System.out.println(previousScene + " " +currentScene);

        PlayerManager.setPlayerCoord(getCurrentScene().getStartPoint(previousScene));
        PlayerManager.changeSpeed(getCurrentScene().getZoom());
        CameraController.scroll(0);
        PlayerManager.faceToward(getCurrentScene().getFacing(previousScene));
        PlayerManager.stop();
    }

    public static PHGate checkGates(Color color) {
        return getCurrentScene().checkGates(color);
    }
}
