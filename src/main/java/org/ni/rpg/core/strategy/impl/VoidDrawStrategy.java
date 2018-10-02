package org.ni.rpg.core.strategy.impl;

import org.ni.rpg.core.composite.GameComponent;
import org.ni.rpg.core.strategy.DrawStrategy;

/**
 * Created by nazmul on 9/29/2018.
 */
public class VoidDrawStrategy implements DrawStrategy {

    /**
     * when the game object is invisible or no instruction to be printed the this drawing strategy is being called
     *
     * @param characters
     * @param gameComponent
     * @return
     */
    @Override
    public Character[][] draw(Character[][] characters, GameComponent gameComponent) {
        return characters;
    }
}
