package org.ni.rpg.utils;

import org.ni.rpg.core.enitiy.helper.Dimension;
import org.ni.rpg.core.enitiy.Player;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static org.ni.rpg.utils.Properties.*;

/**
 * Created by nazmul on 9/29/2018.
 */
public class Commons {
    private static String playerName = "";
    private static boolean showState = false;

    public static String getPlayerName() {
        return playerName;
    }

    public static void setPlayerName(String name) {
        playerName = name;
    }

    public static boolean isShowState() {
        return showState;
    }

    public static void setShowState(boolean showState) {
        Commons.showState = showState;
    }

    public static Dimension calculateDimension(Character[][] content) {
        int height = 0;
        int width = 0;
        for (Character[] row : content) {
            height++;
            if (width < row.length) {
                width = row.length;
            }
        }
        return new Dimension(height, width);
    }

    public static Character[][] generateGameStateContent(int height, int width) {
        Character[][] content = new Character[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                content[i][j] = ' ';
            }
        }
        return content;
    }

    public static int randInt(int min, int max) {
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return randomNum;
    }

    public static String getDirection() {
        int directionPredict = randInt(1, 4);
        if (directionPredict == 1) {
            return UP;
        } else if (directionPredict == 2) {
            return DOWN;
        } else if (directionPredict == 3) {
            return LEFT;
        } else {
            return RIGHT;
        }
    }

    public static boolean isEmpty(List list) {
        if (list == null) {

            return true;
        }
        if (list.size() == 0) {

            return true;
        }

        return false;
    }

    public static boolean isEmpty(String string) {
        if (string == null) {

            return true;
        }
        if ("".equals(string)) {

            return true;
        }

        return false;
    }

    public static boolean isEmpty(Map map) {
        if (map == null) {

            return true;
        }
        if (map.isEmpty()) {

            return true;
        }

        return false;
    }

    public static String getStatusLine(Player player) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(player.getName()).append("-").append("H:").append(player.getHealth()).append(" ")
                .append("A:").append(player.getWeapon().getAttack()).append(" ")
                .append("K:").append(player.getKilled());
        return stringBuffer.toString();
    }
}
