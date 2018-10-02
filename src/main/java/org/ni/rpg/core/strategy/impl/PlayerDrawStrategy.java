package org.ni.rpg.core.strategy.impl;

import org.ni.rpg.core.composite.GameComponent;
import org.ni.rpg.core.enitiy.helper.Dimension;
import org.ni.rpg.core.enitiy.Player;
import org.ni.rpg.core.strategy.DrawStrategy;
import org.ni.rpg.exception.FrameSizeOutOfBound;
import org.ni.rpg.utils.Commons;

import java.io.Serializable;

import static org.ni.rpg.utils.Properties.*;

/**
 * Created by nazmul on 9/29/2018.
 */
public class PlayerDrawStrategy implements DrawStrategy, Serializable {
    private static final long serialversionUID = 538219549L;

    /**
     * Draw each player at it's X and Y position and as the direction it actually point that with a bar (|).
     * If the state is enable it prints the state as well
     *
     * @param content
     * @param gameComponent
     * @return
     * @throws FrameSizeOutOfBound
     */
    @Override
    public Character[][] draw(Character[][] content, GameComponent gameComponent) throws FrameSizeOutOfBound {
        if ((gameComponent.getAppearance().isVisible() || !gameComponent.getAttribute().isKilled()
                || (gameComponent.getAttribute().isKilled()
                && !gameComponent.getAttribute().isRemoveAfterKilled()))
                && gameComponent.getAppearance().getDimension().getWidth() > 0
                && gameComponent.getAppearance().getDimension().getHeight() > 0) {
            int startHeight = gameComponent.getAppearance().getPositionY();
            Dimension dimension = Commons.calculateDimension(content);
            if (gameComponent.getAppearance().getPositionX() < 0
                    || gameComponent.getAppearance().getPositionY() < 0
                    || gameComponent.getAppearance().getPositionY() + gameComponent.getAppearance().getDimension().getHeight()
                    > dimension.getHeight() || (gameComponent.getAppearance().getDimension().getWidth()
                    + gameComponent.getAppearance().getPositionX() > dimension.getWidth())) {
                throw new FrameSizeOutOfBound();
            }
            for (int i = 0; i < gameComponent.getAppearance().getDimension().getHeight(); i++) {
                int startWidth = gameComponent.getAppearance().getPositionX();
                for (int j = 0; j < gameComponent.getAppearance().getDimension().getWidth(); j++) {
                    content[startHeight][startWidth] = gameComponent.getAppearance().getContent()[i][j];
                    startWidth++;
                }
                startHeight++;
            }
            if (!gameComponent.getAttribute().isKilled()) {
                if (gameComponent.getAppearance().getDirection().equals(UP)) {
                    if (gameComponent.getAppearance().getPositionY() > 0) {
                        try {
                            content[gameComponent.getAppearance().getPositionY() - 1]
                                    [gameComponent.getAppearance().getPositionX()] = '|';
                        } catch (Exception ex) {
                        }
                    }
                } else if (gameComponent.getAppearance().getDirection().equals(DOWN)) {
                    if (gameComponent.getAppearance().getPositionY() + gameComponent.getAppearance()
                            .getDimension().getHeight() < dimension.getHeight() - 1) {
                        try {
                            content[gameComponent.getAppearance()
                                    .getPositionY() + gameComponent.getAppearance()
                                    .getDimension().getHeight()][gameComponent.getAppearance().getPositionX() + 1] = '|';
                        } catch (Exception ex) {
                        }
                    }
                } else if (gameComponent.getAppearance().getDirection().equals(LEFT)) {
                    if (gameComponent.getAppearance().getPositionX() > 0) {
                        try {
                            content[gameComponent.getAppearance().getPositionY()]
                                    [gameComponent.getAppearance().getPositionX() - 1] = '-';
                        } catch (Exception ex) {
                        }
                    }
                } else if (gameComponent.getAppearance().getDirection().equals(RIGHT)) {
                    if (gameComponent.getAppearance().getPositionX()
                            + gameComponent.getAppearance().getDimension().getWidth() < dimension.getWidth() - 1) {
                        try {
                            content[gameComponent.getAppearance().getPositionY()][gameComponent
                                    .getAppearance().getPositionX() + gameComponent
                                    .getAppearance().getDimension().getWidth() + 1] = '-';
                        } catch (Exception ex) {
                        }
                    }
                }
                if (Commons.isShowState() || gameComponent.isStatOn()) {
                    getStatus(gameComponent, dimension, content);
                    gameComponent.setStatOn(false);
                }
            }
            helpWatermark(content, dimension);
        }
        return content;
    }

    private void helpWatermark(Character[][] content, Dimension dimension) {
        drawText(content, 0, 0, HELP_STATE_TEST, dimension.getWidth());
    }

    private void getStatus(GameComponent gameComponent, Dimension dimension, Character[][] content) {
        String status = "";
        if (gameComponent instanceof Player) {
            Player player = (Player) gameComponent;
            status = Commons.getStatusLine(player);
        }
        if (!Commons.isEmpty(status) && gameComponent.getAppearance().getPositionY() > 1) {
            int startY = gameComponent.getAppearance().getPositionY() - 2;
            char[] statusCharArr = status.toCharArray();
            int statusLengthHalf = statusCharArr.length / 2;
            int startX = 0;
            if (gameComponent.getAppearance().getPositionX() - statusLengthHalf >= 0) {
                startX = gameComponent.getAppearance().getPositionX() - statusLengthHalf;
            }
            drawText(content, startX, startY, status, dimension.getWidth());
        }
    }

    private void drawText(Character[][] content, int positionX, int positionY, String text, int limit) {
        char[] textCharArr = text.toCharArray();
        try {
            for (int i = 0; i < textCharArr.length; i++) {
                if (content[positionY][positionX].equals(' ')) {
                    try {
                        content[positionY][positionX] = textCharArr[i];
                    } catch (Exception ex) {

                    }
                }
                positionX++;
                if (positionX >= limit) {
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
