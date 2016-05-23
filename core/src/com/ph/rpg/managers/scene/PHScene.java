package com.ph.rpg.managers.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.ph.rpg.managers.EnemyProvider;
import com.ph.rpg.managers.ObjectProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Hamish
 * 2016-05-21.
 */
public class PHScene {

    private Integer id;
//    private Integer startX;
    private String pathScene;
//    private Integer startY;
    private String name;
    private String pathMask;
    private Float zoom;

    Texture scene, mask;

    Map<Vector2,PHObject> objects = new HashMap();
    Map<Vector2,PHEnemy> enemies = new HashMap();
    Map<Integer,PHGate> gates = new HashMap();
    Map<Integer,PHStartingPoint> startingPoints = new HashMap();

    public PHScene(XmlReader.Element child) {
        id = Integer.parseInt(child.get("id"));
        pathScene = child.get("pathScene");
        name = child.get("name");
        pathMask = child.get("pathMask");
        zoom = Float.parseFloat(child.get("zoom"));

        scene = new Texture(Gdx.files.internal(pathScene));
        mask = new Texture(Gdx.files.internal(pathMask));

        getObjects(child);
        getEnemies(child);
        getGates(child);
        getStartingPoints(child);
    }

    private void getStartingPoints(XmlReader.Element _child) {
        XmlReader.Element child = _child.getChildByName("startPoints");
        if(child==null)
            return;
        Array<XmlReader.Element> list = child.getChildrenByName("startPoint");
        for (XmlReader.Element enemy : list) {
            Integer from = Integer.parseInt(enemy.get("from"));
            Vector2 coord = new Vector2(Integer.parseInt(enemy.get("x")),Integer.parseInt(enemy.get("y")));
            Boolean turnRight = Boolean.parseBoolean("turnRight");
            startingPoints.put(from, new PHStartingPoint(coord,turnRight));
        }
    }

    private void getGates(XmlReader.Element _child) {
        XmlReader.Element child = _child.getChildByName("gates");
        if(child==null)
            return;
        Array<XmlReader.Element> list = child.getChildrenByName("gate");
        for (XmlReader.Element enemy : list) {
            Integer id = Integer.parseInt(enemy.get("id"));
            Color color = Color.valueOf(enemy.get("color"));
            gates.put(id, new PHGate(id,color));
        }
    }

    private void getEnemies(XmlReader.Element _child) {
        XmlReader.Element child = _child.getChildByName("enemies");
        if(child==null)
            return;
        Array<XmlReader.Element> list = child.getChildrenByName("enemy");
        for (XmlReader.Element enemy : list) {
            String id = enemy.get("id");
            Vector2 coord = new Vector2(Integer.parseInt(enemy.get("x")),Integer.parseInt(enemy.get("y")));
//            coord.add(EnemyProvider.get(id).getTexture().getWidth()/2,0);
            enemies.put(coord, EnemyProvider.get(id));
        }
    }

    private void getObjects(XmlReader.Element _child) {
        XmlReader.Element child = _child.getChildByName("objects");
        if(child==null)
            return;
        Array<XmlReader.Element> list = child.getChildrenByName("object");
        for (XmlReader.Element object : list) {
            String id = object.get("id");
            Vector2 coord = new Vector2(Integer.parseInt(object.get("x")),Integer.parseInt(object.get("y")));
//            coord.add(ObjectProvider.get(id).getTexture().getWidth()/2,0);
            objects.put(coord,ObjectProvider.get(id));
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

    public Boolean getFacing(Integer from){
        return startingPoints.get(from).getTurnRight();
    }

    public Integer getStartX(Integer from) {
        return (int)startingPoints.get(from).getPoint().x;
    }

    public Integer getStartY(Integer from) {
        return (int)startingPoints.get(from).getPoint().y;
    }

    public Vector2 getStartPoint(Integer from) {
        return new Vector2(getStartX(from),/*scene.getHeight()-*/getStartY(from));
    }

    public Float getZoom() {
        return zoom;
    }


    private void drawObjects(SpriteBatch batch, Vector2 user, boolean behind) {
        for(Vector2 pos : objects.keySet()){
            if(behind){
                if(pos.y >= user.y){
                    batch.draw(objects.get(pos).getTexture(),pos.x,pos.y);
                }
            } else {
                if(pos.y < user.y) {
                    batch.draw(objects.get(pos).getTexture(), pos.x, pos.y);
                }
            }
        }
    }

    private void drawEnemies(SpriteBatch batch, Vector2 user, boolean behind) {
        for(Vector2 pos : enemies.keySet()){
            if(behind){
                if(pos.y >= user.y) {
                    batch.draw(enemies.get(pos).getTexture(), pos.x, pos.y);
                }
            } else {
                if(pos.y < user.y) {
                    batch.draw(enemies.get(pos).getTexture(),pos.x,pos.y);
                }
            }
        }
    }

    public PHGate checkGates(Color color) {
        for(Integer id : gates.keySet()){
            if(gates.get(id).getColor().equals(color)){
                return gates.get(id);
            }
        }
        return null;
    }

    public void drawBatchesBehind(SpriteBatch batch, Vector2 player) {
        drawEnemies(batch,player,true);
        drawObjects(batch,player,true);
    }

    public void drawBatchesBefore(SpriteBatch batch, Vector2 player) {
        drawEnemies(batch,player,false);
        drawObjects(batch,player,false);

    }
}
