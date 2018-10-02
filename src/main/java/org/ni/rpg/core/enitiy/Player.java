package org.ni.rpg.core.enitiy;

import org.ni.rpg.core.composite.GameComponent;
import org.ni.rpg.core.enitiy.helper.Appearance;
import org.ni.rpg.core.enitiy.helper.Attribute;
import org.ni.rpg.exception.FrameSizeOutOfBound;
import org.ni.rpg.core.strategy.DrawStrategy;

/**
 * Created by nazmul on 9/29/2018.
 */
public class Player extends GameComponent {
    private static final long serialversionUID = 538219553L;

    private String name;
    private String description;
    private double health;
    private Weapon weapon;
    private Shield shield;
    private DrawStrategy drawStrategy;
    private int speed;
    private int killed;

    private String UP;
    private String LEFT;
    private String RIGHT;
    private String DOWN;

    public Player(Appearance appearance, Attribute attribute, String name,
                  String description, double health, Weapon weapon, Shield shield,
                  DrawStrategy drawStrategy, int speed) {
        super(appearance, attribute);
        this.name = name;
        this.description = description;
        this.health = health;
        this.weapon = weapon;
        this.shield = shield;
        this.drawStrategy = drawStrategy;
        this.speed = speed;
        this.killed = 0;
    }

    public Character[][] draw(Character[][] contents) throws FrameSizeOutOfBound {
        return drawStrategy.draw(contents, this);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Shield getShield() {
        return shield;
    }

    public void setShield(Shield shield) {
        this.shield = shield;
    }

    public DrawStrategy getDrawStrategy() {
        return drawStrategy;
    }

    public void setDrawStrategy(DrawStrategy drawStrategy) {
        this.drawStrategy = drawStrategy;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getKilled() {
        return killed;
    }

    public void setKilled(int killed) {
        this.killed = killed;
    }
}
