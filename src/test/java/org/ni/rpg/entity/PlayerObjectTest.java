package org.ni.rpg.entity;

import org.junit.Test;
import org.ni.rpg.core.enitiy.Player;
import org.ni.rpg.factory.GameObjectAbstractFactory;
import org.ni.rpg.factory.GameObjectFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.ni.rpg.utils.Properties.UP;

/**
 * Created by nazmul on 10/2/2018.
 */
public class PlayerObjectTest {
    @Test()
    public void testPlayerCreation() {
        Character[][] ch = { {'x'} };
        GameObjectAbstractFactory factory = new GameObjectFactory();
        Player player = factory.createPlayer(0, 0, ch, "red", 50, 3,  50,"Nazmul", "wolverine", 100,UP,1 );
        System.out.println(player);
        assertNotNull(player);
        assertEquals(player.getName(), "Nazmul");
    }
}
