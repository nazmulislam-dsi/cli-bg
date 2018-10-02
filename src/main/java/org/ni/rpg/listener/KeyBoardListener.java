package org.ni.rpg.listener;

import org.ni.rpg.exception.FrameSizeOutOfBound;
import org.ni.rpg.services.GameEngineService;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by nazmul on 9/29/2018.
 */
public class KeyBoardListener {

    private static KeyBoardListener singleton = null;
    private GameEngineService gameEngineService;


    public synchronized static KeyBoardListener getInstance(GameEngineService gameEngineService) {
        if (singleton == null) singleton = new KeyBoardListener(gameEngineService);
        return singleton;
    }


    private KeyBoardListener(GameEngineService gameEngineService) {
        this.gameEngineService = gameEngineService;
    }

    public void listen() throws FrameSizeOutOfBound, IOException, ClassNotFoundException {
        while (true) {
            Scanner input = new Scanner(System.in);
            String givenInput = input.nextLine();
            gameEngineService.registerKeyEvent(givenInput);
        }
    }
}
