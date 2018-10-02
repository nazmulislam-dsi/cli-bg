package org.ni.rpg.services;

import org.ni.rpg.core.enitiy.helper.Dimension;
import org.ni.rpg.core.enitiy.GameState;
import org.ni.rpg.core.enitiy.Player;
import org.ni.rpg.enums.State;
import org.ni.rpg.exception.FrameSizeOutOfBound;
import org.ni.rpg.factory.GameObjectAbstractFactory;
import org.ni.rpg.factory.GameObjectFactory;
import org.ni.rpg.listener.KeyBoardListener;
import org.ni.rpg.utils.Commons;
import org.ni.rpg.utils.Constants;

import java.io.*;
import java.util.List;

import static org.ni.rpg.utils.Properties.*;

/**
 * Created by nazmul on 9/29/2018.
 */
public class GameEngineService {
    private static final GameEngineService instance = new GameEngineService();
    private static State CURRENT_STATE = State.MAIN_MENU;

    private MenuRenderer menuRenderer = MenuRenderer.getInstance();
    private KeyBoardListener keyBoardListener;
    private GameControlService gameControlService;
    private GameObjectAbstractFactory factory;
    private GameStateRenderer gameStateRenderer;
    private GameState gameState;

    private GameEngineService() {
        keyBoardListener = KeyBoardListener.getInstance(this);
        gameControlService = GameControlService.getInstance(this);
        gameStateRenderer = GameStateRenderer.getInstance();
        factory = new GameObjectFactory();
    }

