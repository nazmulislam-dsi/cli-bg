package org.ni.rpg.core.composite;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ni.rpg.core.enitiy.helper.Appearance;
import org.ni.rpg.core.enitiy.helper.Attribute;
import org.ni.rpg.core.enitiy.helper.Dimension;
import org.ni.rpg.exception.FrameSizeOutOfBound;

import static org.junit.Assert.*;
import static org.ni.rpg.utils.Properties.UP;

/**
 * Created by nazmul on 10/2/2018.
 */
public class GameComponentTest {

    private static String id;
    private static Appearance appearance;
    private static Attribute attribute;
    private static GameComponent gameComponent;

    private String expectedId="Nazmul";
    private static Appearance expectedPppearance;
    private static Attribute expectedAttribute;

    @BeforeClass
    public static void initTest(){
        System.out.println("Testing starts");
        id="Nazmul";
        appearance=new Appearance(10,20,new Dimension(10,20),new Character[10][10],"RED",true, UP);
        attribute=new Attribute(true,true,true,true,true,true);
        gameComponent =new GameComponent(appearance,attribute) {
            @Override
            public Character[][] draw(Character[][] characters) throws FrameSizeOutOfBound {
                return new Character[0][];
            }
        };
        gameComponent.setId(id);

        expectedPppearance=new Appearance(10,20,new Dimension(10,20),new Character[10][10],"RED",true, UP);
        expectedAttribute=new Attribute(true,true,true,true,true,true);
    }
    @Test
    public void testObject(){
        assertNotNull(gameComponent);
        assertNotNull(gameComponent.getAppearance());
        assertNotNull(gameComponent.getAttribute());
        assertNotNull(expectedPppearance);
        assertNotNull(expectedAttribute);

        assertEquals(expectedId, gameComponent.getId());
        assertEquals(expectedPppearance.getPositionX(), gameComponent.getAppearance().getPositionX());
        assertEquals(expectedPppearance.getPositionY(), gameComponent.getAppearance().getPositionY());
        assertEquals(expectedPppearance.getDimension().getWidth(), gameComponent.getAppearance().getDimension().getWidth());
        assertEquals(expectedPppearance.getDimension().getHeight(), gameComponent.getAppearance().getDimension().getHeight());
        assertEquals(expectedPppearance.getContent(), gameComponent.getAppearance().getContent());
        assertEquals(expectedPppearance.getColor(), gameComponent.getAppearance().getColor());
        assertEquals(expectedPppearance.isVisible(), gameComponent.getAppearance().isVisible());

        assertEquals(expectedAttribute.isCanMove(), gameComponent.getAttribute().isCanMove());
        assertEquals(expectedAttribute.isGoThrough(), gameComponent.getAttribute().isGoThrough());
        assertEquals(expectedAttribute.isTakeDamage(), gameComponent.getAttribute().isTakeDamage());
        assertEquals(expectedAttribute.isCanBeKilled(), gameComponent.getAttribute().isCanBeKilled());
        assertEquals(expectedAttribute.isRemoveAfterKilled(), gameComponent.getAttribute().isRemoveAfterKilled());

        System.out.println("testObject is passed");
    }
    @AfterClass
    public static void afterTesting(){
        System.out.println("Testing Ends");
    }
}
