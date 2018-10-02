package org.ni.rpg.core.enitiy.helper;

import java.io.Serializable;

/**
 * Created by nazmul on 9/29/2018.
 */
public class Dimension implements Serializable {
    private static final long serialversionUID = 538219548L;

    private int height;
    private int width;

    public Dimension(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
