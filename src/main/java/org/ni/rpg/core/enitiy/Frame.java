package org.ni.rpg.core.enitiy;

import org.ni.rpg.core.enitiy.helper.Dimension;
import org.ni.rpg.exception.FrameSizeOutOfBound;
import org.ni.rpg.utils.Commons;
import org.ni.rpg.utils.Properties;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by nazmul on 9/29/2018.
 */
public class Frame {
    private int WIDTH = -1;
    private int HEIGHT = -1;
    private int frameRate = 10;
    private Character[][] content;

    public Frame() {
        this(Properties.MAX_WIDTH, Properties.MAX_HEIGHT);
    }

    public Frame(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        content = new Character[HEIGHT][WIDTH];
        clean();
    }

    public Frame(String message) throws FrameSizeOutOfBound {
        this(message, false);
    }

    public Frame(String message, Boolean small) throws FrameSizeOutOfBound {
        char[] stringToCharArray = message.toCharArray();
        if (!small) {
            WIDTH = stringToCharArray.length + 2;
            HEIGHT = 1 + 2;
        } else {
            WIDTH = stringToCharArray.length;
            HEIGHT = 1;
        }
        content = new Character[HEIGHT][WIDTH];
        clean();
        drawMiddle(message);
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }

    public Character[][] getContent() {
        return content;
    }

    public void setContent(Character[][] content) {
        this.content = content;
    }

    public void drawBorder() {
        drawBorder('.', '.');
    }

    public void drawBorder(char sideBorder, char uperBorder) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (j == 0 || j == WIDTH - 1) {
                    content[i][j] = sideBorder;
                } else if (i == 0 || i == HEIGHT - 1) {
                    content[i][j] = uperBorder;
                }
            }
        }
    }


    public void clean() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                content[i][j] = ' ';
            }
        }
    }

    public void drawMiddle(String string) throws FrameSizeOutOfBound {
        char[] stringToCharArray = string.toCharArray();
        if (stringToCharArray.length > WIDTH) {
            throw new FrameSizeOutOfBound();
        }
        int startWidth = WIDTH / 2 - stringToCharArray.length / 2;
        int startHeight = HEIGHT / 2;
        for (int i = 0; i < stringToCharArray.length; i++) {
            content[startHeight][startWidth] = stringToCharArray[i];
            startWidth++;
        }
    }

    public void drawMiddle(Character[][] characters) throws FrameSizeOutOfBound {
        Dimension dimension = Commons.calculateDimension(characters);
        int startHeight = HEIGHT / 2 - dimension.getHeight() / 2;
        if (dimension.getHeight() > HEIGHT || (characters.length > 0 && dimension.getWidth() > WIDTH)) {
            throw new FrameSizeOutOfBound();
        }
        for (int i = 0; i < characters.length; i++) {
            int startWidth = WIDTH / 2 - dimension.getWidth() / 2;
            for (int j = 0; j < characters[i].length; j++) {
                content[startHeight][startWidth] = characters[i][j];
                startWidth++;
            }
            startHeight++;
        }
    }

    public void drawMiddle(Frame[] frames) throws FrameSizeOutOfBound {
        // TODO Drawing Frame side by side
        int totalHeight = 0;
        for (Frame frame : frames) {
            totalHeight += frame.getHEIGHT();
        }
        int positionY = HEIGHT / 2 - totalHeight / 2;
        for (Frame frame : frames) {
            int positionX = WIDTH / 2 - frame.getWIDTH() / 2;
            drawAtPosition(positionX, positionY, frame.getContent());
            positionY += frame.getHEIGHT();
        }
    }

    public void drawAtPosition(int positionX, int positionY, Character[][] characters) throws FrameSizeOutOfBound {
        int startHeight = positionY;
        Dimension dimension = Commons.calculateDimension(characters);
        if (positionX < 0 || positionY < 0
                || positionY + dimension.getHeight() > HEIGHT
                || (dimension.getWidth() + positionX > WIDTH)) {
            throw new FrameSizeOutOfBound();
        }
        for (int i = 0; i < characters.length; i++) {
            int startWidth = positionX;
            for (int j = 0; j < characters[i].length; j++) {
                content[startHeight][startWidth] = characters[i][j];
                startWidth++;
            }
            startHeight++;
        }
    }

    public String getFrameForPrint() {
        StringBuffer forPrint = new StringBuffer();
        for (int i = 0; i < HEIGHT; i++) {
            String line = Arrays.stream(content[i]).map(Object::toString).collect(Collectors.joining());
            forPrint.append(line).append("\n");
        }
        return forPrint.toString();
    }

}
