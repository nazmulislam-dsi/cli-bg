package org.ni.rpg.services;

import org.junit.*;
import org.ni.rpg.core.enitiy.Player;
import org.ni.rpg.core.strategy.impl.VoidDrawStrategy;
import org.ni.rpg.enums.State;
import org.ni.rpg.exception.FrameSizeOutOfBound;
import org.ni.rpg.utils.Commons;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.ni.rpg.utils.Properties.*;
import static org.ni.rpg.utils.CommonsTest.PLAYER_NAME;
import static org.ni.rpg.utils.Properties.SAVE_FILE_NAME;
import static org.ni.rpg.utils.Properties.UP_LEFT_RATIO;

/**
 * Created by nazmul on 10/2/2018.
 */
public class GameEngineServiceTest {
    public static GameEngineService gameEngineService;
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
    public void gameEngineTestLoadGame() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        String filename = SAVE_FILE_NAME;
        File saveFile = new File(filename);
        if(saveFile.exists())saveFile.delete();
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameEngineService.registerKeyEvent(ONE);
        gameEngineService.registerKeyEvent(PLAYER_NAME);
        gameEngineService.registerKeyEvent(ONE);
        gameEngineService.registerKeyEvent(ONE);
        assertEquals(State.GAME_PLAY, gameEngineService.getCURRENT_STATE());
        gameEngineService.registerKeyEvent(TWO);
        gameEngineService.registerKeyEvent(THREE);
        gameEngineService.registerKeyEvent(THREE);
        assertEquals(State.MAIN_MENU, gameEngineService.getCURRENT_STATE());
        gameEngineService.registerKeyEvent(TWO);
        assertEquals(State.GAME_PLAY, gameEngineService.getCURRENT_STATE());
        gameEngineService.registerKeyEvent(THREE);
        gameEngineService.registerKeyEvent(THREE);
        assertEquals(State.MAIN_MENU, gameEngineService.getCURRENT_STATE());
        if(saveFile.exists())saveFile.delete();
        gameEngineService.registerKeyEvent(TWO);
        assertEquals(State.MAIN_MENU, gameEngineService.getCURRENT_STATE());
    }

    @Test
    public void gameEngineTestPlayerMovement() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        String filename = SAVE_FILE_NAME;
        File saveFile = new File(filename);
        if(saveFile.exists())saveFile.delete();
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameEngineService.registerKeyEvent(ONE);
        gameEngineService.registerKeyEvent(PLAYER_NAME);
        gameEngineService.registerKeyEvent(ONE);
        gameEngineService.registerKeyEvent(ONE);
        assertEquals(State.GAME_PLAY, gameEngineService.getCURRENT_STATE());
        Player player = (Player) gameEngineService.getGameState().getGameObjct(gameEngineService.getGameState().getPlayerId());
        player.getAppearance().setPositionX(0);
        player.getAppearance().setPositionY(0);
        gameEngineService.registerKeyEvent(UP);
        gameEngineService.registerKeyEvent(UP);
        assertEquals(0, player.getAppearance().getPositionY());
        gameEngineService.registerKeyEvent(DOWN);
        assertEquals(0, player.getAppearance().getPositionY());
        gameEngineService.registerKeyEvent(DOWN);
        assertEquals(1, player.getAppearance().getPositionY());
        gameEngineService.registerKeyEvent(DOWN);
        assertEquals(1*2, player.getAppearance().getPositionY());
        gameEngineService.registerKeyEvent(UP);
        gameEngineService.registerKeyEvent(UP);
        assertEquals(1, player.getAppearance().getPositionY());
        gameEngineService.registerKeyEvent(FIRE);
        gameEngineService.registerKeyEvent(LEFT);
        gameEngineService.registerKeyEvent(LEFT);
        assertEquals(0, player.getAppearance().getPositionX());
        gameEngineService.registerKeyEvent(RIGHT);
        gameEngineService.registerKeyEvent(RIGHT);
        assertEquals(UP_LEFT_RATIO, player.getAppearance().getPositionX());
        gameEngineService.registerKeyEvent(RIGHT);
        assertEquals(UP_LEFT_RATIO*2, player.getAppearance().getPositionX());
        gameEngineService.registerKeyEvent(LEFT);
        gameEngineService.registerKeyEvent(LEFT);
        assertEquals(UP_LEFT_RATIO, player.getAppearance().getPositionX());
    }

    @Test
    public void gameEngineTestKeyEventAction() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        String filename = SAVE_FILE_NAME;
        File saveFile = new File(filename);
        if(saveFile.exists())saveFile.delete();
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameEngineService.registerKeyEvent(ONE);
        gameEngineService.registerKeyEvent(PLAYER_NAME);
        gameEngineService.registerKeyEvent(ONE);
        gameEngineService.registerKeyEvent(ONE);
        assertEquals(State.GAME_PLAY, gameEngineService.getCURRENT_STATE());

    }

    @Test
    public void gameEngineTestPlayerKill() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        String filename = SAVE_FILE_NAME;
        File saveFile = new File(filename);
        if(saveFile.exists())saveFile.delete();
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameEngineService.registerKeyEvent(ONE);
        gameEngineService.registerKeyEvent(PLAYER_NAME);
        gameEngineService.registerKeyEvent(ONE);
        gameEngineService.registerKeyEvent(ONE);
        assertEquals(State.GAME_PLAY, gameEngineService.getCURRENT_STATE());
        Player player = (Player) gameEngineService.getGameState().getGameObjct(gameEngineService.getGameState().getPlayerId());
        player.getAppearance().setPositionX(4);
        player.getAppearance().setPositionY(4);
        player.getAppearance().setDirection(UP);
        player.getWeapon().setAttack(5.0);
        player.getWeapon().setRange(5);
        String aiPlayerId = "";
        for(Player aiPlayer: gameEngineService.getGameState().getMovableGameObject()){
            aiPlayerId = aiPlayer.getId();
            break;
        }
        Player aiPlayer = (Player) gameEngineService.getGameState().getGameObjct(aiPlayerId);

        aiPlayer.setHealth(20.0);
        player.getAppearance().setDirection(UP);
        aiPlayer.getAppearance().setPositionX(4);
        aiPlayer.getAppearance().setPositionY(3);
        gameEngineService.aiAction(player);
        assertEquals(15.0, aiPlayer.getHealth(),0);

        aiPlayer.setHealth(20.0);
        aiPlayer.getAppearance().setPositionX(4);
        aiPlayer.getAppearance().setPositionY(5);
        player.getAppearance().setDirection(DOWN);
        gameEngineService.aiAction(player);
        assertEquals(15.0, aiPlayer.getHealth(),0);

        aiPlayer.setHealth(20.0);
        player.getAppearance().setDirection(LEFT);
        aiPlayer.getAppearance().setPositionX(3);
        aiPlayer.getAppearance().setPositionY(4);
        gameEngineService.aiAction(player);
        assertEquals(15.0, aiPlayer.getHealth(),0);

        aiPlayer.setHealth(20.0);
        player.getAppearance().setDirection(RIGHT);
        aiPlayer.getAppearance().setPositionX(5);
        aiPlayer.getAppearance().setPositionY(4);
        gameEngineService.aiAction(player);
        assertEquals(15.0, aiPlayer.getHealth(),0);

        player.setKilled(0);
        aiPlayer.setHealth(4.0);
        player.getAppearance().setDirection(RIGHT);
        aiPlayer.getAppearance().setPositionX(5);
        aiPlayer.getAppearance().setPositionY(4);
        gameEngineService.aiAction(player);
        assertEquals(1, player.getKilled());


    }

    @Test
    public void gameEngineTestDrawVoid() throws ClassNotFoundException,
            IOException, FrameSizeOutOfBound {
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        String filename = SAVE_FILE_NAME;
        File saveFile = new File(filename);
        if(saveFile.exists())saveFile.delete();
        gameEngineService.setCURRENT_STATE(State.MAIN_MENU);
        gameEngineService.registerKeyEvent(ONE);
        gameEngineService.registerKeyEvent(PLAYER_NAME);
        gameEngineService.registerKeyEvent(ONE);
        gameEngineService.registerKeyEvent(ONE);
        assertEquals(State.GAME_PLAY, gameEngineService.getCURRENT_STATE());
        Player player = (Player) gameEngineService.getGameState().getGameObjct(gameEngineService.getGameState().getPlayerId());
        player.getAppearance().setPositionX(4);
        player.getAppearance().setPositionY(4);
        player.getAppearance().setDirection(UP);
        player.getWeapon().setAttack(5.0);
        player.getWeapon().setRange(5);
        String aiPlayerId = "";
        for(Player aiPlayer: gameEngineService.getGameState().getMovableGameObject()){
            aiPlayerId = aiPlayer.getId();
            break;
        }
        Player aiPlayer = (Player) gameEngineService.getGameState().getGameObjct(aiPlayerId);
        aiPlayer.setDrawStrategy(new VoidDrawStrategy());
        gameEngineService.generateFrame();
        assertEquals(State.GAME_PLAY, gameEngineService.getCURRENT_STATE());
    }

    @AfterClass
    public static void afterTest(){
        System.out.println("Testing ends");
    }
}
