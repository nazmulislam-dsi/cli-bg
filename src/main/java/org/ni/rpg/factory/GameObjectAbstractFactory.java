package org.ni.rpg.factory;

import org.ni.rpg.core.enitiy.GameState;
import org.ni.rpg.core.enitiy.Obstacle;
import org.ni.rpg.core.enitiy.Player;

public interface GameObjectAbstractFactory {
    Player createPlayer(int positionX, int positionY, Character[][] content, String color, double attack, int range,
                        int protection, String name, String description, double health, String direction, int speed);

    GameState createGameMap(String name);

    Obstacle createObstacle(int positionX, int positionY, Character[][] content, String color, double attack,
                            int range, int protection);
}
