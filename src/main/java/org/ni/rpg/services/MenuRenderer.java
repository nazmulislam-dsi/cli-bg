package org.ni.rpg.services;

import org.ni.rpg.core.enitiy.Frame;
import org.ni.rpg.exception.FrameSizeOutOfBound;
import org.ni.rpg.utils.Commons;
import org.ni.rpg.utils.Properties;

import java.util.stream.Stream;

import static org.ni.rpg.utils.Properties.*;

/**
 * Created by nazmul on 9/29/2018.
 */
public class MenuRenderer {
    private static final MenuRenderer instance = new MenuRenderer();

    private MenuRenderer() {
    }

    public static MenuRenderer getInstance() {
        return instance;
    }

    /**
     * Showing main menu
     *
     * @throws FrameSizeOutOfBound
     */
    public void showStartMenu() throws FrameSizeOutOfBound {
        Frame mainFrame = new Frame(Properties.MAX_WIDTH, Properties.MAX_HEIGHT);
        Frame welcomeMessage = new Frame("Welcome to cli-battleground");
        welcomeMessage.drawBorder();
        Frame instructionFrame = new Frame(22, 10);
        Frame pressInstruction1 = new Frame("Press " + ONE + " to Start", true);
        Frame pressInstruction2 = new Frame("Press " + TWO + " to Resume", true);
        Frame pressInstruction3 = new Frame("Press " + THREE + " to Exit", true);
        Frame keyPressSuggestion = new Frame("After taping any word. press enter.", true);
        instructionFrame.drawMiddle(Stream.of(pressInstruction1, pressInstruction2,
                pressInstruction3).toArray(Frame[]::new));
        mainFrame.drawMiddle(Stream.of(welcomeMessage, instructionFrame, keyPressSuggestion).toArray(Frame[]::new));
        System.out.println(mainFrame.getFrameForPrint());
    }


    /**
     * thanking the user upon quit
     *
     * @throws FrameSizeOutOfBound
     */
    public void showThanksMenu() throws FrameSizeOutOfBound {
        Frame mainFrame = new Frame(Properties.MAX_WIDTH, Properties.MAX_HEIGHT);
        Frame goodbyeFrame = new Frame("Thank You");
        goodbyeFrame.drawBorder();
        mainFrame.drawMiddle(goodbyeFrame.getContent());
        System.out.println(mainFrame.getFrameForPrint());
    }

    /**
     * Player creation menu showing
     *
     * @throws FrameSizeOutOfBound
     */
    public void showPlayerCreationMenuName() throws FrameSizeOutOfBound {
        Frame mainFrame = new Frame(Properties.MAX_WIDTH, Properties.MAX_HEIGHT);
        Frame nameAskingFrame = new Frame("Provide your name please:");
        mainFrame.drawMiddle(nameAskingFrame.getContent());
        System.out.println(mainFrame.getFrameForPrint());
    }

    /**
     * weapon choosing menu displaying
     *
     * @throws FrameSizeOutOfBound
     */
    public void showPlayerCreationMenuWeapon() throws FrameSizeOutOfBound {
        Frame mainFrame = new Frame(Properties.MAX_WIDTH, Properties.MAX_HEIGHT);
        Frame nameFrame = new Frame("Player Name:" + Commons.getPlayerName());
        Frame infoFrame = new Frame("Choose your weapon:");
        Frame weaponFrame = new Frame(35, 20);
        Frame weaponFrame1 = new Frame("Press " + ONE + " - " + WEAPON_NAME_1
                + " (A-" + WEAPON_ATTACK_1 + ",R-" + WEAPON_RANGE_1 + ")", true);
        Frame weaponFrame2 = new Frame("Press " + TWO + " - " + WEAPON_NAME_2
                + " (A-" + WEAPON_ATTACK_2 + ",R-" + WEAPON_RANGE_2 + ")", true);
        Frame weaponFrame3 = new Frame("Press " + THREE + " - " + WEAPON_NAME_3
                + " (A-" + WEAPON_ATTACK_3 + ",R-" + WEAPON_RANGE_3 + ")", true);
        weaponFrame.drawMiddle(Stream.of(weaponFrame1, weaponFrame2, weaponFrame3).toArray(Frame[]::new));
        mainFrame.drawMiddle(Stream.of(nameFrame, infoFrame, weaponFrame).toArray(Frame[]::new));
        System.out.println(mainFrame.getFrameForPrint());
    }

    public void showPauseMenu(Character[][] content) throws FrameSizeOutOfBound {
        Frame mainFrame = new Frame(Properties.MAX_WIDTH, Properties.MAX_HEIGHT);
        mainFrame.drawMiddle(content);
        mainFrame.drawBorder();
        Frame message = new Frame("Want to save the game.");
        message.drawBorder();
        Frame instructionFrame = new Frame(23, 20);
        Frame pressInstruction1 = new Frame("Press " + ONE + " to Return");
        Frame pressInstruction2 = new Frame("Press " + TWO + " to Save");
        Frame pressInstruction3 = new Frame("Press " + THREE + " to Main menu");
        instructionFrame.drawMiddle(Stream.of(pressInstruction1, pressInstruction2,
                pressInstruction3).toArray(Frame[]::new));
        mainFrame.drawMiddle(Stream.of(message, instructionFrame).toArray(Frame[]::new));
        System.out.println(mainFrame.getFrameForPrint());
    }

