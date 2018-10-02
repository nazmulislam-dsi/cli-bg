package org.ni.rpg.utils;

import org.junit.Test;
import org.ni.rpg.core.enitiy.helper.Dimension;
import org.ni.rpg.core.enitiy.Player;
import org.ni.rpg.factory.GameObjectAbstractFactory;
import org.ni.rpg.factory.GameObjectFactory;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Created by nazmul on 10/2/2018.
 */
public class CommonsTest {

    public static String PLAYER_NAME = "Nazmul";


    @Test
    public void testCalculateDimension() {
        Character[][] ch = {{' ', ' '}, {' ', ' '}};
        Dimension dimension = Commons.calculateDimension(ch);
        assertNotNull(dimension);
        assertEquals(dimension.getWidth(), 2);
        assertEquals(dimension.getHeight(), 2);
    }

    @Test
    public void testGenerateGameStateContent() {
        Character[][] content = Commons.generateGameStateContent(2,2);
        Character[][] expectedContent = {{' ', ' '}, {' ', ' '}};

        assertEquals(expectedContent, content);
    }

    @Test
    public void testRandomInt() {
        int rand = Commons.randInt(100,200);
        for(int i = 0; i < 1000; i++) {
            if(rand > 200 || rand < 100) {
                fail("Random number is out of range");
            }
        }
    }

    @Test
    public void testGetDirection() {
        for(int i = 0; i < 1000; i++) {
            String direction = Commons.getDirection();
            if (!Arrays.asList("s", "a", "d", "w").contains(direction)) {
                fail("wrong direction");
            }
        }
    }

    @Test
    public void testIsNull() {
        List list = new ArrayList();
        Map map = new HashMap();
        String str = "";

        if(!Commons.isEmpty(list)) {
            fail("List is not empty. Expected to be empty");
        }

        if(!Commons.isEmpty(map)) {
            fail("Map is not empty. Expected to be empty");
        }

        if(!Commons.isEmpty(str)) {
            fail("String is not empty. Expected to be empty");
        }
    }

    @Test
    public void testGetStatusLine() {
        Character[][] ch = { {'x'} };
        GameObjectAbstractFactory factory = new GameObjectFactory();
        Player player = factory.createPlayer(0, 0, ch, "red", 50, 3,  50,"Nazmul", "wolverine", 100, "r",1 );
        String expected = "Nazmul-H:100.0 A:50.0 K:0";
        assertEquals(expected, Commons.getStatusLine(player));
    }
}
