package org.ni.rpg;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.ni.rpg.core.composite.GameComponentTest;
import org.ni.rpg.services.GameControllerTest;
import org.ni.rpg.services.GameEngineServiceTest;
import org.ni.rpg.services.MenuRendererTest;
import org.ni.rpg.entity.*;
import org.ni.rpg.entity.helper.AppearanceTest;
import org.ni.rpg.entity.helper.AttributeTest;
import org.ni.rpg.entity.helper.DimensionTest;
import org.ni.rpg.enums.ColorCodeTest;
import org.ni.rpg.enums.StateTest;
import org.ni.rpg.listener.KeyBoardListenerTest;
import org.ni.rpg.utils.CommonsTest;

/**
 * Created by nazmul on 10/2/2018.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        /* core */
        GameComponentTest.class,

        /* services */
        GameControllerTest.class,

        /* entity */
        AppearanceTest.class,
        AttributeTest.class,
        DimensionTest.class,
        FrameTest.class,
        GameStateTest.class,
        ObstacleTest.class,
        PlayerObjectTest.class,
        ShieldTest.class,
        WeaponTest.class,

        /* enum */
        ColorCodeTest.class,
        StateTest.class,

        /* utility */
        CommonsTest.class,

        /* service */
        KeyBoardListenerTest.class,
        MenuRendererTest.class,
        GameEngineServiceTest.class,

        /* listener */
        KeyBoardListenerTest.class

})
public class MainTest {
}
