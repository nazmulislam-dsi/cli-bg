package org.ni.rpg.services;

import org.ni.rpg.core.enitiy.Frame;
import org.ni.rpg.core.enitiy.GameState;
import org.ni.rpg.exception.FrameSizeOutOfBound;

/**
 * Created by nazmul on 9/30/2018.
 */
public class GameStateRenderer {
    private static final GameStateRenderer instance = new GameStateRenderer();

    private GameStateRenderer() {
    }

    public static GameStateRenderer getInstance() {
        return instance;
    }

    /**
     * Purpose of this method is to generate the frame of the current stat and print that to console
     *
     * @param gameState
     * @throws FrameSizeOutOfBound
     */
    public void generateFrame(GameState gameState) throws FrameSizeOutOfBound {
        Character[][] characters = gameState.draw(gameState.getAppearance().getContent());
        Frame frame = new Frame();
        frame.drawAtPosition(1, 1, characters);
        frame.drawBorder();
        System.out.println(frame.getFrameForPrint());
    }

    public Character[][] getGameFrame(GameState gameState) throws FrameSizeOutOfBound {
        return gameState.draw(gameState.getAppearance().getContent());
    }
}
