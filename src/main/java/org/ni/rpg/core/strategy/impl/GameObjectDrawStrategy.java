package org.ni.rpg.core.strategy.impl;

import org.ni.rpg.core.composite.GameComponent;
import org.ni.rpg.core.enitiy.helper.Dimension;
import org.ni.rpg.core.strategy.DrawStrategy;
import org.ni.rpg.exception.FrameSizeOutOfBound;
import org.ni.rpg.utils.Commons;

import java.io.Serializable;

/**
 * Created by nazmul on 9/29/2018.
 */
public class GameObjectDrawStrategy implements DrawStrategy, Serializable {
    private static final long serialversionUID = 538219549L;

    /**
     * all visible game object is being printed via his method, except Player object because player drawing
     * strategy is explicitly different.
     *
     * @param content
     * @param gameComponent
     * @return
     * @throws FrameSizeOutOfBound
     */
    @Override
    public Character[][] draw(Character[][] content, GameComponent gameComponent) throws FrameSizeOutOfBound {
        if ((gameComponent.getAppearance().isVisible() || !gameComponent.getAttribute().isKilled()
                || (gameComponent.getAttribute().isKilled() && !gameComponent.getAttribute().isRemoveAfterKilled()))
                && gameComponent.getAppearance().getDimension().getWidth() > 0
                && gameComponent.getAppearance().getDimension().getHeight() > 0) {
            int startHeight = gameComponent.getAppearance().getPositionY();
            Dimension dimension = Commons.calculateDimension(content);
            if (gameComponent.getAppearance().getPositionX() < 0 || gameComponent.getAppearance().getPositionY() < 0
                    || gameComponent.getAppearance().getPositionY() + gameComponent.getAppearance().getDimension().getHeight()
                    > dimension.getHeight() || (gameComponent.getAppearance().getDimension().getWidth()
                    + gameComponent.getAppearance().getPositionX() > dimension.getWidth())) {
                throw new FrameSizeOutOfBound();
            }
            for (int i = 0; i < gameComponent.getAppearance().getContent().length; i++) {
                int startWidth = gameComponent.getAppearance().getPositionX();
                for (int j = 0; j < gameComponent.getAppearance().getContent().length; j++) {
                    content[startHeight][startWidth] = gameComponent.getAppearance().getContent()[i][j];
                    startWidth++;
                }
                startHeight++;
            }
        }
        return content;
    }
}
