package com.ph.rpg.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.ph.rpg.game.Game;
import com.ph.rpg.objects.CoinObject;
import com.ph.rpg.objects.ExplosionObject;
import com.ph.rpg.objects.FriendObject;
import com.ph.rpg.objects.MageObject;
import com.ph.rpg.objects.DrawableObject;
import com.ph.rpg.objects.GemObject;
import com.ph.rpg.objects.SkorpionBigObject;
import com.ph.rpg.objects.SkorpionObject;
import com.sun.javafx.geom.Vec4f;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/**
 * Hamish
 * 2016-05-21.
 */
public class PHScene {

    private Integer id;
    private String pathScene;
    private String name;
    private String pathMask;
    private Float zoom;

    private Vector2 lastClick;

    Texture scene, mask;

    ArrayList<DrawableObject> objects = new ArrayList<DrawableObject>();
    Map<Integer, PHGate> gates = new HashMap();
    Map<Integer, PHStartingPoint> startingPoints = new HashMap();
    private Texture texture;

    public PHScene(XmlReader.Element child) {
        id = Integer.parseInt(child.get("id"));
        pathScene = child.get("pathScene");
        name = child.get("name");
        pathMask = child.get("pathMask");
        zoom = Float.parseFloat(child.get("zoom"));

        scene = new Texture(Gdx.files.internal(pathScene));
        mask = new Texture(Gdx.files.internal(pathMask));

        getObjects(child);
        getScorpions(child);
        getBigScorpions(child);
        getFriends(child);
        getGates(child);
        getStartingPoints(child);
    }

    public void click(Vector2 clickPlace) {
        lastClick = clickPlace;
    }

    public void click(float x, float y) {
        lastClick = new Vector2(x, y);
    }

    private void getStartingPoints(XmlReader.Element _child) {
        Array<XmlReader.Element> list = _child.getChildrenByName("startPoint");
        for (XmlReader.Element enemy : list) {
            Integer from = Integer.parseInt(enemy.get("from"));
            Vector2 coord = new Vector2(Integer.parseInt(enemy.get("x")), Integer.parseInt(enemy.get("y")));
            Boolean turnRight = Boolean.parseBoolean("turnRight");
            startingPoints.put(from, new PHStartingPoint(coord, turnRight));
        }
    }

    private void getGates(XmlReader.Element _child) {
        Array<XmlReader.Element> list = _child.getChildrenByName("gate");
        for (XmlReader.Element enemy : list) {
            Integer id = Integer.parseInt(enemy.get("id"));
            Color color = Color.valueOf(enemy.get("color"));
            gates.put(id, new PHGate(id, color));
        }
    }

    private void getFriends(XmlReader.Element _child) {
        Array<XmlReader.Element> list = _child.getChildrenByName("friend");
        for (XmlReader.Element friend : list) {
            FriendObject thing = new FriendObject(friend);
            thing.moveToward(new Vector2(Integer.parseInt(friend.get("x")), Integer.parseInt(friend.get("y"))));
            objects.add(thing);
        }
    }

    private void getScorpions(XmlReader.Element _child) {
        Array<XmlReader.Element> list = _child.getChildrenByName("skorpion");
        for (XmlReader.Element enemy : list) {
            SkorpionObject thing = new SkorpionObject();
            thing.setCoord(new Vector2(Integer.parseInt(enemy.get("x")), Integer.parseInt(enemy.get("y"))));
            objects.add(thing);
        }
    }

    private void getBigScorpions(XmlReader.Element _child) {
        Array<XmlReader.Element> list = _child.getChildrenByName("skorpionbig");
        for (XmlReader.Element enemy : list) {
            SkorpionBigObject thing = new SkorpionBigObject();
            thing.setCoord(new Vector2(Integer.parseInt(enemy.get("x")), Integer.parseInt(enemy.get("y"))));
            objects.add(thing);
        }
    }

    private void getObjects(XmlReader.Element _child) {
        Array<XmlReader.Element> list = _child.getChildrenByName("object");
        for (XmlReader.Element object : list) {
            GemObject thing = new GemObject();
            thing.setCoord(new Vector2(Integer.parseInt(object.get("x")), Integer.parseInt(object.get("y"))));
            objects.add(thing);
        }
    }

