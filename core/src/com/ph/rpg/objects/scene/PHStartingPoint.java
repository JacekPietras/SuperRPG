package com.ph.rpg.objects.scene;

import com.badlogic.gdx.math.Vector2;

/**
 * Hamish
 * 2016-05-22.
 */
public class PHStartingPoint {

    private Vector2 point = new Vector2();
    private Boolean turnRight;

    public PHStartingPoint(Vector2 coord, Boolean turnRight) {
        point.set(coord);
        this.turnRight = turnRight;
    }

    public Vector2 getPoint() {
        return point;
    }

    public Boolean getTurnRight() {
        return turnRight;
    }
}
