package org.ni.rpg.entity;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ni.rpg.core.enitiy.helper.Appearance;
import org.ni.rpg.core.enitiy.helper.Attribute;
import org.ni.rpg.core.enitiy.helper.Dimension;
import org.ni.rpg.core.enitiy.Obstacle;
import org.ni.rpg.core.strategy.impl.GameObjectDrawStrategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.ni.rpg.utils.Properties.UP;

/**
 * Created by nazmul on 10/2/2018.
 */
public class ObstacleTest {

    private static Obstacle obstacle;
    private static Appearance expectedAppearance;
    private  static Attribute expectedAttribute;

    @BeforeClass
    public static void initTest(){
        System.out.println("Testing starts");
        obstacle=new Obstacle(new Appearance(10, 10, new Dimension(10, 10), new Character[10][10], "RED", true, UP),
                new Attribute(true, true, true, true, true, true), new GameObjectDrawStrategy());

        expectedAppearance= new Appearance(10, 10, new Dimension(10, 10), new Character[10][10], "RED", true, UP);
        expectedAttribute=new Attribute(true, true, true, true, true, true);
    }
    @Test
    public void testObject(){
        assertNotNull(obstacle);
        assertNotNull(expectedAppearance);
        assertNotNull(expectedAttribute);

        assertEquals(expectedAppearance.getPositionX(),obstacle.getAppearance().getPositionX());
        assertEquals(expectedAppearance.getPositionY(),obstacle.getAppearance().getPositionY());
        assertEquals(expectedAppearance.getDimension().getHeight(),obstacle.getAppearance().getDimension().getHeight());
        assertEquals(expectedAppearance.getDimension().getWidth(),obstacle.getAppearance().getDimension().getWidth());
        assertEquals(expectedAppearance.getContent(),obstacle.getAppearance().getContent());
        assertEquals(expectedAppearance.getColor(),obstacle.getAppearance().getColor());
        assertEquals(expectedAppearance.isVisible(),obstacle.getAppearance().isVisible());

        assertEquals(expectedAttribute.isCanMove(),obstacle.getAttribute().isCanMove());
        assertEquals(expectedAttribute.isGoThrough(),obstacle.getAttribute().isGoThrough());
        assertEquals(expectedAttribute.isTakeDamage(),obstacle.getAttribute().isTakeDamage());
        assertEquals(expectedAttribute.isCanBeKilled(),obstacle.getAttribute().isCanBeKilled());
        assertEquals(expectedAttribute.isKilled(),obstacle.getAttribute().isKilled());
        assertEquals(expectedAttribute.isRemoveAfterKilled(),obstacle.getAttribute().isRemoveAfterKilled());

        System.out.println("testObject is passed");

    }
    @AfterClass
    public static void afterTesting(){
        System.out.println("Testing Ends");
    }
}
