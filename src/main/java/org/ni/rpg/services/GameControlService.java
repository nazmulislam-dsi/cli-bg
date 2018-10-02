package org.ni.rpg.services;

import org.ni.rpg.enums.State;
import org.ni.rpg.exception.FrameSizeOutOfBound;

import java.io.IOException;

import static org.ni.rpg.utils.Properties.*;

/**
 * Created by nazmul on 9/29/2018.
 */
public class GameControlService {
    private static GameControlService singleton = null;
    private GameEngineService gameEngineService;

    public synchronized static GameControlService getInstance(GameEngineService gameEngineService) {
        if (singleton == null) singleton = new GameControlService(gameEngineService);
        return singleton;
    }

    private GameControlService(GameEngineService gameEngineService) {
        this.gameEngineService = gameEngineService;
    }

    /**
     * This method pass the key event to game services and filter the invalid input
     *
     * @param givenInput Key even given by user.
     * @throws FrameSizeOutOfBound
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void actionOnKeyEvent(String givenInput) throws FrameSizeOutOfBound, IOException, ClassNotFoundException {
        if (gameEngineService.getCURRENT_STATE().equals(State.MAIN_MENU)) {
            mainMenuControl(givenInput);
        } else if (gameEngineService.getCURRENT_STATE().equals(State.PLAYER_CREATION_NAME)) {
            playerCreationMenu(givenInput);
        } else if (gameEngineService.getCURRENT_STATE().equals(State.WEAPON_CHOOSE)) {
            weaponChooseMenu(givenInput);
        } else if (gameEngineService.getCURRENT_STATE().equals(State.INSTRUCTION)) {
            instructionMenu(givenInput);
        } else if (gameEngineService.getCURRENT_STATE().equals(State.GAME_PLAY)) {
            gamePlayWindow(givenInput);
        } else if (gameEngineService.getCURRENT_STATE().equals(State.PAUSE)) {
            pauseMenu(givenInput);
        } else if (gameEngineService.getCURRENT_STATE().equals(State.HELP)) {
            helpMenu(givenInput);
        } else if (gameEngineService.getCURRENT_STATE().equals(State.GAME_OVER)) {
            gameOveMenu(givenInput);
        } else if (gameEngineService.getCURRENT_STATE().equals(State.VICTORY)) {
            victoryMenu(givenInput);
        }
    }

    private void victoryMenu(String givenInput) throws FrameSizeOutOfBound {
        if (givenInput.equals(ONE)) {
            gameEngineService.playerCreationStart();
        } else if (givenInput.equals(THREE)) {
            gameEngineService.shutdown();
        } else {
            gameEngineService.showVictoryMenu();
        }
    }

    private void gameOveMenu(String givenInput) throws FrameSizeOutOfBound {
        if (givenInput.equals(ONE)) {
            gameEngineService.playerCreationStart();
        } else if (givenInput.equals(THREE)) {
            gameEngineService.shutdown();
        } else {
            gameEngineService.showGameOverMenu();
        }
    }

    private void helpMenu(String givenInput) throws FrameSizeOutOfBound, IOException {
        if (givenInput.equals(ONE)) {
            gameEngineService.returnToGameFromHelpMenu();
        } else if (givenInput.equals(TWO)) {
            gameEngineService.gameSave();
        } else if (givenInput.equals(HELP)) {
            gameEngineService.returnToGameFromHelpMenu();
        } else {
            gameEngineService.showHelpMenu();
        }
    }

    private void pauseMenu(String givenInput) throws FrameSizeOutOfBound, IOException {
        if (givenInput.equals(ONE)) {
            gameEngineService.gameResume();
        } else if (givenInput.equals(TWO)) {
            gameEngineService.gameSave();
        } else if (givenInput.equals(THREE)) {
            gameEngineService.gameReset();
        } else {
            gameEngineService.gamePause();
        }
    }

    private void gamePlayWindow(String givenInput) throws FrameSizeOutOfBound, IOException {
        if (givenInput.equals(THREE)) {
            gameEngineService.gamePause();
        } else if (givenInput.equals(UP)) {
            gameEngineService.playerMoveUp();
        } else if (givenInput.equals(DOWN)) {
            gameEngineService.playerMoveDown();
        } else if (givenInput.equals(LEFT)) {
            gameEngineService.playerMoveLeft();
        } else if (givenInput.equals(RIGHT)) {
            gameEngineService.playerMoveRight();
        } else if (givenInput.equals(ONE)) {
            gameEngineService.generateFrame();
        } else if (givenInput.equals(TWO)) {
            gameEngineService.gameSave();
        } else if (givenInput.equals(ZERO)) {
            gameEngineService.showStatus();
        } else if (givenInput.equals(FIRE)) {
            gameEngineService.playerAction();
        } else if (givenInput.equals(HELP)) {
            gameEngineService.showHelpMenu();
        } else {
            gameEngineService.generateFrame();
        }
    }

    private void instructionMenu(String givenInput) throws FrameSizeOutOfBound {
        if (givenInput.equals(ONE)) {
            gameEngineService.startGame();
        } else {
            gameEngineService.showGameStartMenu();
        }
    }

    private void weaponChooseMenu(String givenInput) throws FrameSizeOutOfBound {
        if (givenInput.equals(ONE)) {
            gameEngineService.weaponChoose(ONE);
        } else if (givenInput.equals(TWO)) {
            gameEngineService.weaponChoose(TWO);
        } else if (givenInput.equals(THREE)) {
            gameEngineService.weaponChoose(THREE);
        } else {
            gameEngineService.showPlayerCreationMenuWeapon();
        }
    }

    private void playerCreationMenu(String givenInput) throws FrameSizeOutOfBound {
        if (givenInput.equals(THREE)) {
            gameEngineService.showStartMenu();
        } else if (givenInput.equals("")) {
            gameEngineService.playerCreationStart();
        } else {
            gameEngineService.createGameState(givenInput);
        }
    }

    private void mainMenuControl(String givenInput) throws FrameSizeOutOfBound, IOException, ClassNotFoundException {
        if (givenInput.equals(ONE)) {
            gameEngineService.playerCreationStart();
        } else if (givenInput.equals(TWO)) {
            gameEngineService.gameLoad();
        } else if (givenInput.equals(HELP)) {
            gameEngineService.showStartMenuHelp();
        } else if (givenInput.equals(THREE)) {
            gameEngineService.shutdown();
        } else {
            gameEngineService.showStartMenu();
        }
    }

}
