package com.ph.rpg.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.ph.rpg.game.Game;
import com.ph.rpg.scene.SceneManager;
import com.ph.rpg.utils.ClassFileManager;

import java.util.ArrayList;

/**
 * Created by Jock on 23.05.2016.
 */
public class FriendObject extends MovingObject {

    ArrayList<Statement> statementList;
    private float endDiscusionTime = 0;
    private boolean endDiscusion = false;

    private TipObject tip;


    public boolean isColliding(int x, int y){
        if(!endDiscusion)
            return true;
        return getRectangle().contains(x,y);
    }


    public FriendObject(XmlReader.Element friend) {
        super(ClassFileManager.friendXML);
        speed = 4;
        width = 30;
        height = 50;
        setCoord(new Vector2(300, -20));
        Array<XmlReader.Element> list = friend.getChildrenByName("statement");
        statementList = new ArrayList<Statement>();
        for (XmlReader.Element statement : list) {
            statementList.add(new Statement(statement.getText(), Integer.parseInt(statement.get("guy"))));
        }

//        for (Statement statement : statementList) {
//            System.out.printf(statement.statement + "\n");
//        }
    }


    public void say() {
        if(tip == null){
            tip = new TipObject();
            SceneManager.getCurrentScene().addMeToScene(tip);
        }

        if (!statementList.isEmpty()) {
            if (statementList.get(0).guy == 0) {
                System.out.printf("Oko : ");
                Vector2 tipCord = new Vector2(currentCoord);
                tipCord.add(0,height);
                tip.setCoord(tipCord);
            }else {
                System.out.printf("Mag : ");
                Vector2 tipCord = new Vector2(MageObject.mainObject.currentCoord);
                tipCord.add(0,MageObject.mainObject.height);
                tip.setCoord(tipCord);
            }
            System.out.printf(statementList.get(0).statement + "\n");
            tip.setStatement(statementList.get(0).statement + "\n");
            statementList.remove(0);
        } else {
            endDiscusionTime = Game.stateTime;
            endDiscusion = true;
            SceneManager.getCurrentScene().removeMeFromScene(tip);
            tip = null;
            moveToward(new Vector2(Game.WIDTH + 100, 180));
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float stateTime) {
        super.draw(spriteBatch, stateTime);
        if (endDiscusion && (stateTime - endDiscusionTime > 5 || isIdle())) {
            SceneManager.getCurrentScene().removeMeFromScene(this);
        }
    }

    private class Statement {
        private final String statement;
        private final int guy;

        Statement(String statement, int guy) {
            this.statement = statement;
            this.guy = guy;
        }
    }
}
