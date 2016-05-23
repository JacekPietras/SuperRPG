package com.ph.rpg.objects.scene;

import com.badlogic.gdx.graphics.Color;

/**
 * Hamish
 * 2016-05-22.
 */
public class PHGate {

    private Integer id;
    private Color color;

    public PHGate(Integer id, Color color) {
        this.id = id;
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }
}
