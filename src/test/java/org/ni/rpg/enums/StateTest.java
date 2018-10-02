package org.ni.rpg.enums;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by nazmul on 10/2/2018.
 */
public class StateTest {
    @Test
    public void testGetState() {
        assertThat(State.valueOf("MAIN_MENU"), is(notNullValue()));
    }
}