    public Texture getScene() {
        return scene;
    }

    public Texture getMask() {
        return mask;
    }

    public String getPathScene() {
        return pathScene;
    }

    public String getName() {
        return name;
    }

    public String getPathMask() {
        return pathMask;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getFacing(Integer from) {
        return startingPoints.get(from).getTurnRight();
    }

    public Integer getStartX(Integer from) {
        return (int) startingPoints.get(from).getPoint().x;
    }

    public Integer getStartY(Integer from) {
        return (int) startingPoints.get(from).getPoint().y;
    }

    public Vector2 getStartPoint(Integer from) {
        return new Vector2(getStartX(from), getStartY(from));
    }

    public Float getZoom() {
        return zoom;
    }

    public PHGate checkGates(Color color) {
        for (Integer id : gates.keySet()) {
            if (gates.get(id).getColor().equals(color)) {
                return gates.get(id);
            }
        }
        return null;
    }

    public void drawObjects(SpriteBatch batch) {
        ArrayList<DrawableObject> drawList = new ArrayList<DrawableObject>();
        drawList.addAll(objects);
        drawList.addAll(MageObject.getDrawableObjects());
        Collections.sort(drawList);
        for (DrawableObject object : drawList) {
            object.draw(batch, Game.stateTime);
        }
    }

    public void removeMeFromScene(DrawableObject object){
        objects.remove(object);
    }
    public void addMeToScene(DrawableObject object){
        objects.add(object);
    }

//    public void drawTexts(SpriteBatch batch) {
//        BitmapFont font = new BitmapFont(Gdx.files.internal("arial.fnt"), new TextureRegion(texture), false);
//        batch.setShader(fontShader);
//        font.setColor(Color.BLACK);
//        font.getData().setScale(0.5f);
//        font.draw(batch, "Hello smooth world! LOOLO GLGO LOG L O GL OG G", 10, 300,  200, 1, true);
//        batch.setShader(null);
//
//    }
    public void checkCollisions() {
        ArrayList<DrawableObject> drawList = new ArrayList<DrawableObject>();
        drawList.addAll(objects);
        drawList.addAll(MageObject.getDrawableObjects());
        for (int i = 0; i < drawList.size(); i++) {
            DrawableObject first = drawList.get(i);
            if (first.width == 0 || first.height == 0)
                continue;

            if (lastClick != null && first.isColliding(lastClick)) {
//                System.out.print("collision\n");
                if (first instanceof SkorpionObject) {
                    MageObject.mainObject.shoot(new Vector2(lastClick.x, lastClick.y));
                    lastClick = null;
                }else if (first instanceof FriendObject) {
                    ((FriendObject)first).say();
                    lastClick = null;
                }
            }

            for (int j = i; j < drawList.size(); j++) {
                DrawableObject second = drawList.get(j);
                if (second.width == 0 || second.height == 0 || i == j)
                    continue;

                //types of collisions
                if (first.isColliding(second)) {
                    if (first instanceof ExplosionObject && second instanceof SkorpionObject) {
                        ((SkorpionObject) second).hit(((ExplosionObject) first).getDamage());
                        first.width = first.height = 0;
                    }
                    if (first instanceof SkorpionObject && second instanceof ExplosionObject) {
                        ((SkorpionObject) first).hit(((ExplosionObject) second).getDamage());
                        second.width = second.height = 0;
                    }

                    if (first instanceof MageObject && second instanceof SkorpionObject) {
                        ((MageObject) first).hit(((SkorpionObject) second).getDamage());
                    }
                    if (first instanceof SkorpionObject && second instanceof MageObject) {
                        ((MageObject) second).hit(((SkorpionObject) first).getDamage());
                    }

                    if (first instanceof MageObject && second instanceof CoinObject) {
                        ((CoinObject) second).collected();
                        removeMeFromScene(second);
                    }
                    if (first instanceof CoinObject && second instanceof MageObject) {
                        ((CoinObject) first).collected();
                        removeMeFromScene(first);
                    }

                }
            }
        }

        if (lastClick != null && MageObject.hasFocus()) {
            MageObject.mainObject.moveToward(new Vector2(lastClick.x, lastClick.y));
        }
        lastClick = null;
    }
}
