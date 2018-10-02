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
import static org.junit.Assert.assertNotEquals;
import static org.ni.rpg.utils.Properties.*;
import static org.ni.rpg.utils.CommonsTest.PLAYER_NAME;
import static org.ni.rpg.utils.Properties.SAVE_FILE_NAME;

/**
 * Created by nazmul on 10/1/2018.
 */

public class GameControllerTest {

    public static GameEngineService gameEngineService = null;

    private static GameControlService gameControlService;

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
    public void actionOnKeyEventTest_WEAPON_CHOOSE_ONE_weaponChoose() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {


        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        assertEquals(State.PLAYER_CREATION_NAME, gameEngineService.getCURRENT_STATE());

        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        assertEquals(State.WEAPON_CHOOSE, gameEngineService.getCURRENT_STATE());

        gameControlService.actionOnKeyEvent(ONE);
        assertEquals(State.INSTRUCTION, gameEngineService.getCURRENT_STATE());
        assertNotEquals(null, gameEngineService.getGameState());
    }

    @Test
    public void actionOnKeyEventTest_MAIN_MENU_LOAD_GAME() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(TWO);
        assertEquals(true, gameEngineService.getCURRENT_STATE().equals(State.MAIN_MENU)
                || gameEngineService.getCURRENT_STATE().equals(State.GAME_PLAY));
    }

    @Test
    public void actionOnKeyEventTest_MAIN_MENU_any() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent("any");
        assertEquals(true, gameEngineService.getCURRENT_STATE().equals(State.MAIN_MENU));
    }

    @Test
    public void actionOnKeyEventTest_MAIN_MENU_BackFromPLAYER_CREATION() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(THREE);
        assertEquals(true, gameEngineService.getCURRENT_STATE().equals(State.MAIN_MENU));
    }

    @Test
    public void actionOnKeyEventTest_PLAYER_CREATION_Empty() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent("");
        assertEquals(true, gameEngineService.getCURRENT_STATE().equals(State.PLAYER_CREATION_NAME));
    }

    @Test
    public void actionOnKeyEventTest_MAIN_MENU_HELP() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(HELP);
        assertEquals(true, gameEngineService.getCURRENT_STATE().equals(State.MAIN_MENU));
    }


    @Test
    public void actionOnKeyEventTest_WEAPON_CHOOSE_ELSE_weaponChoose() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent("other");
        assertEquals(State.WEAPON_CHOOSE, gameEngineService.getCURRENT_STATE());
        assertNotEquals(null, gameEngineService.getGameState());
    }


    @Test
    public void actionOnKeyEventTest_INSTRUCTION_startGame() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(ONE);
        assertEquals(State.GAME_PLAY, gameEngineService.getCURRENT_STATE());
    }

    @Test
    public void actionOnKeyEventTest_INSTRUCTION_ANY() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent("any");
        assertEquals(State.INSTRUCTION, gameEngineService.getCURRENT_STATE());
    }


    @Test
    public void actionOnKeyEventTest_GAME_PLAY_gamePause() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(THREE);
        assertEquals(State.PAUSE, gameEngineService.getCURRENT_STATE());
    }


    @Test
    public void actionOnKeyEventTest_GAME_PLAY_playerMoveDown() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(DOWN);
        assertEquals(State.GAME_PLAY, gameEngineService.getCURRENT_STATE());
    }

    @Test
    public void actionOnKeyEventTest_GAME_PLAY_playerMoveLeft() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(TWO);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(LEFT);
        assertEquals(State.GAME_PLAY, gameEngineService.getCURRENT_STATE());
    }


    @Test
    public void actionOnKeyEventTest_GAME_PLAY_playerMoveRight() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(THREE);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(RIGHT);
        assertEquals(State.GAME_PLAY, gameEngineService.getCURRENT_STATE());
    }

    @Test
    public void actionOnKeyEventTest_GAME_PLAY_playerMoveUp() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(UP);
        assertEquals(State.GAME_PLAY, gameEngineService.getCURRENT_STATE());
    }

    @Test
    public void actionOnKeyEventTest_GAME_PLAY_ONE() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(ONE);
        assertEquals(State.GAME_PLAY, gameEngineService.getCURRENT_STATE());
    }

    @Test
    public void actionOnKeyEventTest_GAME_PLAY_FIRE() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent("F");
        assertEquals(State.GAME_PLAY, gameEngineService.getCURRENT_STATE());
    }

    @Test
    public void actionOnKeyEventTest_GAME_PLAY_gameSave() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(TWO);
        String filename = SAVE_FILE_NAME;
        File saveFile = new File(filename);
        assertEquals(true,saveFile.exists());


    }

    @Test
    public void actionOnKeyEventTest_GAME_PLAY_showStatus() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent("0");
        assertEquals(false,Commons.isShowState());

    }

    @Test
    public void actionOnKeyEventTest_GAME_PLAY_showHelpMenu() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(HELP);
        assertEquals(State.HELP, gameEngineService.getCURRENT_STATE());

    }

    @Test
    public void actionOnKeyEventTest_GAME_PLAY_ELSE_generateFrame() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent("anyInput");
        assertEquals(State.GAME_PLAY, gameEngineService.getCURRENT_STATE());
    }

    @Test
    public void actionOnKeyEventTest_PAUSE_gameResume() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(THREE);
        gameControlService.actionOnKeyEvent(ONE);
        assertEquals(State.GAME_PLAY, gameEngineService.getCURRENT_STATE());

    }

    @Test
    public void actionOnKeyEventTest_PAUSE_gameSave() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(THREE);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(THREE);
        gameControlService.actionOnKeyEvent(TWO);
        String filename = SAVE_FILE_NAME;
        File saveFile = new File(filename);
        assertEquals(true,saveFile.exists());
    }


    @Test
    public void actionOnKeyEventTest_PAUSE_gameReset() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(TWO);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(THREE);
        gameControlService.actionOnKeyEvent(THREE);
        assertEquals(State.MAIN_MENU, gameEngineService.getCURRENT_STATE());
    }

    @Test
    public void actionOnKeyEventTest_PAUSE_ELSE_gamePause() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(THREE);
        gameControlService.actionOnKeyEvent("anyInput");
        assertEquals(State.PAUSE, gameEngineService.getCURRENT_STATE());
    }


    @Test
    public void actionOnKeyEventTest_HELP_returnToGameFromHelpMenu() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(HELP);
        gameControlService.actionOnKeyEvent(ONE);
        assertEquals(State.GAME_PLAY, gameEngineService.getCURRENT_STATE());

    }

    @Test
    public void actionOnKeyEventTest_HELP_gameSave() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(HELP);
        String filename = SAVE_FILE_NAME;
        File saveFile = new File(filename);
        if(saveFile.exists())saveFile.delete();
        gameControlService.actionOnKeyEvent(TWO);
        saveFile = new File(filename);
        assertEquals(true,saveFile.exists());
    }
    

    @Test
    public void actionOnKeyEventTest_HELP_returnToGameFromHelpMenu2() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(TWO);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(HELP);
        gameControlService.actionOnKeyEvent(HELP);
        assertEquals(State.GAME_PLAY, gameEngineService.getCURRENT_STATE());
    }

    @Test
    public void actionOnKeyEventTest_HELP_ELSE_showHelpMenu() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(TWO);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(HELP);
        gameControlService.actionOnKeyEvent("anyInput");
        assertEquals(State.HELP, gameEngineService.getCURRENT_STATE());

    }

    @Test
    public void actionOnKeyEventTest_GAME_OVER_playerCreationStart() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(TWO);
        gameControlService.actionOnKeyEvent(ONE);
        gameEngineService.setCURRENT_STATE(State.GAME_OVER);
        gameControlService.actionOnKeyEvent("0");
        assertEquals(State.GAME_OVER, gameEngineService.getCURRENT_STATE());
    }


    @Test
    public void actionOnKeyEventTest_GAME_OVER_ELSE_showGameOverMenu() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(TWO);
        gameControlService.actionOnKeyEvent(ONE);
        gameEngineService.setCURRENT_STATE(State.GAME_OVER);
        gameControlService.actionOnKeyEvent("anyInput");
        assertEquals(State.GAME_OVER, gameEngineService.getCURRENT_STATE());
    }

    @Test
    public void actionOnKeyEventTest_GAME_OVER_ELSE_showMainMenu() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(TWO);
        gameControlService.actionOnKeyEvent(ONE);
        gameEngineService.setCURRENT_STATE(State.GAME_OVER);
        gameControlService.actionOnKeyEvent(ONE);
        assertEquals(State.WEAPON_CHOOSE, gameEngineService.getCURRENT_STATE());
    }

    @Test
    public void actionOnKeyEventTest_VICTORY_playerCreationStart() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(ONE);
        gameEngineService.setCURRENT_STATE(State.VICTORY);
        gameControlService.actionOnKeyEvent(ONE);
        assertEquals(true, gameEngineService.getCURRENT_STATE().equals(State.MAIN_MENU)
                || gameEngineService.getCURRENT_STATE().equals(State.WEAPON_CHOOSE));
    }



    @Test
    public void actionOnKeyEventTest_VICTORY_showVictoryMenu() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(ONE);
        gameEngineService.setCURRENT_STATE(State.VICTORY);
        gameControlService.actionOnKeyEvent("any");
        assertEquals(State.VICTORY, gameEngineService.getCURRENT_STATE());
    }

    @Test
    public void actionOnKeyEventTest_QUIT_showVictoryMenu() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(PLAYER_NAME);
        gameControlService.actionOnKeyEvent(ONE);
        gameControlService.actionOnKeyEvent(ONE);
        gameEngineService.setCURRENT_STATE(State.VICTORY);
        gameControlService.actionOnKeyEvent("any");
        assertEquals(State.VICTORY, gameEngineService.getCURRENT_STATE());
    }

    @AfterClass
    public static void afterTest(){
        System.out.println("Testing ends");
    }

}