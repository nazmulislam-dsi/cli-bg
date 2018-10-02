package org.ni.rpg.entity;

import org.junit.Test;
import org.ni.rpg.core.enitiy.Frame;
import org.ni.rpg.exception.FrameSizeOutOfBound;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by nazmul on 9/29/2018.
 */

public class FrameTest {
    @Test()
    public void testFrameCreation() {
        Frame frame = new Frame(20,20);
        String expected =
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n";
        String output = frame.getFrameForPrint();
        assertNotNull(output);
        assertEquals(output, expected);
    }

    @Test()
    public void testFrameDrawMiddle() throws FrameSizeOutOfBound {
        Frame frame = new Frame(20,20);
        frame.drawMiddle("Hello World");
        String expected =
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "     Hello World    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n";
        String output = frame.getFrameForPrint();
        assertNotNull(output);
        assertEquals(output, expected);
    }

    @Test()
    public void testClear() throws FrameSizeOutOfBound {
        Frame frame = new Frame(20,20);
        frame.drawMiddle("Hello World");
        String expected =
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "     Hello World    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n";
        String output = frame.getFrameForPrint();
        assertNotNull(output);
        assertEquals(output, expected);
        frame.clean();
        expected =
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n";
        output = frame.getFrameForPrint();
        assertNotNull(output);
        assertEquals(output, expected);
    }
    @Test()
    public void testObjectDrawMiddle() throws FrameSizeOutOfBound {
        Frame frame = new Frame(20,20);
        Character[][] ch = { {'x','x'},{'x','x','x','x'} };
        frame.drawMiddle(ch);
        String expected =
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "        xx          \n" +
                "        xxxx        \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n";
        String output = frame.getFrameForPrint();
        assertNotNull(output);
        assertEquals(output, expected);
    }

    @Test()
    public void testObjectDrawPosition() throws FrameSizeOutOfBound {
        Frame frame = new Frame(20,20);
        Character[][] ch = { {'x','x'},{'x','x','x','x'} };
        frame.drawAtPosition(1,1,ch);
        String expected =
                "                    \n" +
                " xx                 \n" +
                " xxxx               \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    \n";
        String output = frame.getFrameForPrint();
        assertNotNull(output);
        assertEquals(output, expected);
    }

    @Test()
    public void testFrameDrawPosition() throws FrameSizeOutOfBound {
        Frame helloWorld = new Frame(20,20);
        helloWorld.clean();
        helloWorld.drawMiddle("Hello World");
        helloWorld.drawBorder('.','.');
        Frame greeting = new Frame(10,10);
        greeting.drawMiddle("Greeting");
        greeting.drawBorder('.','.');
        Frame frame = new Frame(30,30);
        Stream<Frame> stringStream = Stream.of(helloWorld,greeting);
        frame.drawMiddle(stringStream.toArray(Frame[]::new));
        String expected =
                "     ....................     \n" +
                "     .                  .     \n" +
                "     .                  .     \n" +
                "     .                  .     \n" +
                "     .                  .     \n" +
                "     .                  .     \n" +
                "     .                  .     \n" +
                "     .                  .     \n" +
                "     .                  .     \n" +
                "     .                  .     \n" +
                "     .    Hello World   .     \n" +
                "     .                  .     \n" +
                "     .                  .     \n" +
                "     .                  .     \n" +
                "     .                  .     \n" +
                "     .                  .     \n" +
                "     .                  .     \n" +
                "     .                  .     \n" +
                "     .                  .     \n" +
                "     ....................     \n" +
                "          ..........          \n" +
                "          .        .          \n" +
                "          .        .          \n" +
                "          .        .          \n" +
                "          .        .          \n" +
                "          .Greeting.          \n" +
                "          .        .          \n" +
                "          .        .          \n" +
                "          .        .          \n" +
                "          ..........          \n";
        String output = frame.getFrameForPrint();
        assertNotNull(output);
        assertEquals(output, expected);
    }
}
