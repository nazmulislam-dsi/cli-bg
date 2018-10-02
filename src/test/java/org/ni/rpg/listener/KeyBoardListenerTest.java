package org.ni.rpg.listener;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ni.rpg.services.GameEngineService;
import org.ni.rpg.exception.FrameSizeOutOfBound;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by nazmul on 10/2/2018.
 */
public class KeyBoardListenerTest {

    private static KeyBoardListener keyBoardListener;
    private static GameEngineService gameEngineService;


    @BeforeClass
    public static void initTest(){
        System.out.println("Testing starts");
        gameEngineService = gameEngineService.getInstance();
        keyBoardListener=KeyBoardListener.getInstance(gameEngineService);
    }

    @Test
    public void setKeyBoardListenerCreationTest() throws ClassNotFoundException, IOException, FrameSizeOutOfBound {
        assertNotNull(keyBoardListener);
        System.out.println("keyBoardListener create successfully");
    }

    @AfterClass
    public static void afterTest(){
        System.out.println("Testing ends");
    }

}