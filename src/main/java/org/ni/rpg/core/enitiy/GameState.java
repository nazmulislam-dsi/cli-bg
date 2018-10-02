package org.ni.rpg.core.enitiy;

import org.ni.rpg.core.composite.GameComponent;
import org.ni.rpg.core.enitiy.helper.Appearance;
import org.ni.rpg.core.enitiy.helper.Attribute;
import org.ni.rpg.exception.FrameSizeOutOfBound;
import org.ni.rpg.utils.Commons;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by nazmul on 9/29/2018.
 */
public class GameState extends GameComponent {
    private static final long serialversionUID = 538219554L;

    private String playerId;

    ConcurrentHashMap<String, GameComponent> gameObjects = new ConcurrentHashMap<>();

    public GameState(Appearance appearance, Attribute attribute) {
        super(appearance, attribute);
    }

    public void addGameObject(GameComponent gameComponent) {
        gameObjects.put(gameComponent.getId(), gameComponent);
    }

    public void removeGameObject(GameComponent gameComponent) {
        if (gameObjects.contains(gameComponent.getId())) {
            gameObjects.remove(gameComponent.getId());
        }
    }

    public void removeGameObject(String gameObjectId) {
        if (gameObjects.contains(gameObjectId)) {
            gameObjects.remove(gameObjectId);
        }
    }

    public Character[][] draw(Character[][] characters) throws FrameSizeOutOfBound {
        characters = Commons.generateGameStateContent(getAppearance().getDimension().getHeight(),
                getAppearance().getDimension().getWidth());
        for (GameComponent gameComponent : gameObjects.values()) {
            characters = gameComponent.draw(characters);
        }
        return characters;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public GameComponent getGameObjct(String key) {
        return gameObjects.get(key);
    }

    public List<Player> getMovableGameObject() {
        return gameObjects.values().stream()
                .filter(Player.class::isInstance)
                .filter(v -> v.getAttribute().isCanMove())
                .filter(v -> !v.getId().equals(this.getPlayerId()))
                .map(Player.class::cast)
                .collect(Collectors.toList());
    }

    public List<Player> getAllKillAbleGameObject() {

        return gameObjects.values().stream()
                .filter(Player.class::isInstance)
                .filter(v -> v.getAttribute().isCanBeKilled())
                .map(Player.class::cast)
                .collect(Collectors.toList());
    }
}