    public void showNoSaveFileMenu() throws FrameSizeOutOfBound {
        Frame mainFrame = new Frame(Properties.MAX_WIDTH, Properties.MAX_HEIGHT);
        Frame message = new Frame("No save file found or save file not compatible.");
        message.drawBorder();
        Frame instructionFrame = new Frame(20, 20);
        Frame pressInstruction1 = new Frame("Press " + ONE + " to Start");
        Frame pressInstruction3 = new Frame("Press " + THREE + " to Exit");
        instructionFrame.drawMiddle(Stream.of(pressInstruction1, pressInstruction3).toArray(Frame[]::new));
        instructionFrame.drawBorder();
        mainFrame.drawMiddle(Stream.of(message, instructionFrame).toArray(Frame[]::new));
        System.out.println(mainFrame.getFrameForPrint());
    }

    public void inGameHelpMenu() throws FrameSizeOutOfBound {
        Frame mainFrame = new Frame(Properties.MAX_WIDTH, Properties.MAX_HEIGHT);
        Frame message = new Frame("Help");
        message.drawBorder();
        Frame instructionFrame = new Frame(20, 20);
        Frame pressInstruction1 = new Frame("Direction/Control", true);
        Frame pressInstruction2 = new Frame("UP - " + UP, true);
        Frame pressInstruction3 = new Frame("DOWN - " + DOWN, true);
        Frame pressInstruction4 = new Frame("LEFT - " + LEFT, true);
        Frame pressInstruction5 = new Frame("RIGHT - " + RIGHT, true);
        Frame pressInstruction10 = new Frame("FIRE - " + FIRE, true);
        Frame pressInstruction6 = new Frame("HELP - " + HELP, true);
        Frame pressInstruction7 = new Frame("START - " + ONE, true);
        Frame pressInstruction8 = new Frame("SAVE - " + TWO, true);
        Frame pressInstruction9 = new Frame("Main Menu - " + THREE, true);
        instructionFrame.drawMiddle(Stream.of(pressInstruction1, pressInstruction2,
                pressInstruction3, pressInstruction4, pressInstruction5, pressInstruction10,
                pressInstruction6, pressInstruction7, pressInstruction8, pressInstruction9).toArray(Frame[]::new));
        instructionFrame.drawBorder();
        mainFrame.drawMiddle(Stream.of(message, instructionFrame).toArray(Frame[]::new));
        System.out.println(mainFrame.getFrameForPrint());
    }

    public void showGameOverMenu() throws FrameSizeOutOfBound {
        Frame mainFrame = new Frame(Properties.MAX_WIDTH, Properties.MAX_HEIGHT);
        Frame message = new Frame("Better luck next time.");
        message.drawBorder();
        Frame instructionFrame = new Frame(20, 20);
        Frame pressInstruction1 = new Frame("Press " + ONE + " to Start");
        Frame pressInstruction3 = new Frame("Press " + THREE + " to Exit");
        instructionFrame.drawMiddle(Stream.of(pressInstruction1, pressInstruction3).toArray(Frame[]::new));
        instructionFrame.drawBorder();
        mainFrame.drawMiddle(Stream.of(message, instructionFrame).toArray(Frame[]::new));
        System.out.println(mainFrame.getFrameForPrint());
    }

    public void showVictoryMenu() throws FrameSizeOutOfBound {
        Frame mainFrame = new Frame(Properties.MAX_WIDTH, Properties.MAX_HEIGHT);
        Frame message = new Frame("Congratulations.");
        message.drawBorder();
        Frame instructionFrame = new Frame(20, 20);
        Frame pressInstruction1 = new Frame("Press " + ONE + " to Start");
        Frame pressInstruction3 = new Frame("Press " + THREE + " to Exit");
        instructionFrame.drawMiddle(Stream.of(pressInstruction1, pressInstruction3).toArray(Frame[]::new));
        instructionFrame.drawBorder();
        mainFrame.drawMiddle(Stream.of(message, instructionFrame).toArray(Frame[]::new));
        System.out.println(mainFrame.getFrameForPrint());
    }

    public void showGameStartMenu() throws FrameSizeOutOfBound {
        Frame mainFrame = new Frame(Properties.MAX_WIDTH, Properties.MAX_HEIGHT);
        Frame message = new Frame("Instructions");
        message.drawBorder();
        Frame instructionFrame = new Frame(85, 20);
        Frame pressInstruction1 = new Frame("Welcome to the 1st cli based battle royal game.", true);
        Frame pressInstruction2 = new Frame("You are about to enter into the shooting area " +
                "with " + Properties.AI_PLAYER + " players.", true);
        Frame pressInstruction3 = new Frame("You need to survive to win the game " +
                "and kill other players.", true);
        Frame pressInstruction4 = new Frame("Use '" + UP + "','"
                + DOWN + "','" + LEFT + "','" + RIGHT + "' to move up down " +
                "left right and press '" + FIRE + "' to shoot other player.", true);
        Frame pressInstruction5 = new Frame("You can pause anytime in the " +
                "game and resume.", true);
        Frame pressInstruction6 = new Frame("Please save the game if you want to play " +
                "where you left off. Best of luck!", true);
        Frame pressInstruction7 = new Frame("P - Player, E - Enemy, T - Tree, (-) " +
                "Indicating your gun point.", true);
        Frame pressInstruction8 = new Frame("Press 1 to Continue");
        instructionFrame.drawMiddle(Stream.of(pressInstruction1, pressInstruction2,
                pressInstruction3, pressInstruction4, pressInstruction5, pressInstruction6,
                pressInstruction7, pressInstruction8).toArray(Frame[]::new));
        instructionFrame.drawBorder();
        mainFrame.drawMiddle(Stream.of(message, instructionFrame).toArray(Frame[]::new));
        System.out.println(mainFrame.getFrameForPrint());
    }
}