    public static GameEngineService getInstance() {
        return instance;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * This Method actually start the keyboard listener and display the main menu
     *
     * @throws FrameSizeOutOfBound
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void init() throws FrameSizeOutOfBound, IOException, ClassNotFoundException {
        showStartMenu();
        keyBoardListener.listen();
    }

    /**
     * Display Main Menu
     *
     * @throws FrameSizeOutOfBound
     */
    public void showStartMenu() throws FrameSizeOutOfBound {
        CURRENT_STATE = State.MAIN_MENU;
        menuRenderer.showStartMenu();
    }

    /**
     * Show help menu
     *
     * @throws FrameSizeOutOfBound
     */
    public void showStartMenuHelp() throws FrameSizeOutOfBound {
        menuRenderer.inGameHelpMenu();
    }

    /**
     * Pass the key event to game controller to filter
     *
     * @param givenInput
     * @throws FrameSizeOutOfBound
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void registerKeyEvent(String givenInput) throws FrameSizeOutOfBound, IOException, ClassNotFoundException {
        gameControlService.actionOnKeyEvent(givenInput);
    }

    public State getCURRENT_STATE() {
        return CURRENT_STATE;
    }

    public void setCURRENT_STATE(State CURRENT_STATE) {
        this.CURRENT_STATE = CURRENT_STATE;
    }

    /**
     * Initiate player creation screen and asking for player name to create game main object.
     *
     * @throws FrameSizeOutOfBound
     */
    public void playerCreationStart() throws FrameSizeOutOfBound {
        this.CURRENT_STATE = State.PLAYER_CREATION_NAME;
        if (Commons.isEmpty(Commons.getPlayerName())) {
            menuRenderer.showPlayerCreationMenuName();
        } else {
            createGameState(Commons.getPlayerName());
        }
    }

    /**
     * After taking the name create the game object which will hold the entire map and all players position,
     * it's a composite object so it will be easier to be printed.
     *
     * @param name
     * @throws FrameSizeOutOfBound
     */
    public void createGameState(String name) throws FrameSizeOutOfBound {
        this.CURRENT_STATE = State.WEAPON_CHOOSE;
        gameState = factory.createGameMap(name);
        Commons.setPlayerName(name);
        showPlayerCreationMenuWeapon();
    }

    /**
     * Give player to choose the weapon to set the character.
     *
     * @throws FrameSizeOutOfBound
     */
    public void showPlayerCreationMenuWeapon() throws FrameSizeOutOfBound {
        menuRenderer.showPlayerCreationMenuWeapon();
    }

    /**
     * Start the game with gameState composite object and print the map
     *
     * @throws FrameSizeOutOfBound
     */
    public void startGame() throws FrameSizeOutOfBound {
        this.CURRENT_STATE = State.GAME_PLAY;
        gameStateRenderer.generateFrame(gameState);
    }

    /**
     * Generate each game frame when needed
     *
     * @throws FrameSizeOutOfBound
     */
    public void generateFrame() throws FrameSizeOutOfBound {
        gameStateRenderer.generateFrame(gameState);
    }

    /**
     * Sent shutdown signal to stop this process when player want to exit
     *
     * @throws FrameSizeOutOfBound
     */
    public void shutdown() throws FrameSizeOutOfBound {
        menuRenderer.showThanksMenu();
        System.exit(0);
    }

    /**
     * Player movement control move player to right
     *
     * @throws FrameSizeOutOfBound
     */
    public void playerMoveRight() throws FrameSizeOutOfBound {
        aiMove();
        Player player = (Player) gameState.getGameObjct(gameState.getPlayerId());
        playerMoveRight(player);
        this.generateFrame();

    }

    /**
     * Player movement control move AI and actual player to right
     *
     * @param player
     * @throws FrameSizeOutOfBound
     */
    public void playerMoveRight(Player player) throws FrameSizeOutOfBound {
        // TODO consider area before move weather that area is not occupied with another not go throughable abject
        if (player.getAppearance().getDirection().equals(RIGHT)) {
            int positionX = player.getAppearance().getPositionX();
            if (positionX + (player.getSpeed() * UP_LEFT_RATIO)
                    + player.getAppearance().getDimension().getWidth()
                    <= gameState.getAppearance().getDimension().getWidth()) {
                player.getAppearance().setPositionX(positionX + (player.getSpeed() * UP_LEFT_RATIO));
            } else {
                player.getAppearance().setPositionX(gameState.getAppearance()
                        .getDimension().getWidth() - player.getAppearance().getDimension().getWidth());
            }
        } else {
            player.getAppearance().setDirection(RIGHT);
        }

    }

    /**
     * Player movement control move actual player to Left
     *
     * @throws FrameSizeOutOfBound
     */
    public void playerMoveLeft() throws FrameSizeOutOfBound {
        aiMove();
        Player player = (Player) gameState.getGameObjct(gameState.getPlayerId());
        playerMoveLeft(player);
        this.generateFrame();

    }

    /**
     * Player movement control move AI and actual player to left
     *
     * @param player
     * @throws FrameSizeOutOfBound
     */
    public void playerMoveLeft(Player player) throws FrameSizeOutOfBound {
        // TODO consider area before move weather that area is not occupied with another not go throughable abject
        if (player.getAppearance().getDirection().equals(LEFT)) {
            int positionX = player.getAppearance().getPositionX();
            if (positionX - (player.getSpeed() * UP_LEFT_RATIO) >= 0) {
                player.getAppearance().setPositionX(positionX - (player.getSpeed() * UP_LEFT_RATIO));
            } else {
                player.getAppearance().setPositionX(0);
            }
        } else {
            player.getAppearance().setDirection(LEFT);
        }

    }

    /**
     * Player movement control move actual player to down
     *
     * @throws FrameSizeOutOfBound
     */
    public void playerMoveDown() throws FrameSizeOutOfBound {
        aiMove();
        Player player = (Player) gameState.getGameObjct(gameState.getPlayerId());
        playerMoveDown(player);
        this.generateFrame();

    }

    /**
     * Player movement control move AI and actual player to down
     *
     * @param player
     * @throws FrameSizeOutOfBound
     */
    public void playerMoveDown(Player player) throws FrameSizeOutOfBound {
        // TODO consider area before move weather that area is not occupied with another not go throughable abject
        if (player.getAppearance().getDirection().equals(DOWN)) {
            int positionY = player.getAppearance().getPositionY();
            if (positionY + player.getSpeed()
                    + player.getAppearance().getDimension().getHeight()
                    <= gameState.getAppearance().getDimension().getHeight()) {

                player.getAppearance().setPositionY(positionY + player.getSpeed());
            } else {
                player.getAppearance().setPositionY(
                        gameState.getAppearance().getDimension().getHeight()
                                - player.getAppearance().getDimension().getHeight());
            }
        } else {
            player.getAppearance().setDirection(DOWN);
        }

    }

    /**
     * Player movement control move actual player to up
     *
     * @throws FrameSizeOutOfBound
     */
    public void playerMoveUp() throws FrameSizeOutOfBound {
        aiMove();
        Player player = (Player) gameState.getGameObjct(gameState.getPlayerId());
        playerMoveUp(player);
        this.generateFrame();

    }

    /**
     * Player movement control move AI and actual player to up
     *
     * @param player
     * @throws FrameSizeOutOfBound
     */
    public void playerMoveUp(Player player) throws FrameSizeOutOfBound {
        // TODO consider area before move weather that area is not occupied with another not go throughable abject
        if (player.getAppearance().getDirection().equals(UP)) {
            int positionY = player.getAppearance().getPositionY();
            if (positionY - player.getSpeed() >= 0) {
                player.getAppearance().setPositionY(positionY - player.getSpeed());
            } else {
                player.getAppearance().setPositionY(0);
            }
        } else {
            player.getAppearance().setDirection(UP);
        }
    }

    /**
     * This method randomly select the direction and the movement of AI player
     *
     * @throws FrameSizeOutOfBound
     */
    public void aiMove() throws FrameSizeOutOfBound {
        List<Player> movableGameObject = gameState.getMovableGameObject();
        for (Player player : movableGameObject) {
            int direction = Commons.randInt(1, 4);
            if (direction == 1) {
                playerMoveUp(player);
            } else if (direction == 2) {
                playerMoveDown(player);
            } else if (direction == 3) {
                playerMoveLeft(player);
            } else if (direction == 4) {
                playerMoveRight(player);
            }
        }
        for (Player player : movableGameObject) {
            if (Commons.randInt(1, 2) == 2) {
                aiAction(player);
            }
        }
    }

    /**
     * anytime when player need to pause the game
     *
     * @throws FrameSizeOutOfBound
     */
    public void gamePause() throws FrameSizeOutOfBound {
        this.CURRENT_STATE = State.PAUSE;
        menuRenderer.showPauseMenu(gameStateRenderer.getGameFrame(gameState));
    }

    /**
     * To resume the game from pause state
     *
     * @throws FrameSizeOutOfBound
     */
    public void gameResume() throws FrameSizeOutOfBound {
        this.CURRENT_STATE = State.GAME_PLAY;
        gameStateRenderer.generateFrame(gameState);
    }

    /**
     * as our main core object of this game is gameState and that object is Serializable,
     * so we save the whole object to a file to load the game even after quite the game
     *
     * @throws IOException
     * @throws FrameSizeOutOfBound
     */
    public void gameSave() throws IOException, FrameSizeOutOfBound {
        String filename = SAVE_FILE_NAME;
        FileOutputStream file = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(gameState);
        out.close();
        file.close();
        menuRenderer.showPauseMenu(gameStateRenderer.getGameFrame(gameState));
    }

    /**
     * load the game from the save file which we preserved a Serialized string, this method read that file and
     * deserialize that object into core component and initiate the required param
     *
     * @throws IOException
     * @throws FrameSizeOutOfBound
     * @throws ClassNotFoundException
     */
    public void gameLoad() throws IOException, FrameSizeOutOfBound, ClassNotFoundException {
        String filename = SAVE_FILE_NAME;
        File saveFile = new File(filename);
        try {
            if (saveFile.exists()) {
                FileInputStream file = new FileInputStream(saveFile);
                ObjectInputStream in = new ObjectInputStream(file);
                gameState = (GameState) in.readObject();
                in.close();
                file.close();
                this.CURRENT_STATE = State.GAME_PLAY;
                Player player = (Player) gameState.getGameObjct(gameState.getPlayerId());
                Commons.setPlayerName(player.getName());
                gameStateRenderer.generateFrame(gameState);
            } else {
                menuRenderer.showNoSaveFileMenu();
            }
        } catch (Exception ex) {
            menuRenderer.showNoSaveFileMenu();
        }
    }


    public void gameReset() throws FrameSizeOutOfBound {
        GameState gameState = null;
        CURRENT_STATE = State.MAIN_MENU;
        menuRenderer.showStartMenu();
    }

    public void playerAction() throws FrameSizeOutOfBound {
        aiMove();
        Player player = (Player) gameState.getGameObjct(gameState.getPlayerId());
        aiAction(player);
        this.generateFrame();
    }

    /**
     * this is the key method then the actual fight is being calculated. Mainly based on direction and weapon range
     * this method select the players which with take damage. player will be removed when the health become zero.
     * After receiving or giving damage on the next frame you will find the stat.
     *
     * @param player
     * @throws FrameSizeOutOfBound
     */
    public void aiAction(Player player) throws FrameSizeOutOfBound {
        List<Player> aiPlayerList = gameState.getAllKillAbleGameObject();
        int alive = aiPlayerList.size();
        int killed = 0;
        // TODO consider area and add configurable damage amount according to %
        for (Player aiPlayer : aiPlayerList) {
            int damageable = 0;
            if (!player.getId().equals(aiPlayer.getId())) {
                if (player.getAppearance().getDirection().equals(UP)
                        && aiPlayer.getAppearance().getPositionX()
                        == player.getAppearance().getPositionX() && aiPlayer.getAppearance().getPositionY()
                        < player.getAppearance().getPositionY() && aiPlayer.getAppearance().getPositionY()
                        > player.getAppearance().getPositionY() - player.getWeapon().getRange()) {
                    damageable = +HEAD_SHOT_DAMAGE;
                } else if (player.getAppearance().getDirection().equals(DOWN)
                        && aiPlayer.getAppearance().getPositionX()
                        == player.getAppearance().getPositionX() && aiPlayer.getAppearance().getPositionY()
                        > player.getAppearance().getPositionY() && aiPlayer.getAppearance().getPositionY()
                        < player.getAppearance().getPositionY() + player.getWeapon().getRange()) {
                    damageable = +HEAD_SHOT_DAMAGE;
                } else if (player.getAppearance().getDirection().equals(LEFT)
                        && aiPlayer.getAppearance().getPositionY()
                        == player.getAppearance().getPositionY()
                        && aiPlayer.getAppearance().getPositionX()
                        < player.getAppearance().getPositionX()
                        && aiPlayer.getAppearance().getPositionX()
                        > player.getAppearance().getPositionX() - (player.getWeapon().getRange() * UP_LEFT_RATIO)) {
                    damageable = +HEAD_SHOT_DAMAGE;
                } else if (player.getAppearance().getDirection().equals(RIGHT)
                        && aiPlayer.getAppearance().getPositionY()
                        == player.getAppearance().getPositionY()
                        && aiPlayer.getAppearance().getPositionX()
                        > player.getAppearance().getPositionX()
                        && aiPlayer.getAppearance().getPositionX()
                        < player.getAppearance().getPositionX() + (player.getWeapon().getRange() * UP_LEFT_RATIO)) {
                    damageable = +HEAD_SHOT_DAMAGE;
                }
            }
            if (damageable > 0) {
                if (aiPlayer.getHealth() - player.getWeapon().getAttack() <= 0.0) {
                    if (aiPlayer.getId().equals(gameState.getPlayerId())) {
                        showGameOverMenu();
                    }
                    killed++;
                    player.setStatOn(true);
                    player.setHealth(player.getHealth() + KILLING_BONUS_HEALTH);
                    player.setKilled(player.getKilled() + 1);
                    player.getWeapon().setAttack(player.getWeapon().getAttack() + KILLING_BONUS_ATTACK);

                    aiPlayer.setHealth(0.0);
                    Dimension dimension = Commons.calculateDimension(Constants.DEAD_CHAR);
                    aiPlayer.getAppearance().setDimension(dimension);
                    aiPlayer.getAppearance().setContent(Constants.DEAD_CHAR);
                    aiPlayer.getAttribute().setCanBeKilled(false);
                    aiPlayer.getAttribute().setCanMove(false);
                    aiPlayer.getAttribute().setKilled(true);
                    aiPlayer.getAttribute().setTakeDamage(false);
                } else {
                    aiPlayer.setHealth(aiPlayer.getHealth() - player.getWeapon().getAttack());
                    aiPlayer.setStatOn(true);
                }
            }
        }
        if ((alive - 1) == killed) {
            if (player.getId().equals(gameState.getPlayerId())) {
                showVictoryMenu();
            } else {
                showGameOverMenu();
            }
        }
    }

    public void showGameOverMenu() throws FrameSizeOutOfBound {
        CURRENT_STATE = State.GAME_OVER;
        menuRenderer.showGameOverMenu();
    }

    public void showVictoryMenu() throws FrameSizeOutOfBound {
        CURRENT_STATE = State.VICTORY;
        menuRenderer.showVictoryMenu();
    }

    public void showHelpMenu() throws FrameSizeOutOfBound {
        CURRENT_STATE = State.HELP;
        menuRenderer.inGameHelpMenu();
    }

    public void returnToGameFromHelpMenu() throws FrameSizeOutOfBound {
        this.CURRENT_STATE = State.GAME_PLAY;
        gameStateRenderer.generateFrame(gameState);
    }

    public void showStatus() throws FrameSizeOutOfBound {
        Commons.setShowState(true);
        gameStateRenderer.generateFrame(gameState);
        Commons.setShowState(false);
    }

    /**
     * Choose the weapon
     *
     * @param choice
     * @throws FrameSizeOutOfBound
     */
    public void weaponChoose(String choice) throws FrameSizeOutOfBound {
        Player player = (Player) gameState.getGameObjct(gameState.getPlayerId());
        if (choice.equals(ONE)) {
            player.getWeapon().setAttack(WEAPON_ATTACK_1);
            player.getWeapon().setRange(WEAPON_RANGE_1);
        } else if (choice.equals(TWO)) {
            player.getWeapon().setAttack(WEAPON_ATTACK_2);
            player.getWeapon().setRange(WEAPON_RANGE_2);
        } else if (choice.equals(THREE)) {
            player.getWeapon().setAttack(WEAPON_ATTACK_3);
            player.getWeapon().setRange(WEAPON_RANGE_3);
        }
        this.CURRENT_STATE = State.INSTRUCTION;
        showGameStartMenu();

    }

    public void showGameStartMenu() throws FrameSizeOutOfBound {
        menuRenderer.showGameStartMenu();
    }
}
