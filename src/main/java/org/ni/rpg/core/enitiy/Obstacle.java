package org.ni.rpg.core.enitiy;

import org.ni.rpg.core.composite.GameComponent;
import org.ni.rpg.core.enitiy.helper.Appearance;
import org.ni.rpg.core.enitiy.helper.Attribute;
import org.ni.rpg.exception.FrameSizeOutOfBound;
import org.ni.rpg.core.strategy.DrawStrategy;

/**
 * Created by nazmul on 9/29/2018.
 */
public class Obstacle extends GameComponent {
    private static final long serialversionUID = 538219552L;

    private DrawStrategy drawStrategy;

    public Obstacle(Appearance appearance, Attribute attribute, DrawStrategy drawStrategy) {
        super(appearance, attribute);
        this.drawStrategy = drawStrategy;
    }

    public Character[][] draw(Character[][] characters) throws FrameSizeOutOfBound {
        return drawStrategy.draw(characters, this);
    }

}
