package org.ni.rpg.services;

import org.junit.*;
import org.ni.rpg.enums.State;
import org.ni.rpg.exception.FrameSizeOutOfBound;
import org.ni.rpg.utils.Commons;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.ni.rpg.utils.Properties.*;
import static org.ni.rpg.utils.Properties.SAVE_FILE_NAME;

/**
 * Created by nazmul on 10/1/2018.
 */
public class MenuRendererTest {

    public static GameEngineService gameEngineService = null;

    private static GameControlService gameControlService;
    MenuRenderer menuRenderer = MenuRenderer.getInstance();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeClass
    public static void initTest() {
        System.out.println("Testing starts");
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        gameEngineService = GameEngineService.getInstance();
        gameControlService = GameControlService.getInstance(gameEngineService);
        Commons.setPlayerName("");
        gameEngineService.setGameState(null);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void menuRendererTest_MAIN_MENU_LOAD_GAME() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        String filename = SAVE_FILE_NAME;
        File saveFile = new File(filename);
        if(saveFile.exists())saveFile.delete();
        gameControlService.actionOnKeyEvent(TWO);
        assertEquals(State.MAIN_MENU, gameEngineService.getCURRENT_STATE());
    }

    @AfterClass
    public static void afterTest(){
        System.out.println("Testing ends");
    }
}
