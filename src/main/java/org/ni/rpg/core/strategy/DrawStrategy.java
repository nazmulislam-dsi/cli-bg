package org.ni.rpg.core.strategy;

import org.ni.rpg.core.composite.GameComponent;
import org.ni.rpg.exception.FrameSizeOutOfBound;

/**
 * Created by nazmul on 9/29/2018.
 */
public interface DrawStrategy {
    Character[][] draw(Character[][] characters, GameComponent gameComponent) throws FrameSizeOutOfBound;
}
