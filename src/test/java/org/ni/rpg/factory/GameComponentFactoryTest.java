package org.ni.rpg.factory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ni.rpg.core.enitiy.GameState;
import org.ni.rpg.core.enitiy.Obstacle;
import org.ni.rpg.core.enitiy.Player;

import static org.junit.Assert.assertNotNull;
import static org.ni.rpg.utils.Properties.UP;
import static org.ni.rpg.utils.CommonsTest.PLAYER_NAME;

/**
 * Created by nazmul on 10/2/2018.
 */
public class GameComponentFactoryTest {

    private static GameObjectFactory gameObjectFactory;
    private Player player;
    private GameState gameState;
    private Obstacle obstacle;

    @BeforeClass
    public static void initTest(){
        System.out.println("Testing starts");
        gameObjectFactory=new GameObjectFactory();
    }

    @Test
    public void gameObjectPlayerCreationTest(){
        player=gameObjectFactory.createPlayer(10,10,new Character[10][10],"",10.0,10,10,PLAYER_NAME,"Description",100,UP,100);
        assertNotNull(player);
        System.out.println("Player create successfull");
    }

    @Test
    public void gameObjectGameMapCreationTest(){
        gameState=gameObjectFactory.createGameMap(PLAYER_NAME);
        assertNotNull(gameState);
        System.out.println("Game map create successfully");
    }

    @Test
    public void gameObjectObstacleCreationTest(){
        obstacle=gameObjectFactory.createObstacle(10,10,new Character[10][10],"",10.0,10,10);
        assertNotNull(obstacle.getAppearance());
        obstacle.setAppearance(obstacle.getAppearance());
        assertNotNull(obstacle.getAppearance());
        System.out.println("Obstacle create successfully");
    }

    @AfterClass
    public static void afterTesting(){
        System.out.println("Testing Ends");
    }
}