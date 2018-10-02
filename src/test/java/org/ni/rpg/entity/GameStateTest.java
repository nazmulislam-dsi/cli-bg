package org.ni.rpg.entity;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ni.rpg.core.composite.GameComponent;
import org.ni.rpg.core.enitiy.helper.Appearance;
import org.ni.rpg.core.enitiy.helper.Attribute;
import org.ni.rpg.core.enitiy.helper.Dimension;
import org.ni.rpg.core.enitiy.GameState;
import org.ni.rpg.exception.FrameSizeOutOfBound;

import static org.junit.Assert.*;
import static org.ni.rpg.utils.Properties.UP;

/**
 * Created by nazmul on 10/2/2018.
 */
public class GameStateTest {

    private static String playerId;
    private static GameState gameState;
    private static GameComponent gameComponent;


    private static String expectedPlayerId="Nazmul";
    private static GameComponent expectedGameComponent;


    @BeforeClass
    public static void initTest(){
        System.out.println("Testing starts");
        playerId="Nazmul";
        gameState=new GameState(new Appearance(10,10,new Dimension(10,10),
                new Character[10][10],"RED",true, UP),new Attribute(true,true,true,true,true,true));

        gameState.setPlayerId(playerId);
        gameComponent =new GameComponent(new Appearance(10,20,new Dimension(10,20),new Character[10][10],"RED",true, UP),
                new Attribute(true,true,true,true,true,true)) {
            @Override
            public Character[][] draw(Character[][] characters) throws FrameSizeOutOfBound {
                return new Character[0][];
            }
        };
        gameComponent.setId(playerId);
        gameState.addGameObject(gameComponent);

        expectedGameComponent =new GameComponent(new Appearance(10,20,new Dimension(10,20),new Character[10][10],"RED",true, UP),
                new Attribute(true,true,true,true,true,true)) {
            @Override
            public Character[][] draw(Character[][] characters) throws FrameSizeOutOfBound {
                return new Character[0][];
            }
        };
        expectedGameComponent.setId(expectedPlayerId);
    }
    @Test
    public void testObject(){
        
        System.out.println("testObject is passed");
        assertNotNull(gameComponent);
        assertNotNull(gameState);
        assertNotNull(gameState.getGameObjct("Nazmul"));

        assertEquals(expectedPlayerId,gameState.getPlayerId());
        assertEquals(expectedGameComponent.getId(),gameState.getGameObjct(playerId).getId());
        assertEquals(expectedGameComponent.getAttribute().isCanMove(),gameState.getGameObjct(playerId).getAttribute().isCanMove());
        assertEquals(expectedGameComponent.getAttribute().isGoThrough(),gameState.getGameObjct(playerId).getAttribute().isGoThrough());
        assertEquals(expectedGameComponent.getAttribute().isTakeDamage(),gameState.getGameObjct(playerId).getAttribute().isTakeDamage());
        assertEquals(expectedGameComponent.getAttribute().isCanBeKilled(),gameState.getGameObjct(playerId).getAttribute().isCanBeKilled());
        assertEquals(expectedGameComponent.getAttribute().isKilled(),gameState.getGameObjct(playerId).getAttribute().isKilled());
        assertEquals(expectedGameComponent.getAttribute().isRemoveAfterKilled(),gameState.getGameObjct(playerId).getAttribute().isRemoveAfterKilled());

        assertEquals(expectedGameComponent.getAppearance().getPositionX(),gameState.getGameObjct(playerId).getAppearance().getPositionX());
        assertEquals(expectedGameComponent.getAppearance().getPositionY(),gameState.getGameObjct(playerId).getAppearance().getPositionY());
        assertEquals(expectedGameComponent.getAppearance().getDimension().getHeight(),gameState.getGameObjct(playerId).getAppearance().getDimension().getHeight());
        assertEquals(expectedGameComponent.getAppearance().getDimension().getWidth(),gameState.getGameObjct(playerId).getAppearance().getDimension().getWidth());
        assertEquals(expectedGameComponent.getAppearance().getContent(),gameState.getGameObjct(playerId).getAppearance().getContent());
        assertEquals(expectedGameComponent.getAppearance().getColor(),gameState.getGameObjct(playerId).getAppearance().getColor());
        assertEquals(expectedGameComponent.getAppearance().isVisible(),gameState.getGameObjct(playerId).getAppearance().isVisible());
    }
    @AfterClass
    public static void afterTesting(){
        System.out.println("Testing Ends");
    }

}
