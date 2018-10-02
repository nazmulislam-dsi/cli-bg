package org.ni.rpg.factory;

import org.ni.rpg.core.enitiy.*;
import org.ni.rpg.core.enitiy.helper.Appearance;
import org.ni.rpg.core.enitiy.helper.Attribute;
import org.ni.rpg.core.enitiy.helper.Dimension;
import org.ni.rpg.core.strategy.DrawStrategy;
import org.ni.rpg.core.strategy.impl.GameObjectDrawStrategy;
import org.ni.rpg.core.strategy.impl.PlayerDrawStrategy;
import org.ni.rpg.utils.Commons;
import org.ni.rpg.utils.Properties;
import org.ni.rpg.utils.Constants;

import java.util.Random;

public class GameObjectFactory implements GameObjectAbstractFactory {
    @Override
    public Player createPlayer(int positionX, int positionY, Character[][] content,
                               String color, double attack, int range, int protection, String name,
                               String description, double health, String direction, int speed) {
        Attribute attribute = new Attribute(true, false, true,
                true, false, true);
        Dimension dimension = Commons.calculateDimension(content);
        Appearance appearance = new Appearance(positionX, positionY, dimension, content, color, true, direction);
        DrawStrategy drawStrategy = new PlayerDrawStrategy();
        Weapon weapon = new Weapon(appearance, attribute, attack, range, drawStrategy);
        Shield shield = new Shield(appearance, attribute, protection, drawStrategy);
        return new Player(appearance, attribute, name, description, health, weapon, shield, drawStrategy, speed);
    }


    @Override
    public GameState createGameMap(String name) {
        Attribute attribute = new Attribute(false, true,
                false, false, false, false);
        Dimension dimension = new Dimension(Properties.MAX_HEIGHT - 2, Properties.MAX_WIDTH - 2);
        Character[][] content = Commons.generateGameStateContent(Properties.MAX_HEIGHT - 2, Properties.MAX_WIDTH - 2);
        Appearance appearance = new Appearance(0, 0, dimension, content, "", true, "");
        GameState gameState = new GameState(appearance, attribute);
        Random rand = new Random();
        int positionX = Commons.randInt(1, Properties.MAX_WIDTH - 25);
        int positionY = Commons.randInt(1, Properties.MAX_HEIGHT - 5);
        Character[][] playerContent = Constants.PLAYER_CHAR;
        String color = "";
        double attack = Properties.PLAYER_ATTACK;
        int range = Properties.PLAYER_RANGE;
        int speed = Properties.PLAYER_SPEED;
        int protection = 0;
        String description = "";
        double health = Properties.PLAYER_HEALTH;
        String direction = Commons.getDirection();
        Player player = createPlayer(positionX, positionY, playerContent,
                color, attack, range, protection, name, description, health, direction, speed);
        gameState.addGameObject(player);
        gameState.setPlayerId(player.getId());
        for (int i = 0; i < Properties.AI_PLAYER; i++) {
            positionX = Commons.randInt(1, Properties.MAX_WIDTH - 25);
            positionY = Commons.randInt(1, Properties.MAX_HEIGHT - 5);
            playerContent = Constants.ENEMY_CHAR;
            attack = Properties.AI_PLAYER_ATTACK;
            range = Properties.AI_PLAYER_RANGE;
            speed = Properties.AI_PLAYER_SPEED;
            String aiPlayerName = "Enemy-" + i;
            health = Properties.AI_PLAYER_HEALTH;
            direction = Commons.getDirection();
            Player aiPlayer = createPlayer(positionX, positionY, playerContent,
                    color, attack, range, protection, aiPlayerName, description, health, direction, speed);
            gameState.addGameObject(aiPlayer);
        }
        for (int i = 0; i < Properties.OBSTACLE; i++) {
            positionX = Commons.randInt(1, Properties.MAX_WIDTH - 25);
            positionY = Commons.randInt(1, Properties.MAX_HEIGHT - 5);
            playerContent = Constants.TREE_CHAR;
            attack = 0.0;
            range = 0;
            Obstacle aiPlayer = createObstacle(positionX, positionY, playerContent, color, attack, range, protection);
            gameState.addGameObject(aiPlayer);
        }
        return gameState;
    }

    @Override
    public Obstacle createObstacle(int positionX, int positionY, Character[][] content,
                                   String color, double attack, int range, int protection) {
        Attribute attribute = new Attribute(false, false,
                false, false, false, false);
        Dimension dimension = Commons.calculateDimension(content);
        Appearance appearance = new Appearance(positionX, positionY, dimension,
                content, color, true, "");
        DrawStrategy drawStrategy = new GameObjectDrawStrategy();
        return new Obstacle(appearance, attribute, drawStrategy);
    }
}
