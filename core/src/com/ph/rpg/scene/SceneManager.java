package com.ph.rpg.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.ph.rpg.controllers.CameraController;
import com.ph.rpg.objects.MovingObject;

import java.io.IOException;
import java.util.Vector;

/**
 * Created by Hamish on 2016-05-21.
 */
public class SceneManager {

    private static final String scenesXMLPath = "scenes/scenes.xml";
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

        System.out.println("change scene from "+previousScene + " to " +currentScene);

        MovingObject.mainObject.setCoord(getCurrentScene().getStartPoint(previousScene));
        MovingObject.mainObject.setSpeed(getCurrentScene().getZoom());
        CameraController.scroll(0);
        MovingObject.mainObject.faceToward(getCurrentScene().getFacing(previousScene));
        MovingObject.mainObject.stop();
    }

    public static PHGate checkGates(Color color) {
        return getCurrentScene().checkGates(color);
    }
}
