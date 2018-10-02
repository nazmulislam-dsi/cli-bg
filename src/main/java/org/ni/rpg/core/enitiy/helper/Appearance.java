package org.ni.rpg.core.enitiy.helper;

import java.io.Serializable;

/**
 * Created by nazmul on 9/29/2018.
 */
public class Appearance implements Serializable {
    private static final long serialversionUID = 538219547L;

    private int positionX;
    private int positionY;
    private Dimension dimension;
    private Character[][] content;
    private String color;
    private boolean visible;
    private String direction;

    public Appearance(int positionX, int positionY, Dimension dimension, Character[][] content,
                      String color, boolean visible, String direction) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.dimension = dimension;
        this.content = content;
        this.color = color;
        this.visible = visible;
        this.direction = direction;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Character[][] getContent() {
        return content;
    }

    public void setContent(Character[][] content) {
        this.content = content;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
