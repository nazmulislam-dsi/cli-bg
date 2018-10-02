package org.ni.rpg.core.composite;

import org.ni.rpg.core.enitiy.helper.Appearance;
import org.ni.rpg.core.enitiy.helper.Attribute;
import org.ni.rpg.exception.FrameSizeOutOfBound;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by nazmul on 9/28/2018.
 */
public abstract class GameComponent implements Serializable {
    private String id;
    private Appearance appearance;
    private Attribute attribute;
    private boolean statOn;

    public abstract Character[][] draw(Character[][] characters) throws FrameSizeOutOfBound;

    public GameComponent(Appearance appearance, Attribute attribute) {
        id = UUID.randomUUID().toString();
        this.appearance = appearance;
        this.attribute = attribute;
        this.statOn = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public boolean isStatOn() {
        return statOn;
    }

    public void setStatOn(boolean statOn) {
        this.statOn = statOn;
    }

}
